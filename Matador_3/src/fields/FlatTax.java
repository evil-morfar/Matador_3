package fields;

import game_controller.MainController;
import game_entities.Player;

/**
 * The NonOwnable Field, FlatTax. There's only 1 of these on a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class FlatTax extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Tax";
	private int amount;

	/**
	 * Creates an object of the FlatTax type. The tax to be payed is a flat amount.
	 * @param id The id of the field.
	 * @param name The name of the Field.
	 * @param amount The amount to pay.
	 */
	public FlatTax(int id, String name, int amount) {
		super(id, name, FIELD_TYPE);
		this.amount = amount;
	}

	@Override
	/**
	 * Forces the player landing on the field to pay the amount in taxes.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	public void landOnField(MainController controller) {
		player.withdrawBalance(this.amount);
		//TODO consider error handling.
	}

}
