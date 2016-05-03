package cardTypes;

import fields.AbstractFields;
import fields.AbstractOwnable;
import fields.Shipping;
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
			chanceRent(controller);
		
		break;
		case(8):controller.movePlayer(player, 16);
			chanceRent(controller);
		break;
		case(18):controller.movePlayer(player, 26);
			chanceRent(controller);
		break;
		case(23):controller.movePlayer(player, 26);
			chanceRent(controller);
		break;
		case(34):controller.movePlayer(player, 36);
			chanceRent(controller);
		break;
		case(37):controller.movePlayer(player, 6);
			chanceRent(controller);
		break;
		default:
			
		}
	}
	
	public void chanceRent (MainController controller){
		Player player = controller.getCurrentPlayer();
		AbstractFields []field = controller.getBoard().getFields();
		int i = player.getPosition()-1;
		
		
		if(((AbstractOwnable) field[i]).isOwned()) 
			if(((AbstractOwnable) field[i]).getOwner() != player)

				field[i].landOnField(controller);
		field[i].landOnField(controller);

	}
	
}
