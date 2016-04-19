/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fields.AbstractFields;
import fields.AbstractOwnable;
import fields.Territory;
import game_entities.Board;

/**
 * @author Nichlas N. Pilemand
 *
 */
public class BoardTest {
	private AbstractFields[] fields;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Board b = new Board();
		fields = b.getFields();
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

}
