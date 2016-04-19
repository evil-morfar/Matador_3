package fields;

import desktop_codebehind.Player;

/**
 * Superclass for Ownable Fields.
 * @author Henrik 
 */
public abstract class AbstractOwnable extends AbstractFields {

	protected int price;
	protected Player owner;

	public AbstractOwnable(String name, String fieldType, int price) {
		super(fieldType, name);
		this.price = price;
		this.owner = null;
	}

	/********************************************************* 
	 * @param Herited method from superclass AbstractFields
	 ******************************************************''''*/

	@Override 
	public void landOnField(Player player) {

		if(this.owner==null){

			if(player.getBalance()>price) {

				if(//player accepts on gui)
						player.withdraw(price);
						owner = player;
						// buy successful message				

			} else // not bought message 

		} else // not enough balance message 

	}

	public void clearOwner() {
		this.owner = null;
	}



	/**
	 * @return true if owned, otherwise false
	 */
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

