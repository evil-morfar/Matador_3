package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import database.Connector;
import database.DatabaseAccess1;
import game_entities.Player;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void testSavePlayer(){
		Player p = new Player("Test player", 20000, "blue", 1, 1);
		db.savePlayer(p);
	}

	@Test
	public void testAddPlayer() {
		game_id = db.createNewGame("New Test Game", 1);
		db.setGameID(game_id);
		db.addNewPlayer(1, "testName", 30000, 0, "green", 1);
	}

}
