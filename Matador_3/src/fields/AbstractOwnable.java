package fields;

import desktop_codebehind.Player;

/**
 * @author Nichlas N. Pilemand
 *
 */
public abstract class AbstractOwnable extends AbstractFields {

	protected int price;
	protected Player owner;

	public AbstractOwnable(String name, String fieldType, int price) {
		super(fieldType, name);
		this.price = price;
	}

	/********************************************************* 
	 * @param Herited method from superclass AbstractFields
	 ******************************************************''''*/
	
	@Override 
	public void landOnField(Player player) {
	
		if(this.owner==null){
			if(player.getBalance()>price) {
				// prompt to gui and if yes withdraw
			
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
}

