package fields;

import game_controller.MainController;
import game_entities.Player;

/**
 * Ownable Field of the brewery type. Normally 2 of these in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class Brewery extends AbstractOwnable {
	
	private static final String FIELD_TYPE = "Brewery";
	private int multiply;

	/**
	 * Creates an Ownable Field of type Brewery. Rent is calculated according to
	 * the last dice sum times a multiplier, normally 100, or 200 if 2 breweries
	 * is owned.
	 * @param id The id of the field.
	 * @param name Name of the Field.
	 * @param price Price of buying the Field
	 * @param multiply The multiplier that determines rent. The double of this
	 * is used if a player owns 2 breweries.
	 * @see AbstractOwnable
	 */
	public Brewery(int id, String name, int price, int multiply) {
		super(id, name, FIELD_TYPE, price);
		this.multiply = multiply;
	}
	
	/**
	 * Controls what happens when a player lands on a Brewery type Field.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	public void landOnField(MainController controller){
		Player player = controller.getCurrentPlayer();
		Player owner = this.getOwner();
		if(owner != player ){
			int numOwned = controller.getBoard().getNumOwnedBreweries(owner);
			int rent = (this.multiply * numOwned) * controller.getRoll();
			player.withdrawBalance(rent);
		} else if (!this.isOwned()) {
			// TODO Prompt to buy
		}
	}

}
