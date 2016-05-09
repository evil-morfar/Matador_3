package database;

import java.util.ArrayList;

import fields.AbstractOwnable;
import game_entities.Board;
import game_entities.Player;

public interface DatabaseAccess {
	
	public boolean hasConnection();

	public void addNewPlayer(int id, String name, int account, int jailcards, String color, int position);
	
	public int createNewGame(String name, int currentPlayer);
	
	public void saveGame(int currentPlayer, Board board);
	
	public void savePlayer(Player player);
	
	public void updateView(int gameID);
	
	public void setGameID(int id);
	
	public void saveField(AbstractOwnable field);
	
	public int getNumHousesLeft();
	
	public int getNumHotelsLeft();
	
	public ArrayList<String> getSavedGames();
	
	public ArrayList<Player> getPlayersFromGame(int id);
}
