package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cardTypes.CardCreater;
import cardTypes.SuperCard;

public class CardTest {

	private static SuperCard[] chanceCard;
	private CardCreater cardcreater;

	@BeforeClass
	public static void setUp() throws Exception {
	chanceCard = new CardCreater().getCards();
		printFields();
	}

	public static void printFields(){
		for (SuperCard chancecard : chanceCard)
			System.out.println(chancecard.toString());		
	}
	
	@Test
	public void testFields() {
		assertEquals("De modtager Deres aktieudbytte. Modtag kr. 1000 af banken.", chanceCard[0].getCardText());
	}
	
	@Test
	public void testFields2() {
		assertEquals(1, chanceCard[0].getCardNumber());
	}
	
	
	
}