package fields;

import desktop_codebehind.Player;

/**
 * The NonOwnable Field, Chance. Several of these exists in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class Chance extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Chance";

	/**
	 * Creates a NonOwnable Field of type Chance. Used to give random cards
	 * to players during a game.
	 */
	public Chance() {
		// Name and fieldType is the same.
		super(FIELD_TYPE, FIELD_TYPE);
	}

	/**
	 * Draws and executes a chance card on the player landing on the Field.
	 * @param player The player landing on the Field.
	 */
	@Override
	public void landOnField(Player player) {
		// TODO Draw and execute a chance card on the player

	}

}
