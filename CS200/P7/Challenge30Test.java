import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Challenge30Test {

	@Test
	void testIsPrime() {
		assertTrue(Challenge30.isPrime(2)==true);

		assertTrue(!Challenge30.isPrime(4)==true);
	}



	@Test
	void testFactor() {
	
		assertTrue(Challenge30.factor(9).equals("3*3"), Challenge30.factor(9));

		assertTrue(Challenge30.factor(2).equals("2"));
	}
}
