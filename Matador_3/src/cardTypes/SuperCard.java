package cardTypes;

import game_controller.MainController;

/**
 * 
 * @author Henrik
 * @param supercard Super class for all card types
 */

public abstract class SuperCard {

	private int cardNumber;
	private String cardType;
	private String cardText;

	/**
	 * 
	 * @param cardNumber the number of the card
	 * @param cardType the given type of the card
	 * @param cardText the text of the card
	 */
	
	public SuperCard(int cardNumber, String cardType, String cardText) {
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.cardText =cardText;
	}

	public abstract void triggerCard(MainController controller);
	
	public String getCardText() {
		return cardText;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public String getCardType() {
		return cardType;
	}
}
