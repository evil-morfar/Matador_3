package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import game_entities.cards.SuperCard;

public class CardTest {

	private static SuperCard[] chanceCard;
	private static CardCreaterMockClass cardcreater;

	@BeforeClass
	public static void setUp() throws Exception {
		cardcreater = new CardCreaterMockClass();
		chanceCard = cardcreater.getCards();
		printCards();
	}
	// prints all the cards to an array in order
	public static void printCards(){
		for (SuperCard card : chanceCard)
			System.out.println(card.toString());		
	}

	@Test
	public void testChance01() {
		assertEquals("De modtager Deres aktieudbytte. Modtag kr. 1000 af banken.", chanceCard[0].getCardText());
	}

	@Test
	public void testChance02() {
		assertEquals(1, chanceCard[0].getCardNumber());
	}

//	@Test
//	public void testChance03() {
//		assertEquals(3000,chanceCard[30].g)
//	}
	
}