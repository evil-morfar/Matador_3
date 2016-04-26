package game_controller;

import game_entities.Board;
import game_entities.Player;

import java.util.ArrayList;
import die_classes.DieCup;
import die_classes.DieCup_Interface;
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

		while(true){
			switch(state){
			case NAME_STATE: namestate();
			break;
			case PLAY_STATE: playstate();
			break;
			case WIN_STATE: winstate();
			break;
			}
		}
	}

	private void namestate(){

		String name = GetUserString
		
	}

	private void playstate(){

	}
	
	private void winstate(){
	}
}


