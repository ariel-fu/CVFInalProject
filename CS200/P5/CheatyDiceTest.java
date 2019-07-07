import static org.junit.Assert.*;

import org.junit.Test;


public class CheatyDiceTest {

	@Test
	public void testWinOrNot() {
		assertTrue(CheatyDice.winOrNot(1, 2, 9));
		assertTrue(CheatyDice.winOrNot(9, 2, 1));
		assertTrue(CheatyDice.winOrNot(2, 9, 1));
		assertTrue(!CheatyDice.winOrNot(12, 18, 6));
		assertTrue(CheatyDice.winOrNot(1, 1, 1));
		
		
	}

}
