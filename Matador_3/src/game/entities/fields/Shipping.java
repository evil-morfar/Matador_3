package game.entities.fields;

import game.controller.MainController;
import game.entities.Player;

/**
 * The Ownable Field of type Shipping. There's 4 of these in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class Shipping extends AbstractOwnable {
	
	private static final String FIELD_TYPE = "Shipping";
	private int rent;

	/**
	 * Creates an object of the Shipping class. Rent is calculated with
	 * 2^numberOfOwnedShips * rent.
	 * @param id The id of the field.
	 * @param name Name of the field.
	 * @param price Price of buying the field.
	 * @param rent Base rent of the field.
	 * @see AbstractOwnable
	 */
	public Shipping(int id, String name, int price, int rent) {
		super(id, name, FIELD_TYPE, price);
		this.rent = rent;		
	}
	
	/**
	 * Controls what happens when a player lands on the shipping field.
	 * Note the rent is higher if one owns more ships.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	public void landOnField(MainController controller){
		Player player = controller.getCurrentPlayer();
		Player owner = this.getOwner();
		if(player != owner && this.isOwned()){
			int owned = controller.getBoard().getNumOwnedShips(owner);

			int rent = this.rent * (int) Math.pow(2, owned - 1);
			controller.getGUI().showTransferMessage(player.getName(), this.getName(), owner.getName(), rent);
			player.withdrawBalance(rent); 
			owner.depositBalance(rent);
			super.landOnField(controller);
		}
	}

}
