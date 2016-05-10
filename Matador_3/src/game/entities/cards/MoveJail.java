package game.entities.cards;

import game.controller.MainController;
import game.entities.Player;

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
