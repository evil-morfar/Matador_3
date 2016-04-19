/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fields.AbstractFields;
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
		AbstractFields[] fields = b.getFields();
	}

	@Test
	public void testFields() {
		assert(fields[1].getName().equals("Start"));
		assert(fields[40].getName().equals("Rådhuspladsen"));
		assert(((Territory) fields[2]).getPrice() == 1200);
	}

}
