package cardTypes;

import game_controller.MainController;

public class PayHouses extends SuperCard {

	private int amountPerHouse;
	private int amountPerHotel;
	private final static String CARD_TYPE = "PayHouses";
	
	public PayHouses(int cardNumber, String cardText, int amountPerHouse, int amountPerHotel) {
		super(cardNumber, CARD_TYPE, cardText);
		this.amountPerHouse = amountPerHouse;
		this.amountPerHotel = amountPerHotel;
	}

	/**
	 * @return the amountHouses
	 */
	public int getAmountPerHouse() {
		return amountPerHouse;
	}

	/**
	 * @return the amountHotels
	 */
	public int getAmountPerHotel() {
		return amountPerHotel;
	}

	@Override
	public void triggerCard(MainController controller) {
	
		
		// not sure how to count hotels/houses on all fields
		
	}
	
	}

