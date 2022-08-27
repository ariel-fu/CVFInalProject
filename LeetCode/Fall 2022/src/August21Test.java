import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class August21Test {

	private August21 test = new August21();
	@Test
	void runningSum1() {
		int[] nums = {1, 2, 3, 4};
		int[] runningSum = test.runningSum(nums);
		int[] expected = {1, 3, 6, 10};
		for(int i = 0; i<runningSum.length; i++) {
			assertEquals(runningSum[i], expected[i]);
		}
		
	}
	
	@Test
	void runningSum2() {
		int[] nums = {1, 1, 1, 1, 1};
		int[] runningSum = test.runningSum(nums);
		int[] expected = {1, 2, 3, 4, 5};
		for(int i = 0; i<runningSum.length; i++) {
			assertEquals(runningSum[i], expected[i]);
		}
		
	}
	
	@Test
	void runningSum3() {
		int[] nums = {3, 1, 2, 10, 1};
		int[] runningSum = test.runningSum(nums);
		int[] expected = {3, 4, 6, 16, 17};
		for(int i = 0; i<runningSum.length; i++) {
			assertEquals(runningSum[i], expected[i]);
		}
		
	}
	
	@Test
	void pivotIndex1() {
		int[] nums = {1, 7, 3, 6, 5, 6};
		int output = test.pivotIndex(nums);
		int expected = 3;
		assertEquals(expected, output);		
	}
	
	@Test
	void pivotIndex2() {
		int[] nums = {1, 2, 3};
		int output = test.pivotIndex(nums);
		int expected = -1;
		assertEquals(expected, output);		
	}
	
	@Test
	void middleNode1() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode middle = test.middleNode(head);
		assertEquals(3, middle.val);
	}
	
	@Test
	void middleNode2() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
		ListNode middle = test.middleNode(head);
		assertEquals(4, middle.val);
	}
	
	@Test
	void balancedStringSplit1() {
		String s = "RLRRLLRLRL";
		int output = test.balancedStringSplit(s);
		assertEquals(4, output);
	}
	
	@Test
	void balancedStringSplit2() {
		String s = "RLRRRLLRLL";
		int output = test.balancedStringSplit(s);
		assertEquals(2, output);
	}
	
	@Test
	void balancedStringSplit3() {
		String s = "LLLLRRRR";
		int output = test.balancedStringSplit(s);
		assertEquals(1, output);
	}
	
	@Test
	void isIsomorphic1() {
		String s = "egg";
		String t = "add";
		assertTrue(test.isIsomorphic(s, t));
	}
	
	@Test
	void isIsomorphic2() {
		String s = "edg";
		String t = "add";
		assertFalse(test.isIsomorphic(s, t));
	}
	
	@Test
	void isIsomorphic3() {
		String s = "foo";
		String t = "bar";
		assertFalse(test.isIsomorphic(s, t));
	}
	
	@Test
	void isSubsequence1() {
		String s = "abc";
		String t = "ahbgdc";
		assertTrue(test.isSubsequence(s, t));
	}
	
	@Test
	void isSubsequence2() {
		String s = "abc";
		String t = "bahdc";
		assertFalse(test.isSubsequence(s, t));
	}
	
	@Test
	void isSubsequence3() {
		String s = "";
		String t = "bahdc";
		assertTrue(test.isSubsequence(s, t));
	}
	
	@Test
	void findMiddleIndex1() {
		int[] nums = {2, 3, -1, 8, 4};
		int index = test.findMiddleIndex(nums);
		assertEquals(3, index);
	}
	
	@Test
	void testMiddleIndex2() {
		int[] nums = {1, -1, 4};
		int index = test.findMiddleIndex(nums);
		assertEquals(2, index);
	}
	
	@Test
	void testMiddleIndex3() {
		int[] nums = {3, 0};
		int index = test.findMiddleIndex(nums);
		assertEquals(1, index);
	}
	
	@Test
	void testgroupThePeople() {
		int[] groupSizes = {3, 3, 3, 3, 3, 1, 3};
		List<List<Integer>> res = test.groupThePeople(groupSizes);
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		
		expected.add(Arrays.asList(0, 1, 2));
		// , {3, 4, 6}, {5}}
		expected.add(Arrays.asList(3, 4, 6));
		expected.add(Arrays.asList(5));
		
		for(int i = 0; i <expected.size(); i++) {
			for(int j = 0; j < expected.get(i).size(); j++) {
				assertEquals(expected.get(i).get(j), res.get(i).get(j));
				System.out.print(expected.get(i).get(j) + " " + res.get(i).get(j) + "\n");
			}
		}
	}
	
	@Test
	void testGroupThePeople2() {
		int[] groupSizes = {2, 1, 3, 3, 3, 2};
		List<List<Integer>> res = test.groupThePeople(groupSizes);
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		
		expected.add(Arrays.asList(0, 5));
		// , {3, 4, 6}, {5}}
		expected.add(Arrays.asList(1));
		expected.add(Arrays.asList(2, 3, 4));
		
		for(int i = 0; i <expected.size(); i++) {
			for(int j = 0; j < expected.get(i).size(); j++) {
				assertEquals(expected.get(i).get(j), res.get(i).get(j));
				System.out.print(expected.get(i).get(j) + " " + res.get(i).get(j) + "\n");
			}
		}
	}
	
	@Test
	void testGroupThePeople3() {
		int[] groupSizes = {2, 2, 1, 1, 1, 1, 1, 1};
		List<List<Integer>> res = test.groupThePeople(groupSizes);
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		
		expected.add(Arrays.asList(0, 1));
		// , {3, 4, 6}, {5}}
		expected.add(Arrays.asList(2));
		expected.add(Arrays.asList(3));
		expected.add(Arrays.asList(4));
		expected.add(Arrays.asList(5));
		expected.add(Arrays.asList(6));
		expected.add(Arrays.asList(7));
		
		
		for(int i = 0; i <expected.size(); i++) {
			for(int j = 0; j < expected.get(i).size(); j++) {
				assertEquals(expected.get(i).get(j), res.get(i).get(j));
				System.out.print(expected.get(i).get(j) + " " + res.get(i).get(j) + "\n");
			}
		}
	}

}
