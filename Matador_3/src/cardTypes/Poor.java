package cardTypes;

import game_controller.MainController;
import game_entities.Player;

/**
 * 
 * @author Henrik
 *
 */

public class Poor extends SuperCard {

	private final static String CARD_TYPE = "JailCard";

	public Poor(int cardNumber, String cardText) {
		super(cardNumber, CARD_TYPE, cardText);

	}

	
	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		// if the players total owned field value and current balance is under 15000 he is granted 40000
		if (controller.getBoard().getValueOfFields(player)+player.getBalance()<15000) {
			player.depositBalance(40000);
			controller.getGUI().showPoor(player);
		}
		else {
			// if players total owned field value and current balance is above 15000 nothing happens
			controller.getGUI().showNotPoor(player);
		}
	}
}
