package cardTypes;

import game_controller.MainController;
import game_entities.Player;

public class BalanceChange extends SuperCard {

	
	private int amount;
	private final static String CARD_TYPE = "BalanceChange";
	
	/**
	 * 
	 * @param cardNumber number of the card
	 * @param cardType which category the card falls under
	 * @param cardText Text of the card 
	 * @param amount The amount the player receives or pays
	 */
	public BalanceChange(int cardNumber, String cardText, int amount) {
		super(cardNumber, CARD_TYPE, cardText);
		this.amount = amount;
		
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		if (amount<0) {
			// multiplies with -1 so it withdraws properly, if it withdraws a negative value the player would be given money instead
		player.withdrawBalance(amount*(-1));
		}
		else player.depositBalance(amount);
	}

	
}