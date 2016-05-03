package database;

import java.sql.CallableStatement;
import java.sql.SQLException;

import game_entities.Board;
import game_entities.Player;

public class DatabaseAccess1 implements DatabaseAccess {

	private int game_id;
	private Connector c;
	
	public DatabaseAccess1() {
		this.c = new Connector();
	}

	@Override
	public void addNewPlayer(int id, String name, int account, int jailcards, String color, int position) {
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

	@Override
	public int createNewGame(String name, int currentPlayer) {
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
		return -1;	//hopefully newer happens

	}

	@Override
	public void savePlayer(Player player) {
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
		// TODO Auto-generated method stub
		
	}

}
