package cardTypes;

import fields.AbstractFields;
import fields.AbstractOwnable;
import game_controller.MainController;
import game_entities.Player;

public class MoveFleet extends SuperCard {

	private int fieldNumber;
	
	private final static String CARD_TYPE = "MoveFleet";


	public MoveFleet(int cardNumber, String cardText, int fieldNumber) {
		super(cardNumber, CARD_TYPE, cardText);
		this.fieldNumber = fieldNumber;
	}

	@Override
	public void triggerCard(MainController controller) {
		// has to double rent if fleet field is owned 
		Player player = controller.getCurrentPlayer();
		AbstractFields []field = controller.getBoard().getFields();
		
		switch(fieldNumber) {
		
		case(3): controller.movePlayer(player, 6);	
		
		if(field[6].isOwned()) {
			 if(field[6].getOwner == controller.getCurrentPlayer()){
				 controller.getCurrentPlayer().field[6].landOnField(controller);
				 
			 }
		
			
		}

			break;
		case(8):controller.movePlayer(player, 16);
			break;
		case(18):controller.movePlayer(player, 26);
			break;
		case(23):controller.movePlayer(player, 26);
			break;
		case(34):controller.movePlayer(player, 36);
			break;
		case(37):controller.movePlayer(player, 6);
			break;
		default:
			if (player.getPosition()>fieldNumber) {
				player.depositBalance(4000);			}
			else {
			}
			controller.movePlayerTo(player, fieldNumber);
	}

	}
	}
