package fields;

import game_entities.Player;

/**
 * NonOwnable Field that moves a player to the jail when landed on.
 * @author Nichlas N. Pilemand
 */
public class GoToJail extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "GoToJail";

	/**
	 * Creates the NonOwnable Jail Field. 
	 * @param id The id of the field.
	 * @param name Name of the Field
	 */
	public GoToJail(int id, String name) {
		super(id, name, FIELD_TYPE);
	}

	@Override
	/**
	 * @param player The player to move to the jail.
	 * @see GoToJail
	 */
	public void landOnField(Player player) {
		player.setJail(true);
		
		
	}

}
