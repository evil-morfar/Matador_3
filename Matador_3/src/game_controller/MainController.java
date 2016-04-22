package game_controller;

import game_entities.Board;
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
	
	private DieCup_Interface dieCup; 
	private Interface output;

	private ArrayList<String> playerNames;
	
	
	//Constructor
	public MainController(){
		dieCup = DiceCup;
		playerNames = new ArrayList<String>();
		output = new GUI_boundary("");
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


