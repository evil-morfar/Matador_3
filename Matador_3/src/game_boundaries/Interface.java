package game_boundaries;

import desktop_fields.Field;
import game_entities.Player;

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
	
	public void setDice(int[] dice);

	public void update(int pos, int balance, String playerName);

	public void addPlayer(String playerName, int balance, int playerNo);

	public void removeAllOwners();

	public void removeOwner(int fieldNumber);
	
	public void movePlayer(int fieldNum, String playerName);
	
	public void showWelcome();
	
	public void showStartingPlayer(Player player);
	
	public void showWinner(String playername);
	
	public void showTransferMessage(String playerName, String fieldName, String ownerName, int amount);
	
	public void showNotEnoughBalanceMessage(String playerName);
	
	public void showNotEnoughBalanceToBuild(String playername);
	
	public void showBrokeMessage(String playerName);
	
	public void showFieldBoughtMessage(String playerName, String fieldName, int amount);
	
	public String promptPlayerName(int playerNumber, boolean error);
	
	public void updateBalance(String playerName, int playerBalance);
	
	public void setOwner(int fieldNumber, String playerName);

	public String getUserButtonPressed(String msg, String... button1);

	public void create(Field[] fields);

	public void showMessage(String message); 
	
	public void setHouses(int fieldNumber, int houseCount);
		
	public void setHotel(int fieldNumber, boolean hasHotel);
	
	public void showPoor(String playerName);
	
	public void showNotPoor(String playerName);
	
	public void showCard (String message);
	
	public String getUserString(String msg);
	
	public String getUserSelection(String msg, String... options);	public String buildSelection(String message, String... options);
}
