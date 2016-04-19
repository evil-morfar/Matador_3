package fields;

import desktop_codebehind.Player;

/**
 * NonOwnable Field that moves a player to the jail when landed on.
 * @author Nichlas N. Pilemand
 */
public class GoToJail extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "GoToJail";

	/**
	 * Creates the NonOwnable Jail Field. 
	 * @param name Name of the Field
	 */
	public GoToJail(String name) {
		super(name, FIELD_TYPE);
	}

	@Override
	/**
	 * @param player The player to move to the jail.
	 * @see GoToJail
	 */
	public void landOnField(Player player) {
		// TODO Move player to jail

	}

}
