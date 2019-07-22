import static org.junit.Assert.*;

import org.junit.Test;

public class ParadeTest {

	@Test
	public void testFindBestFloat() {
		int[] test1 = new int[] { 4, 5, 2, 5, 4 };
		assertTrue(Parade.findBestFloat(test1) == 3);
		int[] test2 = new int[] { 1, 5, 10, 1 };
		assertTrue(Parade.findBestFloat(test2) == 2);
		int[] test3 = new int[] { 1, 1, 1, 1 };
		assertTrue(Parade.findBestFloat(test3) == 0);
		int[] test4 = new int[] { 1, 10, 22, 40, 100, 40000, 40001 };
		assertTrue(Parade.findBestFloat(test4) == 5);
		int[] test5 = new int[] {1,1,1,1};
		assertTrue(Parade.findBestFloat(test5) == 0);
		int[] test7 = new int[]{5,4,3,2,1};
		assertTrue(Parade.findBestFloat(test7) == 0);
		
	}

	@Test
	public void testFindTallestGroup() {
		int[] test1 = new int[] { 5, 2, 4, 4, 100, 100, 100, 100, 100, 2, 3, 4, 2, 3 };
		assertTrue(Parade.findTallestGroup(test1) == 6);
		int[] test2 = new int[] { 500, 100, 200, 300, 200, 4, 3, 2, 1, 500, 200, 300, 100, 200 };
		assertTrue(Parade.findTallestGroup(test2) == 2);
		int[] test3 = new int[] { 500, 100, 200, 300, 200, 4, 3, 2, 1, 500, 200, 300, 100, 201 };
		assertTrue(Parade.findTallestGroup(test3) == 11);
		int[] test4 = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		assertTrue(Parade.findTallestGroup(test4)==2);
	}

}
