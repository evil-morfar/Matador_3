package database;

import game_entities.Board;
import game_entities.Player;

public interface DatabaseAccess {

	public void addNewPlayer(int id, String name, int account, int jailcards, String color, int position);
	
	public int createNewGame(String name, int currentPlayer);
	
	public void saveGame(int currentPlayer, Board board);
	
	public void savePlayer(Player player);
	
	public void updateView(int gameID);
}
