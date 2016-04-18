/**
 * The Ownable Field of type Shipping. There's 4 of these in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */

package fields;

public class Shipping extends AbstractOwnable {
	
	private static final String FIELD_TYPE = "Shipping";
	private int rent;

	/**
	 * Creates an object of the Shipping class. Rent is calculated with
	 * 2^numberOfOwnedShips * rent.
	 * @param name Name of the field.
	 * @param price Price of buying the field.
	 * @param rent Base rent of the field.
	 * @see AbstractOwnable
	 */
	public Shipping(String name, int price, int rent) {
		super(name, FIELD_TYPE, price);
		this.rent = rent;		
	}
	
	/**
	 * Controls what happens when a player lands on the shipping field.
	 * Note the rent is higher if one owns more ships.
	 * @param player The player landing on the field.
	 * @see AbstractOwnable
	 */
	public void landOnField(Player player){
		Player owner = this.getOwner();
		if(player != owner){
			/* TODO do stuff.
			 * rent = rent * 2^player.ownedShips 
			 */
		}
	}

}
