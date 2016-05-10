package game_entities.fields;

import game_controller.MainController;
import game_entities.cards.SuperCard;


/**
 * The NonOwnable Field, Chance. Several of these exists in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class Chance extends AbstractNonOwnables {

	private static final String FIELD_TYPE = "Chance";


	/**
	 * Creates a NonOwnable Field of type Chance. Used to give random cards
	 * to players during a game.
	 * @param id The id of the field.
	 */
	public Chance(int id) {
		// Name and fieldType is the same.
		super(id, FIELD_TYPE, FIELD_TYPE);

	}
	
	/**
	 * Draws and executes a chance card on the player landing on the Field.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	@Override
	public void landOnField(MainController controller) {
		SuperCard drawn = controller.getCardCreater().drawCard();
		controller.getGUI().showCard(drawn.getCardText());
		drawn.triggerCard(controller);

	}



}
