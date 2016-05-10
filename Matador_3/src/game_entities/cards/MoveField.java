package game_entities.cards;

import game_controller.MainController;
import game_entities.Player;
import game_entities.fields.AbstractFields;

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
		switch(fieldNumber) {
		case -3: // only one chance card moves the player a fixed number of spaces back
			if(player.getPosition()==3) {
				controller.movePlayerTo(player, 40); // if on space 3 he would land on space 40 going 3 back
			}else 
				controller.movePlayer(player, -3);			

		default:
			if (player.getPosition()>fieldNumber) {
				player.depositBalance(4000);
			}
			else {
			}
			AbstractFields field = null;
			controller.movePlayerTo(player, fieldNumber);
			field = controller.getBoard().getFields()[controller.getCurrentPlayer().getPosition()-1];
			field.landOnField(controller);
			controller.getGUI().updateBalance(player.getName(), player.getBalance()); // For when they've payed stuff	
			
			
		}

	}

}
