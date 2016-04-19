package fields;

import game_entities.Player;

/**
 * Super class for every field in the game
 * @author Henrik *
 */
public abstract class AbstractFields {

	protected String name = "";
	protected String fieldType = "";
	protected int fieldID;
	
	/**
	 * 
	 * @param name name of the field 
	 * @param fieldType The type of field 
	 */
	
	public AbstractFields(int fieldID, String name, String fieldType){
		this.name = name;
		this.fieldType = fieldType;
		this.fieldID = fieldID;
	}
	
	/********************************************************************
	 * abstract method which will be inherited in subclasses
	 * @param landOnField THe method to be called when a player moves and lands on a field, different for all classes 
	 *********************************************************************'*/
	
	public abstract void landOnField(Player player);
	
	// Getters
	
	public String getName(){
		return name;
	}
	
	public String getFieldType(){
		return fieldType;
	}
	
	// String to string 
	
	public String toString() {
		return "Field #" +"fieldType"+ ", name = " +name;
	}


}
