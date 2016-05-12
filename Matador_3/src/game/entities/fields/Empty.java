package game.entities.fields;

import game.controller.MainController;
/**
 * An Empty NonOwnable Field for a Monopoly game.
 * @author Nichlas
 */
public class Empty extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Empty";

	/**
	 * Create an Empty NonOwnable Field. Needed for fields that doesn't do
	 * anything :)
	 * @param id The id of the field.
	 * @param name The name of the field
	 */
	public Empty(int id, String name) {
		super(id, name, FIELD_TYPE);
	}

	
	/**
	 * Nothing's supposed to happen here!
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	@Override 
	public void landOnField(MainController controller) {
		// Intentionally left blank!
	}

}
