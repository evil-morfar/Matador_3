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
	
	private Board gameboard;
	
	
	
	
	dieCup = new DieCup(); 
	output = new GUI_boundary();
	
	
	
	public void run(){
		while(true){
			switch(state){
			
			
			}
			
			
			
			
		}
	}

}
