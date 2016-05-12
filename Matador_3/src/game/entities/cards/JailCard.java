package game.entities.cards;

import game.controller.MainController;
import game.entities.Player;

/**
 * 
 * @author Henrik
 *
 */

public class JailCard extends SuperCard {

	private final static String CARD_TYPE = "JailCard";
	
	public JailCard(int cardNumber, String cardText) {
		super(cardNumber, CARD_TYPE, cardText);
		
	}

	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		player.increaseNumJailCards();
	}

}
