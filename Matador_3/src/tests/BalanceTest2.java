package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game_entities.Player;

public class BalanceTest2 {

	private Player Player1;


	@Before
	public void setUp() throws Exception {
		this.Player1 = new Player("Player1", 3000, "Blue", 0, 1);

	}

	@After
	public void tearDown()  {
		this.Player1 = new Player("Player1", 3000, "Blue", 0, 1);
	}


	@Test
	public void negativeTest(){
		
		Player1.depositBalance(-4000);
		
		int expected = 0;
		int actual = Player1.getBalance();
		
		
		Assert.assertEquals(expected,actual);
	}


	@Test
	public void positiveTest(){
		
		Player1.depositBalance(-1000);
	
		int expected = 2000;
		int actual = Player1.getBalance();
		
		Assert.assertEquals(expected, actual);
	}
}