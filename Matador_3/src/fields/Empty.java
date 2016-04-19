package fields;

import game_entities.Player;
/**
 * An Empty NonOwnable Field for a Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class Empty extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Empty";

	/**
	 * Create an Empty NonOwnable Field. Needed for fields that doesn't do
	 * anything :)
	 * @param id The id of the field.
	 */
	public Empty(int id) {
		super(id, FIELD_TYPE, FIELD_TYPE);
	}

	
	/**
	 * Nothing's supposed to happen here!
	 * @param player The player landing on the Field, whom nothing will
	 * happen to.
	 * @see Empty#Empty()
	 */
	@Override 
	public void landOnField(Player player) {
		// Intentionally left blank!
	}

}
