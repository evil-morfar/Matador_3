package game_entities;

import game.entities.Balance;

public class Player {
	
	String name;
	int position;
	boolean broke;
	int startingBalance;
	String color;
	boolean isInJail;
	int playerID;
	
	
	public Player(String name, int startingBalance, String color, int position, int PlayerID){
		this.name = name;
		this.balance = balance;
		this.Broke = false; 
		this.position = position;
	}

	
	public String getName(){
		return name;
	}


	public void setPosition(int position){
		this.position = position;		
	}
	
	public void int changeBalance(){
		
	}
	
	public void int setIsBroke(){
		
	}
	
	public String getColor(){
		return color;
	}
	
	public int getPlayerID(){
		return playerID;
	}
}
