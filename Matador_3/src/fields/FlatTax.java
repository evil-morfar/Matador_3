package fields;

import desktop_codebehind.Player;

/**
 * The NonOwnable Field, FlatTax. There's only 1 of these on a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class FlatTax extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Tax";
	private int amount;

	/**
	 * Creates an object of the FlatTax type. The tax to be payed is a flat amount.
	 * @param name The name of the Field.
	 * @param amount The amount to pay.
	 */
	public FlatTax(String name, int amount) {
		super(name, FIELD_TYPE);
		this.amount = amount;
	}

	@Override
	/**
	 * Forces the player landing on the field to pay the amount in taxes.
	 * @param player The player landing on the field.
	 */
	public void landOnField(Player player) {
		player.changeBalance(-this.amount);
		//TODO consider error handling.
	}

}
