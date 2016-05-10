package game.entities.fields;

import game.controller.MainController;

/**
 * Super class for every field in the game
 * @author Henrik *
 */
public abstract class AbstractFields {

	private String name = "";
	private String fieldType = "";
	private int fieldID;
	
	/**
	 * @param fieldID the ID of the field, used to corporate with the Database
	 * @param name name of the field 
	 * @param fieldType The type of field 
	 */
	
	public AbstractFields(int fieldID, String name, String fieldType){
		this.name = name;
		this.fieldType = fieldType;
		this.fieldID = fieldID;
	}
	
	/**
	 * abstract method which will be inherited in subclasses
	 * @param controller The main game controller. Contains all relevant information. 
	*/	
	public abstract void landOnField(MainController controller);
	
	// Getters
	
	public String getName(){
		return name;
	}
	
	public String getFieldType(){
		return fieldType;
	}
	
	public int getFieldID() {
		return fieldID;
	}
	
	// String to string 
	
	public String toString() {
		return "Field #" +fieldID+ "(" + fieldType + "), name = " + name;
	}


}
