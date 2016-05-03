package cardTypes;

import fields.AbstractFields;
import fields.AbstractOwnable;
import game_controller.MainController;
import game_entities.Player;

public class MoveFleet extends SuperCard {


	private int rent;
	
	private final static String CARD_TYPE = "MoveFleet";


	public MoveFleet(int cardNumber, String cardText ) {
		super(cardNumber, CARD_TYPE, cardText);
		
	}

	@Override
	public void triggerCard(MainController controller) {
		// has to double rent if fleet field is owned 
		Player player = controller.getCurrentPlayer();
		AbstractFields []field = controller.getBoard().getFields();
		int fieldNumber = player.getPosition();
		
		switch(fieldNumber) {
		
		case(3): controller.movePlayer(player, 6);	
		
		if(((AbstractOwnable) field[6-1]).isOwned()) {
			 if(((AbstractOwnable) field[6-1]).getOwner() != controller.getCurrentPlayer()){
				field[6-1].landOnField(controller);
				int rent = this.rent * 2 ^ owned;
				player.withdrawBalance(rent); 
			 }
		}else{
			field[6-1].landOnField(controller);
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
