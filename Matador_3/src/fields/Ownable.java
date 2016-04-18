package fields;

/**
 * @author Nichlas N. Pilemand
 *
 */
public class Ownable extends AbstractFields {

	protected int price;
	protected Player owner;
	
	public Ownable(int id, String name, int price) {
		super(id, name);
		this.price = price;
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

