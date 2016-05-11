package game.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import game.entities.Board;
import game.entities.Player;
import game.entities.fields.AbstractOwnable;
import game.entities.fields.Territory;

public class DatabaseAccess1 implements DatabaseAccess {

	private int game_id;
	private boolean hasConnection;
	private Connector c;

	public DatabaseAccess1() {
		try {
			this.c = new Connector();
			hasConnection = true;
		} catch (NoDbConnectionError e) {
			hasConnection = false;
		}
	}

	public boolean hasConnection(){
		return hasConnection;
	}

	/**
	 * Adds a new player to the Database
	 * @see Player#Player Player() for param menings.
	 */
	@Override
	public void addNewPlayer(int id, String name, int account, int jailcards, String color, int position) {
		if(hasConnection){
			try {
				CallableStatement stmt = c.getConnection().prepareCall(
						"{call CreatePlayer(?,?,?,?,?,?,?)}");
				stmt.setInt(1, id);
				stmt.setInt(2, this.game_id);
				stmt.setString(3, name);
				stmt.setInt(4, account);
				stmt.setInt(5, jailcards);
				stmt.setString(6, color);
				stmt.setInt(7,position);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Creates a new game in the DB.
	 * @param name The game's name.
	 * @param currentPlayer The ID of the player whose turn it is.
	 * @return The game_id of the newly created game.
	 */
	@Override
	public int createNewGame(String name, int currentPlayer) {
		if(hasConnection){
			try {
				CallableStatement stmt = c.getConnection().prepareCall(
						"{call CreateGame(?,?,?)}");
				stmt.setString(1, name);
				stmt.setInt(2, currentPlayer);
				stmt.registerOutParameter(3, java.sql.Types.INTEGER);
				stmt.executeUpdate();
				return stmt.getInt(3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * Extracts all relevant information from the Player and saves it 
	 * to the Database.
	 * @param player The player to save.
	 */
	@Override
	public void savePlayer(Player player) {
		if(hasConnection){
			try {
				CallableStatement stmt = c.getConnection().prepareCall(
						"{call SavePlayer(?,?,?,?,?,?,?,?)}");
				stmt.setInt(1, this.game_id);
				stmt.setInt(2, player.getPlayerID());
				stmt.setInt(3, player.getBalance());
				stmt.setInt(4, player.getNumJailCards());
				stmt.setInt(5, player.getPosition());
				stmt.setInt(6,  player.getNumDoubles());
				stmt.setInt(7, player.getNumJailRolls());
				stmt.setBoolean(8, player.isInJail());
				stmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updates the gameID the class should operate on
	 */
	public void setGameID(int id){
		this.game_id = id;
	}

	/**
	 * Saves the current game.
	 * @param currentPlayer The ID of the player whose turn it is.
	 * @param board The game board //TODO (currently unused)
	 */
	@Override
	public void saveGame(int currentPlayer) {
		if(hasConnection){
			try {
				CallableStatement stmt = c.getConnection().prepareCall(
						"{call SaveGame(?,?)}");
				stmt.setInt(1, this.game_id);
				stmt.setInt(2, currentPlayer);
				stmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves all relevant information from a field to the DB.
	 * @param field The AbstractOwnable field to save.
	 */
	public void saveField(AbstractOwnable field){
		if(hasConnection){
			try {
				CallableStatement stmt = c.getConnection().prepareCall(
						"{call SaveField(?,?,?,?,?)}");
				stmt.setInt(1,  field.getFieldID());
				stmt.setInt(2, this.game_id);
				if(field.isOwned())
					stmt.setInt(3, field.getOwner().getPlayerID());
				else
					stmt.setNull(3, 1);
				if (field instanceof Territory) {
					stmt.setInt(4, ((Territory) field).getNumHouses());
					stmt.setInt(5, ((Territory) field).hasHotel() ? 1 : 0);
				} else {
					stmt.setNull(4, 1);
					stmt.setNull(5, 1);
				}
				stmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * @return The number of houses left in the bank
	 */
	public int getNumHousesLeft(){
		if(hasConnection){
			ResultSet r;
			try {
				r = c.doQuery("select houses_left from bank where game_id = "+this.game_id);				
				r.next();
				return r.getInt("houses_left");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 10; //Just return a few, we can't check it 
		
	}
	
	/**
	 * @return The number of hotels left in the bank.
	 */
	public int getNumHotelsLeft(){
		if(hasConnection){
			ResultSet r;
			try {
				r = c.doQuery("Select hotels_left from bank where game_id = "+this.game_id);
				r.next();
				return r.getInt("hotels_left");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 10; //Just return a few, we can't check it 
	}

	/**
	 * Fetches the game names of all saved games.
	 * @return All the game names contained in an ArrayList<String>.
	 */
	@Override
	public ArrayList<String> getSavedGames() {
		if(hasConnection){
			ArrayList<String> res = new ArrayList<String>();
			ResultSet r;
			try {
				r = c.doQuery("select game_name from games");
				while(r.next()){
					res.add(r.getString("game_name"));
				}
				return res;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * Fetches all players from the DB with game_id = id using the DB View "fullplayer".
	 * Creates the players, and sets all their saved attributes.
	 * @param id The game_id to fetch players from
	 * @return ArrayList<Player> containing all the players.
	 */
	@Override
	public ArrayList<Player> getPlayersFromGame(int id) {
		if(hasConnection){
			ArrayList<Player> res = new ArrayList<Player>();
			ResultSet r;
			try {
				r = c.doQuery("select * from fullplayer where game_id = "+ id + ";");
				while(r.next()){
					Player p = new Player(r.getString("player_name"), r.getInt("account"),
							r.getString("color"), r.getInt("position"), r.getInt("player_id"));
					p.setNumJailCards(r.getInt("jailcards"));
					p.setInJail(r.getBoolean("isInJail"));
					p.setNumDoubles(r.getInt("num_doubles"));
					p.setNumJailRolls(r.getInt("jail_time"));
					p.setIsBroke(p.getBalance() == 0); // Don't forget to check if they're broke
					res.add(p);
				}
				return res;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

	/**
	 * @return The playerID of the current player in the current game.
	 */
	public int getCurrentPlayer() {
		ResultSet r;
		try {
			r = c.doQuery("select current_player from games where game_id = " +game_id);
			r.next();
			return r.getInt("current_player");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1; // default
	}
	
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
	public List<List<Integer>> getFieldsFromGame(int id){
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		int i = 0;
		ResultSet r;
		try {
			r = c.doQuery("select field_id, player_id, num_houses, num_hotels from player_properties where game_id = "+id);
			while(r.next()){
				ret.add(i, new	ArrayList<Integer>());
				ret.get(i).add(r.getInt("field_id"));
				ret.get(i).add(r.getInt("player_id"));
				ret.get(i).add(r.getInt("num_houses"));
				ret.get(i).add(r.getInt("num_hotels"));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;		
	}

}
	