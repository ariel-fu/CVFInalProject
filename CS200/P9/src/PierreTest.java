import static org.junit.Assert.*;

import org.junit.Test;

public class PierreTest {

	@Test
	public void test() {
		int[] test = { 0, 0, 0 };
		runTest(test);
		test = new int[] { 9, 8, 6 };
		runTest(test);
		test = new int[] { 6, 8, 10 };
		runTest(test);
		test = new int[] { 8, 6, 10 };
		runTest(test);
		test = new int[] { 8, 10, 6 };
		runTest(test);
		test = new int[20];
		for (int i = 0; i < 20; i++) {
			test[i] = 100 - (3 * i);
		}
		runTest(test);
		test = new int[20];
		for (int i = 0; i < 20; i++) {
			test[i] = (3 * i);
		}
		runTest(test);

		test = new int[100000];

		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < test.length; i++) {
				test[i] = (int) (Math.random() * 1000000);
			}
			runTest(test);
		}
	}

	private void runTest(int[] test) {
		Pierre.order(test);
		assertTrue(Pierre.isInOrder(test));
	}

}
