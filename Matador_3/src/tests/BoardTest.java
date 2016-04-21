/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fields.*;
import game_entities.Board;
import game_entities.Player;

/**
 * @author Nichlas N. Pilemand
 *
 */
public class BoardTest {
	private AbstractFields[] fields;
	private Board board;
	private Player p1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//TODO @Before is before every test
		board = new Board();
		fields = board.getFields();
		p1 = new Player("p1", 3000, "Blue", 0, 1);
		printFields();
	}
	
	public void printFields(){
		for (AbstractFields field : fields)
			System.out.println(field.toString());		
	}

	@Test
	public void testFields() {
		assertEquals("Start", fields[0].getName());
		assertEquals("Rådhuspladsen", fields[39].getName());
		assertEquals(1200, ((AbstractOwnable) fields[1]).getPrice());
		assertEquals(40, fields.length);
		assertEquals(0, ((Territory) fields[3]).getNumHouses());
	}
	
	@Test
	public void testFieldToString() {
		assertEquals("Field #1(Empty), name = Start", fields[0].toString());
	}
	
	
	@Test
	public void testGetNumOwnedSameColor(){
		p1 = new Player("p1", 3000, "Blue", 0, 1);
		((AbstractOwnable) fields[1]).setOwner(p1);
		assertEquals(1, board.getNumOwnedSameColor(p1, "Blue"));
		((AbstractOwnable) fields[3]).setOwner(p1);
		assertEquals(2, board.getNumOwnedSameColor(p1, "Blue"));
	}
	
	@Test
	public void testGetNumOwnedShips() {
		p1 = new Player("p1", 3000, "Blue", 0, 1);
		for (AbstractFields field : fields)
			if(field instanceof Shipping)
				((AbstractOwnable) field).setOwner(p1);
		assertEquals(4, board.getNumOwnedShips(p1));
	}
	
	@Test
	public void testGetNumOwnedBreweries(){
		for(AbstractFields field : fields)
			if(field instanceof Brewery)
				((AbstractOwnable) field).setOwner(p1);
		assertEquals(2, board.getNumOwnedBreweries(p1));
	}
	
	@Test
	public void testGetNumOwnedFields(){
		// TODO Doesn't work yet, @see @Before
		System.out.println("Total owned: "+board.getNumOwnedFields(p1));
	}

}
