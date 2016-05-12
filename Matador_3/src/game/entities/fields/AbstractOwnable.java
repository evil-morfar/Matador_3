package game.entities.fields;

import game.controller.MainController;
import game.entities.Player;

/************************************************
 * Superclass for Ownable Fields.
 * @author Henrik 
 ***************************************************/
public abstract class AbstractOwnable extends AbstractFields {

	private int price;
	private Player owner;

	public AbstractOwnable(int fieldID, String name, String fieldType, int price) {
		super(fieldID, name, fieldType);
		this.price = price;
		this.owner = null;
	}

	/**
	 * Inherited method from superclass AbstractFields
	 * Gives the player the option to buy the field if not owned, else the player must pay rent
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	@Override 
	public void landOnField(MainController controller) {
		//Update the owners balance incase he got some rent
		controller.getGUI().updateBalance(this.owner.getName(), this.owner.getBalance());
	}

	public void clearOwner() {
		this.owner = null;
	}



	/***************************************************
	 * @return true if owned, otherwise false			*
	***************************************************/
	public boolean isOwned() {
		return (owner == null) ? false : true;
	}

	public int getPrice() {
		return price;
	}

	public Player getOwner(){
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	@Override
	public String toString(){
		String str = super.toString() + ", price=" + price;
		if(owner != null)
			str += ", owner=" + owner.getName();

		return str;
	}	
}

