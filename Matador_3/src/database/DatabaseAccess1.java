package database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fields.AbstractOwnable;
import fields.Territory;
import game_entities.Board;
import game_entities.Player;

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

	@Override
	public void updateView(int gameID) {
		// TODO Auto-generated method stub

	}

	public void setGameID(int id){
		this.game_id = id;
		updateView(id);
	}

	@Override
	public void saveGame(int currentPlayer, Board board) {
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

}
	