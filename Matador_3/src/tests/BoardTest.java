/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import game.entities.Board;
import game.entities.Player;
import game.entities.fields.AbstractFields;
import game.entities.fields.AbstractOwnable;
import game.entities.fields.Brewery;
import game.entities.fields.Shipping;
import game.entities.fields.Territory;

/**
 * @author Nichlas
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
		((AbstractOwnable) fields[1]).setOwner(p1);
		((AbstractOwnable) fields[3]).setOwner(p1);
		assertEquals(2, board.getNumOwnedFields(p1));
		((AbstractOwnable) fields[1]).clearOwner();
		assertEquals(1, board.getNumOwnedFields(p1));
	}
	
	@Test
	public void testOwner(){
		assertNotEquals(((AbstractOwnable) fields[1]).getOwner(), p1);
		assertFalse(((AbstractOwnable) fields[1]).getOwner() == p1);
	}

}
