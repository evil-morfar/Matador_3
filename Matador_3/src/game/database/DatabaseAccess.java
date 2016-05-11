package game.database;

import java.util.ArrayList;
import java.util.List;

import game.entities.Board;
import game.entities.Player;
import game.entities.fields.AbstractOwnable;

public interface DatabaseAccess {
	
	public boolean hasConnection();

	/**
	 * Adds a new player to the Database
	 * @see Player#Player Player() for param menings.
	 */
	public void addNewPlayer(int id, String name, int account, int jailcards, String color, int position);
	
	/**
	 * Creates a new game in the DB.
	 * @param name The game's name.
	 * @param currentPlayer The ID of the player whose turn it is.
	 * @return The game_id of the newly created game.
	 */
	public int createNewGame(String name, int currentPlayer);

	/**
	 * Saves the current game.
	 * @param currentPlayer The ID of the player whose turn it is.
	 */
	public void saveGame(int currentPlayer);

	/**
	 * Extracts all relevant information from the Player and saves it 
	 * to the Database.
	 * @param player The player to save.
	 */
	public void savePlayer(Player player);

	/**
	 * Updates the gameID the class should operate on
	 */
	public void setGameID(int id);

	/**
	 * Saves all relevant information from a field to the DB.
	 * @param field The AbstractOwnable field to save.
	 */
	public void saveField(AbstractOwnable field);

	/**
	 * @return The number of houses left in the bank
	 */
	public int getNumHousesLeft();

	/**
	 * @return The number of hotels left in the bank.
	 */
	public int getNumHotelsLeft();
	
	/**
	 * Fetches the game names of all saved games.
	 * @return All the game names contained in an ArrayList<String>.
	 */
	public ArrayList<String> getSavedGames();
	
	/**
	 * Fetches all players from the DB with game_id = id using the DB View "fullplayer".
	 * Creates the players, and sets all their saved attributes.
	 * @param id The game_id to fetch players from
	 * @return ArrayList<Player> containing all the players.
	 */
	public ArrayList<Player> getPlayersFromGame(int id);

	/**
	 * Fetches saved fields from the DB on a given game.
	 * Example to get num_hotels from the 2 row: num_hotels = ret.get(1).get(3);
	 * @param id The game_id in the DB to fetch saved fields from
	 * @return A 2 dim integer List where each field is represented as the first row,
	 * and the retrieved data in the second list, in the following order:
	 * 		field_id,
	 * 		player_id,
	 * 		num_houses,
	 * 		num_hotels.	 		
	 */
	public List<List<Integer>> getFieldsFromGame(int gameID);
	/**
	 * @return The playerID of the current player in the current game.
	 */
	public int getCurrentPlayer();
}
