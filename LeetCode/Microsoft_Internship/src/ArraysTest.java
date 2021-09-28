import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArraysTest {
	Arrays test;

	@BeforeEach
	void setUp() throws Exception {
		test = new Arrays();
	}

	@Test
	void testTrap() {
		int[] height = {1, 0, 1};
		int expected = 1;
		int output = test.trap(height);
		assertEquals(expected, output);
		height = new int[]{1, 0, 1, 2};
		output = test.trap(height);
		assertEquals(expected, output);
		
		height = new int[] {0, 1, 0, 2, 1, 0, 1, 3, 4, 1, 2, 1};
		expected = 6;
		output = test.trap(height);
		assertEquals(expected, output);
		
		height = new int[] {1, 2, 3, 4, 5, 6};
		expected = 0;
		output = test.trap(height);
		assertEquals(expected, output);
		
		height = new int[] {6, 5, 4, 3, 2, 1};
		expected = 0;
		output = test.trap(height);
		assertEquals(expected, output);
		
		height = new int[] {4, 2, 0, 3, 2, 5};
		expected = 9;
		output = test.trap(height);
		assertEquals(expected, output);
	}
//	@Test
//	void testRestoreString() {
//		String s = "codeleet";
//		int[] indices = {4, 5, 6, 7 ,0, 2, 1, 3};
//		String output = "leetcode";
//		String result = test.restoreString(s, indices);
//		assertEquals(output, result);
//	}
//	
//@Test
//void testSmallerNumbersThanCurrent() {
////	int[] nums = {8, 1, 2,2, 3};
////	int [] nums = {6, 5, 4, 8};
//	int[] nums = {1, 2, 3, 4, 5, 6, 7};
////	int[] expected = {2, 1, 0, 3};
//	int [] expected = {0, 1, 2, 3, 4, 5, 6};
////	int[] expected = {4, 0, 1, 1, 3};
//	int[] result = test.smallerNumbersThanCurrent(nums);
//	for(int i = 0; i<result.length; i++) {
//		assertEquals(expected[i], result[i]);
//	}
//}
	
	//	@Test
//	void testMax() {
//		int[][] accounts = {{1, 2, 3}, {3, 2, 1}};
//		assertEquals(6, test.maximumWealth(accounts));
//		
//	}
	
//	@Test
//	void testConcat() {
//		int[] nums = new int[] {1};
//		int[] ans = new int[] {1, 1};
//		int[] result = test.getConcatenation(nums);
//		for(int i = 0; i<result.length; i++) {
//			assertEquals(ans[i], result[i]);
//		}
//	}
	
//	@Test
//	void testIntersect() {
//		int[] nums1 = new int[] {1, 2, 3, 4};
//		int[] nums2 = new int[] {1, 2, 59, 9};
//		int[] result = new int[] {1, 2};
//		int [] actual = test.intersect(nums1, nums2);
//		for(int i = 0; i<actual.length; i++) {
//			assertEquals(result[i], actual[i]);
//		}
//	}
	//
//	@Test
//	void testsubsets() {
//		int[] nums = new int[] {1, 2};
//		List<List<Integer>> actual = test.subsets(nums);
//		for(int i = 0; i<actual.size(); i++) {
//			for(int j = 0; j<actual.get(i).size(); j++) {
//				System.out.println(actual.get(i).get(j));
//			}
//		}
//	}
//	@Test
//	void testNumJewels() {
//		String jewels = "aA";
//		String stones = "aAAbbbde";
//		assertEquals(3, test.numJewelsInStones(jewels, stones));
//		
//		jewels = "zz";
//		stones = "wefklwefnwef";
//		assertEquals(0, test.numJewelsInStones(jewels, stones));
//		
//		jewels = "aljews";
//		stones = "aaallljjjeeewwwsss";
//		assertEquals(18, test.numJewelsInStones(jewels, stones));
//	}
//	@Test
//	void testRotate() {
//		int[] nums = new int[] {1, 2, 3};
//		int[] result = new int[] {3, 1, 2};
//		test.rotate(nums, 1);
//		for(int i = 0; i<nums.length; i++) {
//			System.out.println(nums[i]);
//			assertEquals(result[i], nums[i]);
//		}
//		nums = new int[] {1, 2, 3};
//		result = new int[] {2, 3, 1};
//		test.rotate(nums, 2);
//		for(int i = 0; i<nums.length; i++) {
//			System.out.println(nums[i]);
//			assertEquals(result[i], nums[i]);
//		}
//		
//		nums = new int[] {1, 2, 3};
//		result = new int[] {2, 3, 1};
//		test.rotate(nums, 5);
//		for(int i = 0; i<nums.length; i++) {
//			System.out.println(nums[i]);
//			assertEquals(result[i], nums[i]);
//		}
//	}
//	@Test
//	void testMaxProfit() {
//		int[] prices = new int[] {7, 1, 5, 3, 6, 4};
//		int profit = test.maxProfit(prices);
//		assertEquals(7, profit);
//	}
//	@Test
//	void testfizzBuzz() {
//		int n = 15;
//		List<String> output = test.fizzBuzz(n);
//		for(int i = 1; i<output.size(); i++) {
//			System.out.println(output);
//			if(i%3 ==0 && i%5 ==0) {
//				assertEquals("fizzbuzz", output.get(i));
//			} else if(i%3 == 0) {
//				assertEquals("fizz", output.get(i));
//			} else if(i%5 == 0) {
//				assertEquals("buzz", output.get(i));
//			} else {
//				assertEquals(i+"", output.get(i));
//			}
//		}
//	}
//
//		@Test
//		void testRemoveDuplicates() {
//			int[] nums = {0, 1, 1, 1};
//			int[] result = {0, 1, 1};
//			int length = test.removeDuplicates(nums);
//			int[] actual = new int[3];
//			for(int i = 0; i<length; i ++) {
//				actual[i] = nums[i];
//			}
//			
//			for(int i = 0; i<result.length; i++) {
//				assertEquals(result[i], actual[i]);
//			}
//			
//			nums = new int[] {1, 1, 1, 2, 2, 3};
//			result = new int[] {1, 1, 2, 2, 3};
//			length = test.removeDuplicates(nums);
//			System.out.println(length);
//			actual = new int[result.length];
//			for(int i = 0; i<length; i ++) {
//				actual[i] = nums[i];
//			}
//			
//			for(int i = 0; i<result.length; i++) {
//				assertEquals(result[i], actual[i]);
//			}
//			
//			nums = new int[] {1, 1, 1};
//			result = new int[] {1, 1};
//			length = test.removeDuplicates(nums);
//			System.out.println(length);
//			actual = new int[result.length];
//			for(int i = 0; i<length; i ++) {
//				actual[i] = nums[i];
//			}
//			
//			for(int i = 0; i<result.length; i++) {
//				assertEquals(result[i], actual[i]);
//			}
//		}
//	@Test
//	void testMoveZeroes() {
//		int[] nums = new int[] {0, 0, 1, 2};
//		int[] result = new int[] {1, 2, 0, 0};
//		
//		
//		nums = new int[] {1};
//		result = new int[] {1};
//		test.moveZeroes(nums);
//		assertEquals(result[0], nums[0]);
//	
//		nums = new int[] {1, 0};
//		result = new int[] {1, 0};
//		test.moveZeroes(nums);
//		for(int i = 0; i<result.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//		
//		nums = new int[] {1, 0, 3};
//		result = new int[] {1, 3, 0};
//		test.moveZeroes(nums);
//		for(int i = 0; i<result.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//	}
//	@Test
//	void testRemoveDuplicates() {
//		int[] nums = new int[] {1, 1, 2};
//		int length = test.removeDuplicates(nums);
//		assertEquals(2, length);
//		
//		nums = new int[] {1, 1, 1, 1};
//		length = test.removeDuplicates(nums);
//		assertEquals(1, length);
//	}
//	@Test
//	void testSingleNumber() {
//		int[] nums = new int[] {1, 2, 3, 4, 1, 2, 3};
//		int result = 4;
//		int actual = test.singleNumber(nums);
//		assertEquals(result, actual);
//	}
//	@Test
//	void testCreateTargetArray() {
//		int[] nums = new int[] { 1, 2, 3 };
//		int[] index = new int[] { 0, 2, 1 };
//		int[] result = new int[] { 1, 3, 2 };
//		nums = test.createTargetArray(nums, index);
//		for (int i = 0; i < index.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		nums = new int[] { 1, 2, 3 };
//		index = new int[] { 0, 0, 0 };
//		result = new int[] { 3, 2, 1 };
//		nums = test.createTargetArray(nums, index);
//		for (int i = 0; i < index.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		nums = new int[] { 1, 2, 3 };
//		index = new int[] { 1, 0, 0 };
//		result = new int[] { 3, 2, 1 };
//		nums = test.createTargetArray(nums, index);
//		for (int i = 0; i < index.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		nums = new int[] { 1, 2, 3, 4, 0 };
//		index = new int[] { 0, 1, 2, 3, 0 };
//		result = new int[] { 0, 1, 2, 3, 4 };
//		nums = test.createTargetArray(nums, index);
//		for (int i = 0; i < index.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//	}
//
//	@Test
//	void testSearchInsert() {
//		int[] nums = new int[] { 1, 3, 5, 6 };
//		int target = 5;
//		assertTrue(test.searchInsert(nums, target) == 2);
//
//		nums = new int[] { 3, 3, 4, 5, 6, 293 };
//		target = 0;
//		assertEquals(0, test.searchInsert(nums, target));
//
//		nums = new int[] { 1, 3, 5, 6 };
//		target = 7;
//		assertEquals(4, test.searchInsert(nums, target));
//
//		target = 4;
//		assertEquals(2, test.searchInsert(nums, target));
//	}
//
//	@Test
//	void testRemoveElement() {
//		int[] nums = new int[] { 3, 2, 2, 3 };
//		int[] result = new int[] { 3, 3, 2, 2 };
//		assertEquals(2, test.removeElement(nums, 2));
//		nums = new int[] { 2, 3, 2, 3 };
//		nums = test.removeElement2(nums, 2);
//		for (int i = 0; i < nums.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		nums = new int[] { 2, 2, 1, 1, 4 };
//		nums = test.removeElement2(nums, 1);
//		result = new int[] { 2, 2, 4, 1, 1 };
//		for (int i = 0; i < nums.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		nums = new int[] { 1, 2, 3, 4 };
//		nums = test.removeElement2(nums, 0);
//		result = new int[] { 1, 2, 3, 4 };
//		for (int i = 0; i < nums.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		nums = new int[] { 0, 0, 0, 0 };
//		nums = test.removeElement2(nums, 0);
//		result = new int[] { 0, 0, 0, 0 };
//		for (int i = 0; i < nums.length; i++) {
//			assertEquals(result[i], nums[i]);
//		}
//
//		assertEquals(0, test.removeElement(nums, 0));
//	}
//
//	@Test
//	void testNumGoodPairs() {
//		int[] nums = new int[] { 1, 2, 3, 1, 1, 3 };
//		assertEquals(4, test.numIdenticalPairs(nums));
//
//		nums = new int[] { 1, 2, 3, 4 };
//		assertEquals(0, test.numIdenticalPairs(nums));
//
//		nums = new int[] { 1, 1, 1, 1 };
//		assertEquals(6, test.numIdenticalPairs(nums));
//	}

}
