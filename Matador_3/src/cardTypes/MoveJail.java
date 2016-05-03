package cardTypes;

import game_controller.MainController;
import game_entities.Player;

public class MoveJail extends SuperCard {

	private final static String CARD_TYPE = "MoveJail";

	public MoveJail(int cardNumber, String cardText) {
		super(cardNumber, CARD_TYPE, cardText);
	}

	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		player.setInJail(true);
		controller.movePlayerTo(player, 11);
		controller.endTurn();

	}

}
