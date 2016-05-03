package cardTypes;

import fields.AbstractFields;
import fields.AbstractOwnable;
import game_controller.MainController;
import game_entities.Player;

public class MoveFleet extends SuperCard {

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
			chanceRent(controller, player, 6 - 1, field);
		
		break;
		case(8):controller.movePlayer(player, 16);
			chanceRent(controller, player, 16 - 1, field);
		break;
		case(18):controller.movePlayer(player, 26);
			chanceRent(controller, player, 26 - 1, field);
		break;
		case(23):controller.movePlayer(player, 26);
			chanceRent(controller, player, 26 - 1, field);
		break;
		case(34):controller.movePlayer(player, 36);
			chanceRent(controller, player, 36 - 1, field);
		break;
		case(37):controller.movePlayer(player, 6);
			chanceRent(controller, player, 6 - 1, field);
		break;
		default:
			
		}
	}
	
	public void chanceRent (MainController controller, Player player, int i, AbstractFields[] field){		
		if(((AbstractOwnable) field[i]).isOwned()) 
			if(((AbstractOwnable) field[i]).getOwner() != player)
				field[i].landOnField(controller);
		field[i].landOnField(controller);

	}
	
}
