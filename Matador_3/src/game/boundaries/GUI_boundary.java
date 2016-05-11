package game.boundaries;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_fields.Field;
import desktop_resources.GUI;
import game.entities.Player;



public class GUI_boundary implements GUI_Interface {

	private String[] GUIarray;
	private final static String guiFileLoc = "src/game/boundaries/GUItext.csv";
	
	public GUI_boundary (){
		BoundaryReader r = new BoundaryReader(guiFileLoc);
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
	public void showWinner(String playerName) {
		GUI.showMessage(String.format(GUIarray[5],playerName));
	}

	@Override
	public void showTransferMessage(String playerName, String fieldName, String ownerName, int amount) {
		GUI.showMessage(String.format(GUIarray[12], playerName, fieldName, amount, ownerName));
	}

	@Override
	public void showNotEnoughBalanceMessage(String playerName) {
		GUI.showMessage(String.format(GUIarray[14], playerName));
	}

	@Override
	public void showBrokeMessage(String playerName) {
		GUI.showMessage(String.format(GUIarray[6], playerName));
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
	public void movePlayer(int fieldNum, String playerName) {
		GUI.removeAllCars(playerName);
		GUI.setCar(fieldNum, playerName);		
	}

	@Override
	public void updateBalance(String playerName, int playerBalance) {
		GUI.setBalance(playerName, playerBalance);		
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
	public void showPoor(String playerName) {
		GUI.showMessage(String.format(GUIarray[29], playerName));	
	}

	@Override
	public void showNotPoor(String playerName) {
		GUI.showMessage(String.format(GUIarray[30], playerName));	
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

	@Override
	public void removeCar(String playername) {
		GUI.removeAllCars(playername);		
	}
}
