package game.entities.fields;

import game.controller.MainController;
import game.entities.Board;
import game.entities.Player;

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
	private String color;
	private boolean hasHotel = false;

	/**
	 * Creates a Ownable Field of type Territory. Rent is calculated according to
	 * number of hotels, houses and owned territories of the same color.
	 * @param id The id of the field
	 * @param name Name of the Field
	 * @param price Price of buying the Field
	 * @param housePrice The price of buying a new house.
	 * @param color The color group of the field.
	 * @param rent Array, size 6.
	 * Indexes: 	<p>0 = rent without houses</p>
	 * 				<p>1-4 = rent with 1-4 houses</p>
	 * 				<p>5 = rent with hotel</p>
	 * @see AbstractOwnable
	 */
	public Territory(int id, String name, int price, int housePrice, String color, int[] rent) {
		super(id, name, FIELD_TYPE, price);
		this.rent = rent;
		this.housePrice = housePrice;
		this.color = color;
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
	
	public String getColor() {
		return this.color;
	}
	
	/**
	 * Controls what happens when a player lands on this type of field.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	public void landOnField(MainController controller){
		Player player = controller.getCurrentPlayer();
		Player owner = this.getOwner();
		Board board = controller.getBoard();
		if (owner != player && this.isOwned()) {
			int rent = 0;
			// Calculate the rent based on number of owned fields or houses
			if (this.hasHotel) {
				rent = this.rent[5];
			} else if(this.numHouses > 0) {
				rent = this.rent[this.numHouses];
			} else if(board.getNumColorFields(this.color) == board.getNumOwnedSameColor(owner, this.color)) {
				rent = 2 * this.rent[0]; // Double rent
			} else if (player != owner) {
				rent = this.rent[0];
			} else if(!this.isOwned()){
				//case not owned
				
			}
			if (rent != 0) // Rent is due
				player.withdrawBalance(rent);
				owner.depositBalance(rent);
			controller.getGUI().showTransferMessage(player.getName(), this.getName(), owner.getName(), rent);
		}
	}

	/**
	 * @return the housePrice
	 */
	public int getHousePrice() {
		return housePrice;
	}


}
