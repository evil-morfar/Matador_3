package cardTypes;

/**
 * 
 * @author Henrik
 * @param Super class for all card types
 */

public abstract class SuperCard {

	private int cardNumber;
	private String cardType;
	private String cardText;
	
	public SuperCard(int cardNumber, String cardType, String cardText) {
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.cardText =cardText;
	}

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
