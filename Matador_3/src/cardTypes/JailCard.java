package cardTypes;

import game_controller.MainController;

public class JailCard extends SuperCard {

	private final static String CARD_TYPE = "JailCard";
	
	public JailCard(int cardNumber, String cardText) {
		super(cardNumber, CARD_TYPE, cardText);
		
	}

	@Override
	public void triggerCard(MainController controller) {
		
		
	}

}
