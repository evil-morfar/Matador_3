package game_entities;

import game_entities.Account;

/**
 * 
 * @author PC / Valdemar
 *
 */


public class Player {
	
	private String name;
	private int position;
	private boolean isBroke;
	private String color;
	private boolean isInJail;
	private int numJailRolls;
	private int numJailCards;
	private int numDoubles;
	private int playerID;
	private Account account;


	/**
	 * Constructor for the Player class
	 * @param name, the players name
	 * @param startingBalance, the amount of starting credits for a player
	 * @param color, the color type the player will display on the gameboard
	 * @param position, what field # the player is currently on 
	 * @param PlayerID, ID to identify the player
	 */
	public Player(String name, int startingBalance, String color, int position, int playerID){
		this.name = name;
		this.account = new Account(startingBalance);
		this.isBroke = false; 
		this.position = position;
		this.playerID = playerID;
		this.numJailRolls = 0;
		this.numJailCards = 0;
		this.numDoubles = 0;
	}
	
	public int getNumDoubles() {
		return numDoubles;
	}

	public void setNumDoubles(int numDoubles) {
		this.numDoubles = numDoubles;
	}

	public String getName(){
		return name;
	}

	public int getBalance(){
		return this.account.getBalance();
	}

	/**
	 * Sets the player's position (1-40)
	 * @param position 1-40
	 */
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
	 * Withdraws an amount from the players balance. If under 0, player is set to broke
	 * @param value The amount to withdraw.
	 * @param setisBroke sets the player broke status to true or false 
	 * @see Player#depositBalance(int)
	 */
	public void withdrawBalance(int value){
		 try {
			this.account.withdraw(value);
		} catch (NotEnoughMoneyException e) {
			this.account.setBalance(0);
			this.isBroke = true;
		}
	}
	
	/**
	 * Tells if a player has lost the game 
	 * @param isBroke, true/false
	 */
	
	public void setIsBroke(boolean isBroke){
		this.isBroke = isBroke;
	}
	
	public boolean isBroke(){
		return this.isBroke;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getPlayerID(){
		return playerID;
	}
	
	public boolean isInJail() {
		return isInJail;
	}

	public void setInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}

	public int getPosition() {
		return position;
	}
	
	public int getNumJailCards(){
		return numJailCards;
	}
	
	public void increaseNumJailCards(){
		numJailCards++;
	}
	public void decreaseNumJailCards() {
		numJailCards--;
	}
	
	/**
	 * Moves the player.
	 * @param places The number of places to move. Can be negative.
	 */
	public void move(int places){
		this.position += places;
	}
	
	public void setNumJailRolls(int i){
		this.numJailRolls = i;
	}
	
	public int getNumJailRolls(){
		return this.numJailRolls;
	}
}
