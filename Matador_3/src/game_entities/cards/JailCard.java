package game_entities.cards;

import game_controller.MainController;
import game_entities.Player;

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
