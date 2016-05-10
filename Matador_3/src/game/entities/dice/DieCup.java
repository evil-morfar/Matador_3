package game.entities.dice;

/**************************************************************
 * 
 * @author Henrik
 * @return Simulates the Diecup with 2 dices
 *************************************************************/

public class DieCup implements DieCup_Interface{

	private Die die1;
	private Die die2;


	public DieCup() {
		die1 = new Die();
		die2 = new Die();
	}

	// Getters

	public int getDie1() {
		return die1.getFaceValue();
	}

	public int getDie2() {
		return die2.getFaceValue();
	}

	public int getSum() {
		return die1.getFaceValue() + die2.getFaceValue();
	}

	// 

	/******************************************************************
	 * 
	 * @return check if facevalues are identical, returns 0 if not
	 * 
	 ***************************************************************/

	public int isDoubles() {
		if (die1.getFaceValue()==die2.getFaceValue()) {
			return die1.getFaceValue();
		}else {
			return 0;
		}
	}

	/***********************************************
	 * 
	 * @return returns sum of the dice rolled
	 *********************************************/

	public int roll() {
		return die1.roll() + die2.roll();
	}

	/**********************************************************************************
	 * this getDice is easily used with the GUI that we are using in this game
	 * @return dice number in an array
	 *********************************************************************************/
	
	public int[] getDice(){
		int[] dice = {getDie1(), getDie2()};
		return dice;
		
	}
	

}
