import static org.junit.Assert.*;

import org.junit.Test;

public class PierreTest {

	@Test
	public void test() {
		int[] test= {0,0,0};
		runTest(test);
		test = new int[]{9,8,7};
		runTest(test);
	}
	
	private void runTest(int[] test) {
		Pierre.order(test);
		assertTrue(Pierre.isInOrder(test));
	}

}
