package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import game.database.DatabaseAccess1;
import game.entities.Board;
import game.entities.Player;
import game.entities.fields.Territory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {
	
	private static DatabaseAccess1 db;
	private static boolean hasCon;
	private static int game_id; //Apparently has to be static
	private static Board board;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = new Board();
		db = new DatabaseAccess1();
		hasCon = db.hasConnection();
		if(!hasCon)
			System.out.println("Tests won't run due to lack of DB connection");
	}
		
	@Test
	public void test1CreateGame(){
		if (hasCon) {
			DatabaseTest.game_id = db.createNewGame("New Test Game", 1);
			System.out.println("Created game with id: "+game_id);
			db.setGameID(game_id);
			assertEquals(1, db.getCurrentPlayer());
			assertTrue(db.getSavedGames().contains("New Test Game"));
		}
		
	}
	
	@Test
	public void testAddPlayer() {
		if(hasCon) {
			db.setGameID(game_id);
			db.addNewPlayer(1, "testName", 30000, 0, "green", 1);
		}
	}
	
	@Test 
	public void testSavePlayer(){
		if(hasCon) {
			db.setGameID(game_id);
			// Create a player and add it to the db
			Player p = new Player("Test player", 20000, "blue", 1, 2);
			db.addNewPlayer(2, "Test player", 20000, 0, "blue", 1);
			//Change a value
			p.withdrawBalance(1000);
			//p.getBalance() is now 19000, so save the player:
			db.savePlayer(p);
			ArrayList<Player> players = db.getPlayersFromGame(game_id);
			//Getting the player from the DB give equal values to the original player
			// (but of course it's not exactly the same, as it's a new object!)
			Player p2 = players.get(1);
			assertEquals(p.getBalance(), p2.getBalance());
			assertEquals(p.getName(), p2.getName());
			assertEquals(p.getColor(), p2.getColor());
		}
	}

	
	
	@Test
	public void testSaveGame(){
		db.saveGame(2);
		assertEquals(2, db.getCurrentPlayer());
	}

	@Test
	public void testSaveField(){
		if(hasCon){
			db.setGameID(game_id);
			Territory field =  (Territory) board.getFields()[1];
			field.setNumHouses(1);
			db.saveField(field);
			List<List<Integer>> fieldList = db.getFieldsFromGame(game_id);
			assertEquals(field.getFieldID(), (int)(fieldList.get(0)).get(0));
			assertEquals(field.getNumHouses(), (int)fieldList.get(0).get(2));			
		}
	}
	
	@Test
	public void testBank(){
		if(hasCon){
			db.setGameID(game_id);
			//The DB inits the game with 32 houses and 12 hotels
			assertEquals(32, db.getNumHousesLeft());
			assertEquals(12, db.getNumHotelsLeft());
		}
	}
}
