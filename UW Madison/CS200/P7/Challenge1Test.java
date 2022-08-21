import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Challenge1Test {

	@Test
	void testComputeDigitSum() {
		assertTrue(Challenge1.computeDigitSum(0)==0);
		assertTrue(Challenge1.computeDigitSum(10)==1);
		assertTrue(Challenge1.computeDigitSum(159)==15);
		assertTrue(Challenge1.computeDigitSum(11111)==5);
		assertTrue(Challenge1.computeDigitSum(12)==3);
	}

}
