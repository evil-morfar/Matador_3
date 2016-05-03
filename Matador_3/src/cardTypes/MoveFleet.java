package cardTypes;

import game_controller.MainController;

public class MoveFleet extends SuperCard {

	private final static String CARD_TYPE = "MoveFleet";


	public MoveFleet(int cardNumber, String cardText) {
		super(cardNumber, CARD_TYPE, cardText);	
	}

	@Override
	public void triggerCard(MainController controller) {
		// has to double rent if fleet field is owned 

	}

}
