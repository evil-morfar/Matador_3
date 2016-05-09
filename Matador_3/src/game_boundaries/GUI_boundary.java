package game_boundaries;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_fields.Field;
import desktop_resources.GUI;
import game_entities.Player;



public class GUI_boundary implements Interface {

	private String[] GUIarray;
	
	public GUI_boundary (String filename){
		BoundaryReader r = new BoundaryReader();
		GUIarray = r.getGUIText();		
	}
		
	@Override
	public void update(int pos, int balance, String playerName) {
		// TODO Auto-generated method stub
		
		GUI.setCar(pos, playerName);
		GUI.setBalance(playerName, balance);
	}

	/*************************************************************
	 * sets the dices on the GUI to what the player has rolled
	***************************************************************/
	
	@Override
	public void setDice(int[] dice) {
	GUI.setDice(dice[0], dice[1]);

	}	

	/************************************************************************
	 * Adds a new player to the board. using balance, name, and player #	*
	 * 																		*
	 * @param playerName Name of player which should be added to the board	*
	 * @param balance Starting balance of player							*
	 * @param playerNumber 0-5, otherwise error will occur					*
	 ***********************************************************************/
	
	@Override
	public void addPlayer(String playerName, int balance, int playerID) {
		Color[] colors = {Color.BLUE, Color.WHITE, Color.MAGENTA, Color.YELLOW, Color.BLACK, Color.GREEN};
	
		Car car = new Car.Builder()
				.primaryColor(colors[playerID-1])
				.secondaryColor(colors[5-(playerID-1)]).build();
		GUI.addPlayer(playerName, balance, car);
		GUI.setCar(1, playerName);
	}

	@Override
	public void removeAllOwners() {
		int i = 0; 
		while(i < 40){
		
			GUI.removeOwner(i);
			i++;
		}	
	}

	@Override
	public void removeOwner(int fieldNumber) {
		GUI.removeOwner(fieldNumber);
	}

	@Override
	public void showWelcome() {
		GUI.showMessage(String.format(GUIarray[1]));
	}

	@Override
	public void showStartingPlayer(Player player) {
		GUI.showMessage(String.format(GUIarray[4],player));
	}

	@Override
	public void showWinner(String player) {
		GUI.showMessage(String.format(GUIarray[5],player));
	}

	@Override
	public void showTransferMessage(Player player, String fieldName, Player owner, int amount) {
		GUI.showMessage(String.format(GUIarray[12], player.getName(), fieldName, amount, owner.getName()));
	}

	@Override
	public void showNotEnoughBalanceMessage(Player player) {
		GUI.showMessage(String.format(GUIarray[14], player));
	}

	@Override
	public void showBrokeMessage(Player player) {
		GUI.showMessage(String.format(GUIarray[6], player));
	}

	@Override
	public void showNotBoughtMessage(Player player) {
		GUI.showMessage(String.format(GUIarray[10], player));
	}

	@Override
	public void showFieldBoughtMessage(String playerName, String fieldName, int amount) {
		GUI.showMessage(String.format(GUIarray[9], playerName, fieldName, amount));
	}

	/**
	 * returns a message. If the player has fulfilled the error condition he gets an error message 
	 * @message the message to be displayed at the GUI
	 * @return the message to be printed out on the GUI
	 */
	@Override
	public String promptPlayerName(int playerNumber, boolean error) {
		String message;

		// Checks if there was an error last time a player name was entered
		if(error)
			message = GUIarray[3];
		else
			message = String.format(GUIarray[2], playerNumber);
		return GUI.getUserString(message);
	}

	@Override
	public boolean promptBuy(Player player, String fieldName, int price) {
		String msg = String.format(GUIarray[8], player, fieldName, price);
		return GUI.getUserLeftButtonPressed(msg, "yes", "no");
	}

	@Override
	public void movePlayer(int fieldNum, String playerName) {
		GUI.removeAllCars(playerName);
		GUI.setCar(fieldNum, playerName);		
	}

	@Override
	public void updateBalance(Player player) {
		GUI.setBalance(player.getName(), player.getBalance());		
	}

	@Override
	public void setOwner(int fieldNumber, String playerName) {
		GUI.setOwner(fieldNumber, playerName);		
	}
	
	@Override
	public String getUserButtonPressed(String msg, String... button1){
		return GUI.getUserButtonPressed(msg, button1);
	}

	@Override
	public void create(Field[] fields){
		GUI.create(fields);
	}

	@Override
	public void showMessage(String message){
		GUI.showMessage(message);
	}
	
	@Override
	public void setHouses(int fieldNumber, int houseCount){
		GUI.setHouses( fieldNumber, houseCount);
	}
	
	@Override
	public void setHotel(int fieldNumber, boolean hasHotel){
		GUI.setHotel( fieldNumber, hasHotel);
	}

	@Override
	public void showPoor(Player player) {
		GUI.showMessage(String.format(GUIarray[29], player));	
	}

	@Override
	public void showNotPoor(Player player) {
		GUI.showMessage(String.format(GUIarray[30], player));	
	}

	@Override
	public void showCard(String message) {
		GUI.displayChanceCard(message);
	}

	@Override
	public String buildSelection(String message, String... options) {
	return GUI.getUserSelection(message, options);
	}
	
	@Override
	public void showNotEnoughBalanceToBuild(String playername) {
		GUI.showMessage(String.format(GUIarray[31], playername));
	}
	
	public String getUserString(String msg) {
		return GUI.getUserString(msg);
	}
	
	public String getUserSelection(String msg, String... options){
		return GUI.getUserSelection(msg, options);
	}
}
