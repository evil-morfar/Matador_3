/**
 * The Ownable Field of type Territory - the common buyable field in a Monopoly game.
 * @author Nichlas N. Pilemand, 2016
 */

package fields;

public class Territory extends AbstractOwnable {
	
	private static final String FIELD_TYPE = "Territory";
	private int[] rent;
	private int numHouses = 0;
	private boolean hasHotel = false;

	public Territory(String name, int price, int[] rent) {
		super(name, FIELD_TYPE, price);
		this.rent = rent;
	}
	
	/**
	 * Assigns houses to the territory. Must be at least 0 and at most 4.
	 * @param i The number of houses to assign (0-4)
	 */
	public void setNumHouses(int i){
		if (i >= 0 && i <= 4)
			this.numHouses = i;
		else {
			//TODO Throw error?			
		}
	}
	
	/**
	 * @return The number of houses on the field
	 */
	public int getNumHouses(){
		return this.numHouses;
	}
	
	/**
	 * @param b Boolean, assigns hotel to the field
	 */
	public void setHotel(boolean b){
		this.hasHotel = b;
	}
	
	/**
	 * @return True if there's a hotel on the field, false otherwise
	 */
	public boolean hasHotel(){
		return this.hasHotel;
	}
	
	/**
	 * Controls what happens when a player lands on this type of field.
	 * @param player The player landing on the field.
	 * @see AbstractOwnable
	 */
	public void landOnField(Player player){
		Player owner = super.getOwner();
		if (owner != player) {
			//TODO do stuff
		}
	}

}
