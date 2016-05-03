package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import database.Connector;
import database.DatabaseAccess1;

public class DatabaseTest {

	private static DatabaseAccess1 db;
	private int game_id;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		db = new DatabaseAccess1();
	}
	
	@Test
	public void testCreateGame(){
		game_id = db.createNewGame("New Test Game", 1);
	}

	@Test
	public void testAddPlayer() {
		db.setGameID(game_id);
		db.addNewPlayer(1, "testName", 30000, 0, "green", 1);
	}

}
