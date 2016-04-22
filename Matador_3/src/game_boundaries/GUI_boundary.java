package game_boundaries;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;

/**
 * 
 * @author Henrik
 *
 */

public class GUI_boundary implements Interface {

	@Override
	public void update(int pos, int balance, String playerName) {
		// TODO Auto-generated method stub



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

	}

	@Override
	public void removeOwner(int fieldNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showUpdateMessage(String playerName, int pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showWelcome() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showStartingPlayer(String playerName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showWinner(String playerName) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void showBrokeMessage(String playerName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showNotBoughtMessage(String playerName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showFieldBoughtMessage(String playerName, int fieldNumber) {
		// TODO Auto-generated method stub

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
	public boolean promptBuy(String name, int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initializeBoard() {
		// TODO Auto-generated method stub

	}





}
