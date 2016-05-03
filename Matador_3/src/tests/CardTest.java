package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cardTypes.CardCreater;
import cardTypes.SuperCard;

public class CardTest {

	private static SuperCard[] chanceCard;
	private static CardCreater cardcreater;

	@BeforeClass
	public static void setUp() throws Exception {
		cardcreater = new CardCreater();
		chanceCard = cardcreater.getCards();
		printFields();
	}

	public static void printFields(){
		for (SuperCard card : chanceCard)
			System.out.println(card.toString());		
	}
	
	@Test
	public void testFields01() {
		assertEquals("De modtager Deres aktieudbytte. Modtag kr. 1000 af banken.", chanceCard[0].getCardText());
	}
	
	@Test
	public void testFields02() {
		assertEquals(1, chanceCard[0].getCardNumber());
	}
	
}