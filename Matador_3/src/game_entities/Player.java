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


	/**
	 * 
	 * @param name
	 * @param startingBalance
	 * @param color
	 * @param position
	 * @param PlayerID
	 */
	public Player(String name, int startingBalance, String color, int position, int PlayerID){
		this.name = name;
		this.account = new Account(startingBalance);
		this.broke = false; 
		this.position = position;
	}

	/**
	 * 
	 * @return
	 */
	public String getName(){
		return name;
	}

	public int getBalance(){
		return this.account.getBalance();
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
	
	/**
	 * Withdraws an amount from the players balance.
	 * @param value The amount to withdraw.
	 * @see Player#depositBalance(int)
	 */
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
