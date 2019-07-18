import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Parade {

	public static void main(String[] args) {

		/*
		 * int[] test1 = { 4, 5, 2, 5, 4 }; int[] test2 = { 1,5,10,1}; int[] test4 = new
		 * int[] {1,10,22,40,100,40000,40001}; int[] test3 = new int[] {1,1,1,1};
		 * System.out.println("5 --> " + findBestFloat(test4));
		 * System.out.println("1 --> " + findBestFloat(test3));
		 * System.out.println("3 --> " + findBestFloat(test1));
		 * System.out.println("2 --> " + findBestFloat(test2));
		 * 
		 * 
		 * int[] test3 = { 500, 100, 200, 300, 200, 4, 3, 2, 1, 500, 200, 300, 100, 201
		 * }; System.out.println("11 --> " + findTallestGroup(test3));
		 * 
		 * int []test1 = {10,5,6,7,8,11}; findNovelFloats(test1);
		 * System.out.println("----------------------"); int[] test2 = {1,2,3,4,5,5};
		 * findNovelFloats(test2); System.out.println("----------------------"); int[]
		 * test3 = {10,9,10}; findNovelFloats(test3);
		 * System.out.println("----------------------"); int[] test4 =
		 * {2,5,4,8,6,7,8,10,15}; findNovelFloats(test4);
		 */
		int[] test1 = { 1, 2, 5, 4, 6, 4, 7, 8, 9, 10, 11, 12 };
		int[] far = findLongestIncreasingSequence(test1);
		System.out.print("{");
		for (int i = 0; i < far.length; i++) {
			System.out.print(far[i] + " ");

		}
		System.out.print("}");

	}

	public static int findBestFloat(int[] heights) {
		int currIndex = 1;
		int greatestHeightChange = 0;
		int currHeight = 0;
		for (int i = 1; i < heights.length; i++) {
			currHeight = heights[i] - heights[i - 1];
			if (greatestHeightChange < currHeight) {
				greatestHeightChange = currHeight;
				currIndex = i;
			}
		}
		return currIndex;

	}

	public static int findTallestGroup(int[] heights) {
		double largestAverage = 0;
		double currAverage = 0;
		int currIndex = 0;
		for (int i = 0; i <= heights.length - 5; i++) {
			for (int j = i; j < i + 5; j++) {

				currAverage += heights[j];

			}
			currAverage /= 5.0;
			if (largestAverage < currAverage) {
				largestAverage = currAverage;
				currIndex = i + 2;
			}
			currAverage = 0;
		}

		return currIndex;
	}

	public static void findNovelFloats(int[] heights) {
		int currHeight = 0;

		for (int i = 0; i < heights.length; i++) {
			if (currHeight < heights[i]) {
				currHeight = heights[i];
				System.out.println(i);
			}
		}
	}

	public static int[] findLongestIncreasingSequence(int[] heights) {
		int biggestLength = 0;
		int currLength = 0;
		int currIndex = 0;
		int[] largestSequence = { 0, 0 };
		for (int i = 1; i < heights.length; i++) {
			int height1 = heights[i - 1];
			int height2 = heights[i];
			if (height1 < height2) {
				currLength++;
			} else {
				currLength = 0;
			}

			if (biggestLength < currLength) {
				biggestLength = currLength;
				currIndex = i;
			}

		}
		largestSequence[0] = currIndex - biggestLength;
		largestSequence[1] = currIndex;
		return largestSequence;

	}

}
