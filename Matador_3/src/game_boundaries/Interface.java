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
	
	public void showWinner(Player player);
	
	public void showTransferMessage(Player player, String fieldName, Player owner, int amount);
	
	public void showNotEnoughBalanceMessage(Player player);
	
	public void showBrokeMessage(Player player);
	
	public void showNotBoughtMessage(Player player);
	
	public void showFieldBoughtMessage(String playerName, String fieldName, int amount);
	
	public void showPlayerIsOwner(Player player);
	
	public String promptPlayerName(int playerNumber, boolean error);
	
	public boolean promptBuy(Player player, String fieldName, int price);
	
	public void updateBalance(Player player);
	
	public void setOwner(int fieldNumber, String playerName);

	public String getUserButtonPressed(String msg, String... button1);

	public void create(Field[] fields);

	public void showMessage(String message); 
	
	public void setHouses(int fieldNumber, int houseCount);
		
	public void setHotel(int fieldNumber, boolean hasHotel);
	
	public void showPoor(Player player);
	
	public void showNotPoor(Player player);
	
	public void showCard (String message);
	
	public String getUserString(String msg);
	
	public String getUserSelection(String msg, String... options);
}
