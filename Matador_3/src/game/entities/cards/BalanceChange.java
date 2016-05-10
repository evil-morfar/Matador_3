package game.entities.cards;

import game.controller.MainController;
import game.entities.Player;

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
		else if (amount==0) {
			// has to withdraw 200 from every player except card drawer and give it to card drawer 
			
			
		}
		else player.depositBalance(amount);
	}

	
}
