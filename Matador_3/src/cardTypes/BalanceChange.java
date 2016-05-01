package cardTypes;

import game_controller.MainController;
import game_entities.Player;

public class BalanceChange extends SuperCard {

	
	private int amount;
	
	public BalanceChange(int cardNumber, String cardType, String cardText, int amount) {
		super(cardNumber, cardType, cardText);
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
