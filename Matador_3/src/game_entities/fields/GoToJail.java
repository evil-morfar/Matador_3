package game_entities.fields;

import game_controller.MainController;
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
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	public void landOnField(MainController controller) {
		Player player = controller.getCurrentPlayer();
		player.setPosition(11);
		player.setInJail(true);		
		controller.endTurn();
	}

}
