package cardTypes;

/**
 * 
 * @author Henrik
 * @param Super class for all card types
 */

public abstract class SuperCard {

	private String cardText;
	
	public SuperCard(String cardText) {
		this.setCardText(cardText);
	}

	public String getCardText() {
		return cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

}
