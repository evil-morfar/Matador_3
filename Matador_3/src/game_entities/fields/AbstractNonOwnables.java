package game_entities.fields;

import game_controller.MainController;

/** 
 * Superclass for NonOwnable Fields
 * @author Henrik
 */

public abstract class AbstractNonOwnables extends AbstractFields {

	public AbstractNonOwnables(int fieldID, String name, String fieldType) {
		super(fieldID, name, fieldType);
	}

	
	public void landOnfield(MainController controller) {
		
	}

}
