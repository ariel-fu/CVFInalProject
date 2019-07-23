/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Parade {

	public static void main(String[] args) {

		int[] test1 = { 4, 5, 2, 5, 4 };
		int[] test2 = { 1, 5, 10, 1 };
		int[] test4 = new int[] { 1, 10, 22, 40, 100, 40000, 40001 };
		int[] test3 = new int[] { 1, 1, 1, 1 };
		System.out.println("3 --> " + findBestFloat(test1));
		System.out.println("2 --> " + findBestFloat(test2));
		System.out.println("5 --> " + findBestFloat(test4));
		System.out.println("0" + " --> " + findBestFloat(test3));
		System.out.println("End of findBestFloat testing -----------------------");
		System.out.println("");

		int[] test3B = { 500, 100, 200, 300, 200, 4, 3, 2, 1, 500, 200, 300, 100, 201 };
		System.out.println("11 --> " + findTallestGroup(test3B));
		int[] test4B = {0,0,0,0,0,0,0};
		System.out.println("2 --> " + findTallestGroup(test4B));
		int[] test5B = {1,1,1,1,1,1};
		System.out.println("2 --> "+findTallestGroup(test5B));
		System.out.println("End of findTallestGroup testing -----------------------");
		System.out.println("");

		//base cases from prof
		int[] test1A = { 10, 5, 6, 7, 8, 11 };
		findNovelFloats(test1A);
		System.out.println("");
		int[] test2A = { 1, 2, 3, 4, 5, 5 };
		findNovelFloats(test2A);
		System.out.println("");
		int[] test3A = { 10, 9, 10 };
		findNovelFloats(test3A);
		System.out.println("");
		int[] test4A = { 2, 5, 4, 8, 6, 7, 8, 10 };
		findNovelFloats(test4A);
		System.out.println("");
		int[] test13 = { 0, 0, 0 };
		findNovelFloats(test13);
		System.out.println("");
		//all increasing
		int[] test5A = {1,2,3,4,5,6,7};
		findNovelFloats(test5A);
		System.out.println("");
		//all decreasing
		int[] test6a = {7,6,5,4,3,2,1,0};
		findNovelFloats(test6a);
		System.out.println("");
		//increasing at beginning
		int[] test7a = {1,5,9,12,10,8,2};
		findNovelFloats(test7a);
		System.out.println("");
		//increasing at end
		int[] test8a = {5,4,2,6,9,84,259};
		findNovelFloats(test8a);
		
		System.out.println("End of findNovelFloats testing -----------------------");
		System.out.println("");

		//follows same testing pattern as findNovelFloats testing
		int[] test1C = { 1, 2, 5, 4, 6, 4, 7, 8, 9, 10, 11, 12 };
		int[] far = findLongestIncreasingSequence(test1C);
		printArray(far);
		System.out.println();
		int[] test2C = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] far1 = findLongestIncreasingSequence(test2C);
		printArray(far1);
		System.out.println();
		int[] test1D = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] fa4 = findLongestIncreasingSequence(test1D);
		System.out.println();
		int[] test1E = {1,2,3,1,2,3,1,2,3};
		int[] far2 = findLongestIncreasingSequence(test1E);
		printArray(far2);
		System.out.println();
		int[] test23 = {1,2,3,4,8,9,24};
		int[] ew = findLongestIncreasingSequence(test23);
		printArray(ew);
		System.out.println();
		int[] test32 = {1,2,3,4,8,9,2,1};
		int[] ewwdf = findLongestIncreasingSequence(test32);
		printArray(ewwdf);
		System.out.println();
		int[] test239 = {5,3,2,1,9,81,259};
		int[] ewlfedk = findLongestIncreasingSequence(test239);
		printArray(ewlfedk);
		System.out.println();
		System.out.println("End of findTallestGroup testing -----------------------");
		
	}

	
	public static int findBestFloat(int[] heights) {
		// set the current index to be one because the first float will never be
		// the
		// best
		int currIndex = 0;
		int greatestHeightChange = 0;

		// loop through every element in the array
		for (int i = 1; i < heights.length; i++) {
			// the current height change is the elements at i minus (i-1)
			int currHeight = heights[i] - heights[i - 1];
			// if the current height change is greater than the greatest height
			// change so
			// far
			// then change the greatest height change so far to the current
			// height change
			// and the current index of the best float to i
			if (greatestHeightChange < currHeight) {
				greatestHeightChange = currHeight;
				currIndex = i;
			}
		}
		// return the index of the float with the greatest height change!
		return currIndex;

	}


	public static int findTallestGroup(int[] heights) {
		double largestAverage = -1;
		double currAverage = 0;
		int currIndex = 0;
		// loop through every element until there are five spots at the end to
		// compensate for the second for loop, that loops through i to i+4,
		// which computes the total sum from i to i+4 (a group of 5)
		for (int i = 0; i <= heights.length - 5; i++) {
			for (int j = i; j < i + 5; j++) {

				currAverage += heights[j];

			}
			// divide by 5 to get the average
			currAverage /= 5.0;
			// if the current average is greater than the largest average so
			// far,
			// set the largest average to the current average and set the index
			// of the
			// largest average to the current index+2, to get the middle index
			// of the group
			// of 5
			if (largestAverage < currAverage) {
				largestAverage = currAverage;
				currIndex = i + 2;
			}
			// set the current average back to 0
			currAverage = 0;
		}
		// return the middle index of the group of 5 with the largest index
		return currIndex;
	}


	public static void findNovelFloats(int[] heights) {
		int tallestHeightSoFar = -1;
		// run through every elements to find which element is taller than all
		// the floats that came before it

		for (int i = 0; i < heights.length; i++) {
			// if the tallest float so far is less than the current height set
			// the tallest
			// height
			// to the current height and print out the index of the current
			// height
			if (tallestHeightSoFar < heights[i]) {
				tallestHeightSoFar = heights[i];
				System.out.println(i);
			}
		}
	}

	public static int[] findLongestIncreasingSequence(int[] heights) {
		int biggestLength = 0;
		int currLength = 0;
		int currIndex = 0;
		// we will plug in the starting index of the longest increasing sequence
		// and the
		// ending index of the longest increasing sequence into this array and
		// return it
		int[] largestSequence = { 0, 0 };
		// start the for loop at i=1 so then we can compare i and i-1 (the
		// previous
		// element)
		for (int i = 1; i < heights.length; i++) {
			int height1 = heights[i - 1];
			int height2 = heights[i];
			// if the heights are increasing, add one to the current length
			if (height1 < height2) {
				currLength++;
			} else {
				// otherwise, reset the current length
				currLength = 0;
			}
			// test to find the biggest length and set the finishing index to
			// the current
			// index
			if (biggestLength < currLength) {
				biggestLength = currLength;
				currIndex = i;
			}

		}
		// the starting index will be the finishing index - the length of the
		// sequence
		largestSequence[0] = currIndex - biggestLength;
		largestSequence[1] = currIndex;
		return largestSequence;

	}

	public static void printArray(int[] a) {
		System.out.print("{");
		for (int i = 0; i < a.length - 1; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.print(a[a.length - 1] + "}");
	}

}
