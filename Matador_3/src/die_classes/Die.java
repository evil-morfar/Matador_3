package die_classes;

/**
 * @author Henrik
 * Die.java
 * Simulates a six sided die
 */

public class Die {

	private int faceValue;
	
	public Die(){
		faceValue = 1;
	}
	
	/**
	 * 
	 * @return sets the facevalue to a random number from 1-6 and returns the value
	 */
	
	public int roll(){
			faceValue = (byte)(Math.random()*6+1);
			return faceValue;
	}
	
/**
 * 
 * @return the current facevalue
 */
	
public int getFaceValue(){
	return faceValue;		
	}	
}
