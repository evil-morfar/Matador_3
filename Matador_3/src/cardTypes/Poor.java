package cardTypes;

import game_controller.MainController;
import game_entities.Player;

public class Poor extends SuperCard {

	private final static String CARD_TYPE = "JailCard";
	
	public Poor(int cardNumber, String cardText) {
		super(cardNumber, CARD_TYPE, cardText);

	}

	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		// if the players total owned field value and curent balance is under 15000 he is granted 40000
		if (controller.getBoard().getValueOfFields(player)+player.getBalance()<15000) {
			player.depositBalance(40000);
			output.showPoor(player);
		}
		else {
			
			controller.getGUI().showPoor(player);
			
		}
		
	}

}
