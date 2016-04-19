package fields;

import game_entities.Player;

/**
 * The Ownable Field of type Territory - the common buyable field in a Monopoly game.
 * @see Territory#Territory(String, int, int[])
 * @author Nichlas N. Pilemand
 */
public class Territory extends AbstractOwnable {
	
	private static final String FIELD_TYPE = "Territory";
	private int[] rent;
	private int numHouses = 0;
	private int housePrice;
	private boolean hasHotel = false;

	/**
	 * Creates a Ownable Field of type Territory. Rent is calculated according to
	 * number of hotels, houses and owned territories of the same color.
	 * @param id The id of the field
	 * @param name Name of the Field
	 * @param price Price of buying the Field
	 * @param housePrice The price of buying a new house.
	 * @param rent Array, size 6.
	 * Indexes: 	<p>0 = rent without houses</p>
	 * 				<p>1-4 = rent with 1-4 houses</p>
	 * 				<p>5 = rent with hotel</p>
	 * @see AbstractOwnable
	 */
	public Territory(int id, String name, int price, int housePrice, int[] rent) {
		super(id, name, FIELD_TYPE, price);
		this.rent = rent;
		this.housePrice = housePrice;
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
		Player owner = this.getOwner();
		if (owner != player) {
			//TODO do stuff
		}
	}

}
