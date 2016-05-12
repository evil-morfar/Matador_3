package game.entities.cards;

import game.controller.MainController;
import game.entities.Player;
import game.entities.fields.AbstractFields;

public class MoveField extends SuperCard {

	/**
	 * @author Henrik
	 */

	private int fieldNumber;
	private final static String CARD_TYPE = "MoveField";

	public MoveField(int cardNumber, String cardText, int fieldNumber) {
		super(cardNumber, CARD_TYPE, cardText);
		this.fieldNumber = fieldNumber;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		controller.getGUI().showChanceMoveMessage(player.getName());
		switch(fieldNumber) {
		case (-3): 
			controller.movePlayer(player, -3);			
			break;
		default:
			if (player.getPosition()>fieldNumber) 
				player.depositBalance(4000);			
			controller.movePlayerTo(player, fieldNumber);			
		}
		AbstractFields field = null;
		field = controller.getBoard().getFields()[player.getPosition() - 1];
		field.landOnField(controller);
		controller.getGUI().updateBalance(player.getName(), player.getBalance()); // For when they've payed stuff
	}

}
