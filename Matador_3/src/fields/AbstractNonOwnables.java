package fields;

/** 
 * Superclass for NonOwnable Fields
 * @author Henrik
 */

public abstract class AbstractNonOwnables extends AbstractFields {

	public AbstractNonOwnables(String name, String fieldType) {
		super(name, fieldType);
	}

	@Override public void landOnfield(Player player) {
		
	}
	
}
