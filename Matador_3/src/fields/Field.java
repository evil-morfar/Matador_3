/**
 * Super class for every ownable fields in the game
 */
package fields;

/**
 * @author Nichlas N. Pilemand
 *
 */
public abstract class Field {

	protected String name = "";
	protected int id;
	
	public Field(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
	}
	
	public String toString() {
		return "Field #" +id+ ", name = " +name;
	}
}
