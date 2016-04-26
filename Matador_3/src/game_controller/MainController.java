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
			players.add(new Player("Player 1", 30000, "Blue", 0, 1));
			output.addPlayer("Player 1", 30000, 1);
			players.add(new Player("Player 2", 30000, "White",  0, 2));
			output.addPlayer("Player 2", 30000, 2);
			state = GameState.PLAY_STATE;
		}
		currentPlayer = players.get(0);
		
	}

	private void playstate(){
		if (currentPlayer.isInJail()) {
			// TODO Jail options
		} else {
			GUI.showMessage(currentPlayer.getName() +"'s turn!");
			Boolean end = false;
			while(!end) {
				String option = GUI.getUserButtonPressed("Choose option:", "Roll", "Build", "Pawn", "Save and Exit");
				switch(option) {
				case("Roll"):
					dieCup.roll();
					output.setDice(dieCup.getDice());
					movePlayer(currentPlayer, dieCup.getSum());
					AbstractFields field = board.getFields()[currentPlayer.getPosition()];
					field.landOnField(this);
					
					while(!end) {
						// Only show the "Buy" option if it's possible to buy the field
						if(field instanceof AbstractOwnable && !((AbstractOwnable) field).isOwned())
							option = GUI.getUserButtonPressed("Choose option:", "Buy", "Build", "Pawn", "End Turn");
						else
							option = GUI.getUserButtonPressed("Choose option:", "Build", "Pawn", "End Turn");
						
						switch(option) {
						case("Buy"):
							//TODO Test if the field is buyable
							currentPlayer.withdrawBalance(((AbstractOwnable) field).getPrice());
							output.updateBalance(currentPlayer);
							((AbstractOwnable) field).setOwner(currentPlayer);	
							output.setOwner(field.getFieldID(), currentPlayer.getName());
							break;
						case("Build"):
							//TODO
							break;
						case("Pawn"):
							//TODO
							break;
						case("End Turn"):
							System.out.println(currentPlayer.getName());
							currentPlayer = getNextPlayer(currentPlayer);
							System.out.println(currentPlayer.getName());
							end = true;
						}
					}
					
					break;
				case("Build"):
					//TODO
					break;
				
				case("Pawn"):
					//TODO
					break;
				
				case("Save and Exit"):
					System.exit(0);
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

	private void movePlayer(Player player, int num){
		int position = player.getPosition();
		if (position + num > board.getFields().length)
			position -= board.getFields().length;
		position += num;
		player.moveTo(position);
		output.movePlayer(position, player.getName());
	}

	private void winstate(){
	}
}


