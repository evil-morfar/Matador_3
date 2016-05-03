package fields;

import game_controller.MainController;
/**
 * The NonOwnable Field, Tax. There's normally 1 of these in a standard Monopoly game.
 * @see Tax#Tax
 * @author Nichlas N. Pilemand
 */
public class Tax extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Tax";
	private int amount;
	private int percent;

	/**
	 * Creates a NonOwnable Field of type Tax. This field requires a player to pay
	 * a percentage of their balance, or a flat amount.
	 * @param id The id of the field.
	 * @param name Name of the Field.
	 * @param amount The flat amount to pay.
	 * @param percent The percentage of a player balance to pay.
	 */
	public Tax(int id, String name, int amount, int percent) {
		super(id, name, FIELD_TYPE);
		this.amount = amount;
		this.percent = percent;
	}

	/**
	 * Provides the choice between paying a flat amount, or a percentage of
	 * the players current balance.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	@Override
	public void landOnField(MainController controller) {
		// TODO The player must choose between paying this.amount or
		// player.getBalance() / this.percent

	}

}
