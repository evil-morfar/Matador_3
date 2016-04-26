package game_boundaries;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import game_entities.Player;
import game_controller.MainController;



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
				.primaryColor(colors[playerID])
				.secondaryColor(colors[5-playerID]).build();
		GUI.addPlayer(playerName, balance, car);
		GUI.setCar(1, playerName);
	
	}

	@Override
	public void removeAllOwners() {
		// TODO Auto-generated method stub
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
	public void showUpdateMessage(String playerName, int pos) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void showWelcome() {
		GUI.showMessage(String.format(GUIarray[1]));

	}

	@Override
	public void showStartingPlayer(String playerName) {
		GUI.showMessage(String.format(GUIarray[4],playerName));

	}

	@Override
	public void showWinner(String playerName) {
		GUI.showMessage(String.format(GUIarray[5],playerName));

	}

	@Override
	public void showWithdrawMessage(String playerName, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDepositMessage(String playerName, int bonus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTransferMessage(String playerName, String ownerName, int amount) {
		// TODO Auto-generated method stub

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
	
	public void showNotBoughtMessage(String playerName) {
		GUI.showMessage(String.format(GUIarray[10], playerName));

	}

	@Override
	public void showFieldBoughtMessage(String playerName, String fieldName, int amount) {
		GUI.showMessage(String.format(GUIarray[9], playerName, fieldName, amount));
	}

	@Override
	public void showRollingDiceForRent(String playeName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showPlayerIsOwner(String playerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String promptPlayerName(int playerNumber, boolean error) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void promptRollDice(String playerName) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean promptTax() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptBuy(String playerName, String fieldName, int price) {
		String msg = String.format(GUIarray[8], playerName, fieldName, price);
		return GUI.getUserLeftButtonPressed(msg, "yes", "no");
	}

	@Override
	public void initializeBoard() {
		// TODO Auto-generated method stub

	}


	@Override
	public void movePlayer(int fieldNum, String playername) {
		GUI.removeAllCars(playername);
		GUI.setCar(fieldNum, playername);		
	}


	@Override
	public void updateBalance(Player player) {
		GUI.setBalance(player.getName(), player.getBalance());		
	}


	@Override
	public void setOwner(int fieldNumber, String playername) {
		GUI.setOwner(fieldNumber, playername);		
	}





}
