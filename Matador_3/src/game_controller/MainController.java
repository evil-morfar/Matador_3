package game_controller;

import game_entities.Board;
import game_entities.Player;

import java.util.ArrayList;

import desktop_resources.GUI;
import die_classes.DieCup;
import die_classes.DieCup_Interface;
import fields.AbstractFields;
import fields.AbstractOwnable;
import game_boundaries.*;

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
	private Interface output;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Board board;
	
	private Boolean debug = true; //Sets up players automatically if true
	
	//Constructor
	public MainController(){
		dieCup = new DieCup();
		players = new ArrayList<Player>();
		output = new GUI_boundary("");
		board = new Board();
	}
	

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public int getRoll(){
		return dieCup.getSum();
	}

	public void run(){
		GUI.create(board.getGuiFields());

		while(true){
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
				break;
			}
		}
	}

	private void namestate(){
		if (debug){
			players.add(new Player("Player 1", 30000, "Blue", 1, 1));
			output.addPlayer("Player 1", 30000, 1);
			players.add(new Player("Player 2", 30000, "White",  1, 2));
			output.addPlayer("Player 2", 30000, 2);
			state = GameState.PLAY_STATE;
		}
		currentPlayer = players.get(0);
		
	}

	private void playstate(){
		String option = "";
		if (currentPlayer.isInJail()) {
			if(currentPlayer.getNumJailRolls() < 3) {
				option = GUI.getUserButtonPressed(currentPlayer.getName()+" is jailed!", "Roll", "Pay 1000,-");
				if (option.equals("Roll")){
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
				} else { // Player chooses to pay
					currentPlayer.withdrawBalance(1000);
					output.updateBalance(currentPlayer);
					currentPlayer.setInJail(false);
					currentPlayer.setNumJailRolls(0);
					playstate(); // Just call playstate to continue the game
				}
			} else {
				option = GUI.getUserButtonPressed(currentPlayer.getName()+" is jailed and used 3 rolls", "Pay 1000,-");
				currentPlayer.withdrawBalance(1000);
				output.updateBalance(currentPlayer);
				currentPlayer.setInJail(false);
				currentPlayer.setNumJailRolls(0);
				playstate(); // Just call playstate to continue the game
			}
		} else {			
			Boolean end = false;
			int numDoubles = 0;
			Boolean hasRolled = false;
			AbstractFields field = null;
			while(!end) {
				String sPlayer = currentPlayer.getName() + "'s turn:";
				if(!option.equals("end")) // Special case
					if(!hasRolled)
						 option = GUI.getUserButtonPressed(sPlayer, "Roll", "Build", "Pawn", "Save and Exit");
					// Only show the "Buy" option if it's possible to buy the field
					else if(field instanceof AbstractOwnable && !((AbstractOwnable) field).isOwned())
						if (numDoubles != 0) // Can't end turn when one still have a roll
							option = GUI.getUserButtonPressed(sPlayer, "Roll", "Buy", "Build", "Pawn");
						else
							option = GUI.getUserButtonPressed(sPlayer, "Buy", "Build", "Pawn", "End Turn");
					else if (numDoubles != 0)
						option = GUI.getUserButtonPressed(sPlayer, "Roll", "Build", "Pawn");
					else
						option = GUI.getUserButtonPressed(sPlayer, "Build", "Pawn", "End Turn");
				
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
				
				case("Pawn"):
					//TODO
					break;
				
				case("Buy"):
					currentPlayer.withdrawBalance(((AbstractOwnable) field).getPrice());
					output.updateBalance(currentPlayer);
					((AbstractOwnable) field).setOwner(currentPlayer);	
					output.setOwner(field.getFieldID(), currentPlayer.getName());
					break;
					
				case("End Turn"): case("end"):
					System.out.println(currentPlayer.getName());
					currentPlayer = getNextPlayer(currentPlayer);
					System.out.println(currentPlayer.getName());
					hasRolled = false;
					end = true;
					
				case("Save and Exit"):
					end = true;
				//TODO Database
					break;
				}
			}
		}
	}
	
	/**
	 * Gets the next player in players based on a given player.
	 * @param player The player right before the intended player.
	 * @return The next player. Will be the first if player was the last in line.
	 */
	private Player getNextPlayer(Player player) {
		if(player.getPlayerID() == players.size())
			return players.get(0);
		else
			return players.get(player.getPlayerID());
	}

	/**
	 * Moves a player a number of fields. Note player position starts at 1, while
	 * field indicies starts at 0.
	 * @param player The player to move
	 * @param num Number of fields to move.
	 */
	private void movePlayer(Player player, int num){
		int position = player.getPosition();
		if (position + num > board.getFields().length + 1){
			position -= board.getFields().length + 1;
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
	private void movePlayerTo(Player player, int position){
		player.setPosition(position);
		output.movePlayer(position, player.getName());
	}

	private void winstate(){
	}
}


