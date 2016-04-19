package fields;

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
	 * @param name Name of the Field.
	 * @param price Price of buying the Field
	 * @param multiply The multiplier that determines rent. The double of this
	 * is used if a player owns 2 breweries.
	 * @see AbstractOwnable
	 */
	public Brewery(String name, int price, int multiply) {
		super(name, FIELD_TYPE, price);
		this.multiply = multiply;
	}
	
	/**
	 * Controls what happens when a player lands on a Brewery type Field.
	 * @param player The player landing on the field.
	 * @param lastRoll The sum of the last dice roll - used to determine rent.
	 * @see Brewery#Brewery(String, int, int)
	 */
	public void landOnField(Player player, int lastRoll){
		Player owner = this.getOwner();
		if(owner != player ){
			// TODO do stuff.
			// Rent = (multiplier * numberOfOwnedBreweries) * lastRoll
		}
	}

}
