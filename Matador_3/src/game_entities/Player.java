package game_entities;

import game.entities.Balance;

public class Player {
	
	private String name;
	private int position;
	private boolean broke;
	private String color;
	private boolean isInJail;
	private int playerID;
	private Account account;


	
	public Player(String name, int startingBalance, String color, int position, int PlayerID){
		this.name = name;
		this.account = new Account(startingBalance);
		this.broke = false; 
		this.position = position;
	}

	public String getName(){
		return name;
	}


	public void setPosition(int position){
		this.position = position;		
	}
	
	public void setBalance(int value){
		this.account.setBalance(value);
	}
	
	public void depositBalance(int value){
		this.account.deposit(value);
		
	}
	
	public void withdrawBalance(int value){
		this.account.withdraw(value);
		
	}
	
	public void setIsBroke(boolean isBroke){
		this.broke = isBroke;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getPlayerID(){
		return playerID;
	}
}
