package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import game.database.DatabaseAccess1;
import game.entities.Board;
import game.entities.Player;
import game.entities.fields.AbstractOwnable;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {
	
	private static DatabaseAccess1 db;
	private static boolean hasCon;
	private int game_id;
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
	public void testCreateGame(){
		if (hasCon) 
			game_id = db.createNewGame("New Test Game", 1);
		
	}
	
	@Test 
	public void testSavePlayer(){
		if(hasCon) {
			Player p = new Player("Test player", 20000, "blue", 1, 1);
			db.setGameID(game_id);
			db.savePlayer(p);
		}
	}

	@Test
	public void testAddPlayer() {
		if(hasCon) {
			game_id = db.createNewGame("New Test Game", 1);
			db.setGameID(game_id);
			db.addNewPlayer(1, "testName", 30000, 0, "green", 1);
		}
	}
	
	@Test
	public void testSaveGame(){
		db.setGameID(1);
		db.saveGame(1, board);
	}

	@Test
	public void testSaveField(){
		if(hasCon){
			game_id = db.createNewGame("Another Test game", 1);
			db.setGameID(game_id);
			db.saveField((AbstractOwnable) board.getFields()[1]);
			
		}
	}
	
	@Test
	public void testBank(){
		if(hasCon){
			game_id = db.createNewGame("Yet Another Test game", 1);
			db.setGameID(game_id);
			assertEquals(32, db.getNumHousesLeft());
			assertEquals(12, db.getNumHotelsLeft());
		}
	}
}
