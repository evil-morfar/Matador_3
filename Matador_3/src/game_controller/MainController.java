package game_controller;

import java.awt.Color;
import java.util.ArrayList;

import cardTypes.CardCreater;
import cardTypes.SuperCard;
import database.DatabaseAccess1;
import die_classes.DieCup;
import fields.AbstractFields;
import fields.AbstractOwnable;
import fields.Territory;
import game_boundaries.GUI_boundary;
import game_entities.Board;
import game_entities.Player;

/**
 * 
 * @author PC / Valdemar
 *
 */

public class MainController {

	// evt add flerer sub state under PLAY_STATE
	public enum GameState {
		START_STATE, LOAD_STATE, NAME_STATE, PLAY_STATE, WIN_STATE
	};

	private GameState state = GameState.START_STATE;

	private int turnNumber;

	private DieCup dieCup;
	private GUI_boundary output;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Board board;
	private Boolean endTurn = false;
	private final int startingBalance = 30000;
	private ArrayList<Territory> candidateTerritories;
	private ArrayList<Territory> buildableTerritories;
	private DatabaseAccess1 db;

	private static CardCreater cardcreater;

	// Constructor
	public MainController() {
		dieCup = new DieCup();
		players = new ArrayList<Player>();
		output = new GUI_boundary("");
		board = new Board();
		cardcreater = new CardCreater();
		candidateTerritories = createCandidateTerritory();
		buildableTerritories = new ArrayList<Territory>();
		db = new DatabaseAccess1();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public CardCreater getCardCreater() {
		return cardcreater;
	}

	public Board getBoard() {
		return board;
	}

	public GUI_boundary getGUI() {
		return output;
	}

	public int getRoll() {
		return dieCup.getSum();
	}

	public void run() {
		output.create(board.getGuiFields());
		boolean running = true;
		while (running) {
			System.out.println("New loop");
			String option = "";
			switch (state) {
			case START_STATE:
				if(!db.hasConnection())
					output.showMessage("No connection to the Database, games will be offline only.");
				option = output.getUserButtonPressed("Welcome", "New Game", "Load Game", "Exit");
				
				switch(option) {
				case("New Game"):
					String name = output.getUserString("Enter game name:");
					if(name.isEmpty()){
						output.showMessage("Game name cannot be empty");
						break;
					}
					db.setGameID(db.createNewGame(name, 1));
					this.state = GameState.NAME_STATE;						
					break;
				case("Load Game"):
					
					break;
				
				case("Exit"):					
				default:
					System.exit(0);
				}
				break;
				
			case LOAD_STATE:
				
				break;
			
			case NAME_STATE:
				namestate();
				break;
			case PLAY_STATE:
				playstate();
				break;
			case WIN_STATE:
				winstate();
				running = false;
				break;
			}
		}
	}

	private void namestate() {
		String[] colors = {"blue", "white", "magenta", "yellow", "black", "green"};
		for (int i = 1; i <= 6; i++) {
			boolean error = false;
			// Checks if the names are long enough
			while (true) {
				String name = output.promptPlayerName(i + 1, error);
				if (name.length() == 0) {
					if (i >= 2) {
						// breaks the loop and starts the game
						i = 6;
						break;
					} else
						error = true;
				} else {
					players.add(new Player(name, startingBalance, colors[i-1], 1, i));
					// Adds the player to the GUI
					output.addPlayer(name, startingBalance, i);
					// and the db
					db.addNewPlayer(i, name, startingBalance, 0, colors[i-1], 1);
					break;
				}
			}
		}
		currentPlayer = players.get(0);
		state = GameState.PLAY_STATE;
	}

	private void playstate(){
		if(getNumNotBrokePlayers() == 1){
			state = GameState.WIN_STATE;
			return;
		}
		String option = "";
		if (currentPlayer.isInJail()) {
			if(currentPlayer.getNumJailRolls() < 3) {
				if(currentPlayer.getNumJailCards() > 0 )
					option = output.getUserButtonPressed(currentPlayer.getName()+" is jailed!", "Roll", "Pay 1000,-", "Use Jail Card");
				else
					option = output.getUserButtonPressed(currentPlayer.getName()+" is jailed!", "Roll", "Pay 1000,-");

				switch(option) {
				case ("Roll"):
					dieCup.roll();
				output.setDice(dieCup.getDice());
				if(dieCup.isDoubles() != 0 ) { // Player is freed
					currentPlayer.setInJail(false);
					currentPlayer.setNumJailRolls(0);
					movePlayer(currentPlayer, dieCup.getSum());
					AbstractFields field = board.getFields()[currentPlayer.getPosition()-1];
					field.landOnField(this);
				} else
					currentPlayer.setNumJailRolls(currentPlayer.getNumJailRolls() + 1);
				break;

				default: // It actually saves lines doing it this way ;)
					if(option.equals("Use Jail Card"))
						currentPlayer.decreaseNumJailCards();
					else {
						currentPlayer.withdrawBalance(1000);
						output.updateBalance(currentPlayer);
					}
					currentPlayer.setInJail(false);
					currentPlayer.setNumJailRolls(0);
					playstate(); // Just call playstate to continue the game
					break;					
				}
			} else {
				if(currentPlayer.getNumJailCards() > 0 )
					option = output.getUserButtonPressed(currentPlayer.getName()+" is jailed and used 3 rolls", "Pay 1000,-", "Use Jail Card");
				else
					option = output.getUserButtonPressed(currentPlayer.getName()+" is jailed and used 3 rolls", "Pay 1000,-");

				if(option.equals("Pay 1000,-")) {
					currentPlayer.withdrawBalance(1000);
					output.updateBalance(currentPlayer);					
				} else
					currentPlayer.decreaseNumJailCards();
				currentPlayer.setInJail(false);
				currentPlayer.setNumJailRolls(0);
				playstate(); // Just call playstate to continue the game
			}
		} else {			
			this.endTurn = false;
			int numDoubles = 0;
			Boolean hasRolled = false;
			AbstractFields field = null;
			while(!this.endTurn) {
				String sPlayer = currentPlayer.getName() + "'s turn:";
				if(!option.equals("end")) // Special case
					if(!hasRolled)
						option = output.getUserButtonPressed(sPlayer, "Roll", "Build", "Save and Exit");
				// Only show the "Buy" option if it's possible to buy the field
					else if(field instanceof AbstractOwnable && !((AbstractOwnable) field).isOwned())
						if (numDoubles != 0) // Can't end turn when one still have a roll
							option = output.getUserButtonPressed(sPlayer, "Roll", "Buy", "Build");
						else
							option = output.getUserButtonPressed(sPlayer, "Buy", "Build", "End Turn");
					else if (numDoubles != 0)
						option = output.getUserButtonPressed(sPlayer, "Roll", "Build");
					else
						option = output.getUserButtonPressed(sPlayer, "Build", "End Turn");

				switch(option) {
				case("Roll"):
					hasRolled = true;
				dieCup.roll();
				output.setDice(dieCup.getDice());
				if(dieCup.isDoubles()>0)
					numDoubles++;
				else
					numDoubles = 0;
				if(numDoubles == 3){
					currentPlayer.setInJail(true);
					movePlayerTo(currentPlayer, 11);
					option = "end";
					break;
				}
				movePlayer(currentPlayer, dieCup.getSum());
				field = board.getFields()[currentPlayer.getPosition()-1];
				field.landOnField(this);
				output.updateBalance(currentPlayer); // For when they've payed stuff			
				break;

				case ("Build"):
					String buildChoice = "";
				buildableTerritories.clear(); // which territories to be sent to the array on the drop down menu has to be cleared 
				for (Territory candidateTerritory : candidateTerritories) { //This block cycles through all Territories in the game and makes sure they meet all criteria for building a house
					if (!candidateTerritory.hasHotel()) {
						if (currentPlayer.getBalance() > candidateTerritory.getHousePrice()) {
							int numOfTerritoryThisColor = board.getNumColorFields(candidateTerritory.getColor()); //This sets the number of Territories in the color group
							if (board.getNumOwnedSameColor(currentPlayer,
									candidateTerritory.getColor()) == numOfTerritoryThisColor) { //This verifies that the number of Territories of this color owned by the player is the same as the number above
								int territoryWithMoreOrEqualHouses = 0;
								for (Territory comparedTerritory : candidateTerritories) { //This block then cycles through all the Territories in the game again, comparing them all to the candidate territory
									if (candidateTerritory.getColor() == comparedTerritory.getColor()) { // If it hits another Territory in the same color group
										if (comparedTerritory.getNumHouses() >= candidateTerritory.getNumHouses()) { //It compares houses, and verifies that the candidate Territory has houses less than or equal to the current Territory in the cycle.
											territoryWithMoreOrEqualHouses++;
										}
									}
									if (territoryWithMoreOrEqualHouses == numOfTerritoryThisColor) 
										break; // No need to keep going
												// if we've already
												// checked all the
												// colors in the group.
								}
								if (territoryWithMoreOrEqualHouses == numOfTerritoryThisColor) { //This verifies that all the Territories in the color group has houses less than or equal to the candidate Territory.
									buildableTerritories.add(candidateTerritory);

								}
							}
						}
					}
				}
				if (buildableTerritories.size() != 0) { //Makes sure there are actually Territories in the array.
					String[] buildableStrings = new String[buildableTerritories.size()];
					int i = 0;
					for (Territory buildable : buildableTerritories) { //Cycles through all buildable Territories and creates strings for either buying a house or a hotel.
						int numberOfHouses = buildable.getNumHouses();
						if (numberOfHouses < 4) {
							buildableStrings[i] = i+1 + ") " + buildable.getName() + " - Hus nr. " + numberOfHouses + 1
									+ ": " + buildable.getHousePrice() + " kr.";
						} else {
							buildableStrings[i] = buildable.getName() + " - Hotel: " + buildable.getHousePrice()
									+ " kr.";
						}
						i++;
					}
					buildChoice = output.buildSelection("V�lg grund til at bygge p�", buildableStrings);
					for (Territory buildable : buildableTerritories) {
						if (buildChoice.contains(buildable.getName())) { //Checks which Territory Name is appears in the returned string, then adds a house or hotel based on the number of houses already present.
							if(buildable.getNumHouses()<4){
								buildable.setNumHouses(buildable.getNumHouses()+1);
								currentPlayer.withdrawBalance(buildable.getHousePrice());
								output.setHouses(buildable.getFieldID(), buildable.getNumHouses()+1);
							}else {
								buildable.setHotel(true);
								buildable.setNumHouses(0);
								currentPlayer.withdrawBalance(buildable.getHousePrice());
								output.setHotel(buildable.getFieldID(), true);	
								output.setHouses(buildable.getFieldID(), 0);
							}
							break;
						}
					}
				} else {
					getGUI().showNotEnoughBalanceToBuild(currentPlayer.getName());
				}
					break;


				case("Buy"):
					currentPlayer.withdrawBalance(((AbstractOwnable) field).getPrice());
				output.updateBalance(currentPlayer);
				((AbstractOwnable) field).setOwner(currentPlayer);	
				output.setOwner(field.getFieldID(), currentPlayer.getName());
				output.showFieldBoughtMessage(currentPlayer.getName(), field.getName(), ((AbstractOwnable)field).getPrice());
				db.saveField((AbstractOwnable) field);

				case("End Turn"): case("end"):
					System.out.println(currentPlayer.getName());
				currentPlayer = getNextPlayer(currentPlayer);
				System.out.println(currentPlayer.getName());
				hasRolled = false;
				this.endTurn = true;
				db.savePlayer(currentPlayer);
				db.saveGame(currentPlayer.getPlayerID(), board);
				break;

				case("Save and Exit"):
					this.endTurn = true;
				db.savePlayer(currentPlayer);
				db.saveGame(currentPlayer.getPlayerID(), board);
				System.exit(0);
				//TODO Database
				break;
				}
			}
			currentPlayer.setNumDoubles(numDoubles);
		}
	}

	/**
	 * Gets the next player in players based on a given player.
	 * 
	 * @param player
	 *            The player right before the intended player.
	 * @return The next player. Will be the first if player was the last in
	 *         line.
	 */
	private Player getNextPlayer(Player player) {
		Player p;
		if (player.getPlayerID() == players.size())
			p = players.get(0);
		else
			p = players.get(player.getPlayerID());
		// Make sure we don't mark a broke player as the next player
		return !p.isBroke() ? p : getNextPlayer(p);
	}

	/**
	 * Moves a player a number of fields. Note player position starts at 1,
	 * while field indices starts at 0.
	 * 
	 * @param player
	 *            The player to move
	 * @param num
	 *            Number of fields to move.
	 */
	public void movePlayer(Player player, int num) {
		int position = player.getPosition();
		if (position + num > board.getFields().length) {
			position -= board.getFields().length;
			System.out.println(player.getName() + " passed start, received 4k.");
			player.depositBalance(4000);
		}
		position += num;
		System.out.println(player.getName() + " moved to " + position);
		player.setPosition(position);
		output.movePlayer(position, player.getName());
	}

	/**
	 * Moves a player to a specific position
	 * 
	 * @param player
	 *            The player to move
	 * @param position
	 *            The fieldID to move to
	 */
	public void movePlayerTo(Player player, int position) {
		player.setPosition(position);
		output.movePlayer(position, player.getName());
	}

	/**
	 * @return The number of broke players
	 */
	private int getNumNotBrokePlayers() {
		int num = 0;
		for (Player player : players) {
			if (!(player.isBroke()))
				num++;
		}
		return num;
	}
	/**
	 * @return Create an ArrayList of fields of type Territory
	 */
	private ArrayList<Territory> createCandidateTerritory(){
		ArrayList<Territory> candidateTerritory = new ArrayList<Territory>();
		for(AbstractFields field : board.fields){
		if (field.getFieldType().equals("Territory")){
			candidateTerritory.add((Territory) field);
		}
		}
		return candidateTerritory;
	}
	
	private void winstate() {
		getGUI().showWinner(currentPlayer.getName());
		System.exit(0);
	}

	/**
	 * Ends the player's turn.
	 */
	public void endTurn() {
		this.endTurn = true;
	}

	public int getNumPlayers() {
		return players.size();
	}

}