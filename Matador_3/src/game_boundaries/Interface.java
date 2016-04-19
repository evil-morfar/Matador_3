package game_boundaries;

/**
 * @author Henrik
 */

// udkast til hvilke metoder vi skal have til vores GUI boundary 

/******************************************************************* * 
 * 
 * Interface defining methods for input output to screen from game
 * 
 ********************************************************************/

public interface Interface {

	public void update(int pos, int balance, String playerName);

	public void addPlayer(String playerName, int balance, int playerNo);

	public void removeAllOwners();

	public void removeOwner(int fieldNumber);

	public void showUpdateMessage(String playerName, int pos);
	
	public void showWelcome();
	
	public void showStartingPlayer(String playerName);
	
	public void showWinner(String playerName);
	
	public void showWithdrawMessage(String playerName, int amount);
	
	public void showDepositMessage(String playerName, int bonus);
	
	public void showTransferMessage(String playerName, String ownerName, int amount);
	
	public void showNotEnoughBalanceMessage(String playerName);
	
	public void showBrokeMessage(String playerName);
	
	public void showNotBoughtMessage(String playerName);
	
	public void showFieldBoughtMessage(String playerName, int fieldNumber);
	
	public void showRollingDiceForRent(String playeName);
	
	public void showPlayerIsOwner(String playerName);
	
	public String promptPlayerName(int playerNumber, boolean error);
	
	public void promptRollDice(String playerName);
	
	public boolean promptTax();
	
	public boolean promptBuy(String name, int price);
	
	public void initializeBoard();
	
}
