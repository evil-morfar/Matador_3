package cardTypes;

import game_controller.MainController;
import game_entities.Player;

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
				controller.movePlayerTo(player, 40);
			}else 
				controller.movePlayer(player, -3);			

		default:
			if (player.getPosition()>fieldNumber) {
				player.depositBalance(4000);
			}
			else {
			}
			controller.movePlayerTo(player, fieldNumber);
		}

		// not done, skal finde ud af hvordan man flytter personen men stadig giver ham mulighed for at k�be feltet
	}

}
