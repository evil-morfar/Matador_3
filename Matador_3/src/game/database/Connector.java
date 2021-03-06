package game.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** @author Ronnie Dalsgaard */
public class Connector {
	private final String HOST 	  = "Localhost";
	private final int	 PORT 	  = 3306;
	private final String DATABASE = "matadordb";
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private Connection connection;

	public Connector() throws NoDbConnectionError{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			System.out.println("Connected to DB");
		} catch (ClassNotFoundException | SQLException e) {
			throw new NoDbConnectionError();
		}
		
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	public ResultSet doQuery(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(query);
		return res;
	}
	
	public void doUpdate(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query);
	}
}
