import static org.junit.Assert.*;

import org.junit.Test;


public class FairDiceTest {

	@Test
	public void testWinOrNot() {
		assertTrue(FairDice.winOrNot(1,1));
		assertTrue(FairDice.winOrNot(1,2));
		assertTrue(FairDice.winOrNot(1,4));
		assertTrue(!FairDice.winOrNot(1,7));
		assertTrue(FairDice.winOrNot(6,3));
		assertTrue(FairDice.winOrNot(6,4));
		assertTrue(!FairDice.winOrNot(6,1));
	}

}
