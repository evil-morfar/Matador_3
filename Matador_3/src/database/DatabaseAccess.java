package database;

import game_entities.Board;
import game_entities.Player;

public interface DatabaseAccess {

	public void addNewPlayer(int id, String name, int account, int jailcards, String color, int position);
	
	public void createNewGame(int id, String name, int currentPlayer);
	
	public void saveGame(int currentPlayer, Player[] players, Board board);
	
	public void updateView(int gameID);
}