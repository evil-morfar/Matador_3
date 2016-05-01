package cardTypes;

import game_controller.MainController;
import game_entities.Player;

public class MoveField extends SuperCard {

	/**
	 * @author Henrik
	 */

	private int fieldNumber;

	public MoveField(int cardNumber, String cardType, String cardText, int fieldNumber) {
		super(cardNumber, cardType, cardText);
		this.fieldNumber = fieldNumber;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	@Override
	public void triggerCard(MainController controller) {
		Player player = controller.getCurrentPlayer();
		if (player.getPosition()>fieldNumber) {
			player.depositBalance(4000);
		}
		else {
		}
		controller.movePlayerTo(player, fieldNumber);
		// not done, skal finde ud af hvordan man flytter personen men stadig giver ham mulighed for at købe feltet
	}

}
