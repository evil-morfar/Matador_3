/**
 * The NonOwnable Field, Tax. There's normally 1 of these in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */

package fields;

import desktop_codebehind.Player;

public class Tax extends AbstractNonOwnables {
	
	private static final String FIELD_TYPE = "Tax";
	private int amount;
	private int percent;

	/**
	 * Creates a NonOwnable Field of type Tax. This field requires a player to pay
	 * a percentage of their balance, or a flat amount.
	 * @param name Name of the Field.
	 * @param amount The flat amount to pay.
	 * @param percent The percentage of a player balance to pay.
	 */
	public Tax(String name, int amount, int percent) {
		super(name, FIELD_TYPE);
		this.amount = amount;
		this.percent = percent;
	}

	/**
	 * Provides the choice between paying a flat amount, or a percentage of
	 * the players current balance.
	 * @param player The player landing on the Field.
	 */
	@Override 
	public void landOnField(Player player) {
		// TODO The player must choose between paying this.amount or
		// player.getBalance() / this.percent

	}

}
