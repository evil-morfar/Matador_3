package game_controller;

import game_entities.Board;
import die_classes.DieCup;
import die_classes.DieCup_Interface;
import game_boundaries.*;

public class Controller {

	//evt add flerer sub state under PLAY_STATE
	public enum GameState{NAME_STATE, PLAY_STATE, WIN_STATE}; 

	private int turnNumber;
	
	private DieCup_Interface dieCup; 
	private GUI_boundary output;

	
	
	//Constructor
	public Controller(){
		dieCup = DiceCup;
		//output = insert output class here
	}
	

	public void run(){

		while(true){
			switch(state){
			case NAME_STATE: namestate();
			break;
			case PLAY_STATE: playstate();
			break;
			case PLAY_STATE2: playstate2();
			break;
			case PLAY_STATE3: playstate3();
			break;
			case WIN_STATE: winstate();
			break;
			}
		}
	}

	private void namestate(){

	}

	private void playstate(){

	}
	
	private void winstate(){
	}
	}
	
}


