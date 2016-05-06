package game_controller;

import java.util.ArrayList;

import cardTypes.CardCreater;
import cardTypes.SuperCard;
import die_classes.DieCup;
import fields.AbstractFields;
import fields.AbstractOwnable;
import game_boundaries.GUI_boundary;
import game_entities.Board;
import game_entities.Player;

/**
 * 
 * @author PC / Valdemar
 *
 */

public class MainController {

	//evt add flerer sub state under PLAY_STATE
	public enum GameState{NAME_STATE, PLAY_STATE, WIN_STATE}; 
	private GameState state = GameState.NAME_STATE;

	private int turnNumber;

	private DieCup dieCup; 
	private GUI_boundary output;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Board board;
	private Boolean endTurn = false;
	private final int startingBalance = 30000;

	private static CardCreater cardcreater;

	//Constructor
	public MainController(){
		dieCup = new DieCup();
		players = new ArrayList<Player>();
		output = new GUI_boundary("");
		board = new Board();
		cardcreater = new CardCreater();
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

	public int getRoll(){
		return dieCup.getSum();
	}

	public void run(){
		output.create(board.getGuiFields());
		boolean running = true;
		while(running){
			System.out.println("New loop");
			switch(state){
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

	private void namestate(){

		for (int i = 1; i <= 6; i++){
			boolean error = false;
			// Checks if the names are long enough
			while (true){
				String name = output.promptPlayerName(i+1, error);
				if (name.length() == 0){
					if(i>=2){
						// breaks the loop and starts the game
						i = 6;
						break;
					}
					else
						error = true;
				}
				else{
					players.add(new Player(name, startingBalance, "blue", 1, i));
					// Adds the player to the GUI
					output.addPlayer(name, startingBalance, i);
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

				case("Build"):
					//TODO
					break;


				case("Buy"):
					currentPlayer.withdrawBalance(((AbstractOwnable) field).getPrice());
				output.updateBalance(currentPlayer);
				((AbstractOwnable) field).setOwner(currentPlayer);	
				output.setOwner(field.getFieldID(), currentPlayer.getName());
				output.showFieldBoughtMessage(currentPlayer.getName(), field.getName(), ((AbstractOwnable)field).getPrice());

				case("End Turn"): case("end"):
					System.out.println(currentPlayer.getName());
				currentPlayer = getNextPlayer(currentPlayer);
				System.out.println(currentPlayer.getName());
				hasRolled = false;
				this.endTurn = true;
				break;

				case("Save and Exit"):
					this.endTurn = true;
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
	 * @param player The player right before the intended player.
	 * @return The next player. Will be the first if player was the last in line.
	 */
	private Player getNextPlayer(Player player) {
		Player p;
		if(player.getPlayerID() == players.size())
			p = players.get(0);
		else
			p = players.get(player.getPlayerID());
		// Make sure we don't mark a broke player as the next player
		return !p.isBroke() ? p : getNextPlayer(p);
	}

	/**
	 * Moves a player a number of fields. Note player position starts at 1, while
	 * field indicies starts at 0.
	 * @param player The player to move
	 * @param num Number of fields to move.
	 */
	public void movePlayer(Player player, int num){
		int position = player.getPosition();
		if (position + num > board.getFields().length){
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
	 * @param player The player to move
	 * @param position The fieldID to move to
	 */
	public void movePlayerTo(Player player, int position){
		player.setPosition(position);
		output.movePlayer(position, player.getName());
	}

	/**
	 * @return The number of broke players
	 */
	private int getNumNotBrokePlayers(){
		int num = 0;
		for(Player player : players){
			if(!(player.isBroke()))
				num++;
		}
		return num;
	}

	private void winstate(){
		output.showMessage(currentPlayer.getName() + " has won!");
		System.exit(0);
	}

	/**
	 * Ends the player's turn.
	 */
	public void endTurn(){
		this.endTurn = true;
	}

	public int getNumPlayers(){
		return players.size();
	}

	
}


