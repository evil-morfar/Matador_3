package cardTypes;

import fields.AbstractFields;
import fields.Territory;
import game_controller.MainController;
import game_entities.Player;

public class PayHouses extends SuperCard {
	
	private AbstractFields[] fields;
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

	/**
	 * Method to find the amount of houses the player owns 
	 * @param player
	 * @return number of houses the current player owns
	 */
	
	private int getNumOwnedHouses (Player player) {
		int houses = 0;
		for (AbstractFields field: fields)
			if(field instanceof Territory)
				if(((Territory) field).getOwner() == player)
					houses += (((Territory) field).getNumHouses());
		return houses;
	}

	/**
	 * Method to find the amount of hotels the player owns 
	 * @param player
	 * @return number of hotels the current player owns 
	 */
	
	private int getNumOwnedHotels (Player player) {
		int hotels = 0;
		for (AbstractFields field: fields)
			if(field instanceof Territory)
				if(((Territory) field).getOwner() == player)
					if (((Territory) field).hasHotel());
		hotels++;
		return hotels;
	}
	
	@Override
	public void triggerCard(MainController controller) {
		fields = controller.getBoard().getFields();
		Player player = controller.getCurrentPlayer();
		int hotels = getNumOwnedHotels(player);		
		int houses = getNumOwnedHouses(player);
		
		int	amount = (houses*getAmountPerHouse())+ hotels*getAmountPerHotel();
		player.withdrawBalance(amount);
	}
}

