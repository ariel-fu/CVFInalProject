import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		String[] x = { "A", "C", "d", "d", "e", "s", "e", "e" };
		String y = findMostFrequent(x);
		System.out.println(y);
		int es = 0;
		es++;
		es++;
		es++;
		System.out.println(es);
		int[] onlineSales = { 4, 3, 1, 4, 3, 9 };
		int onlineSalesSize = 6;
		onlineSalesSize = removeFirst(onlineSales, onlineSalesSize, 4);
		onlineSalesSize = removeFirst(onlineSales, onlineSalesSize, 4);
		System.out.println(Arrays.toString(onlineSales));

		Scanner scnr = new Scanner(System.in);
		int sum = readInt(scnr) + readInt(scnr);

	}

	public static int readInt(Scanner scnr) {

		return scnr.nextInt();
	}

	public static String findMostFrequent(String[] arrayReference) {
		String mostCommon;
		int times; // The number of times the current element has appeared previously
		int mostTimes; // The number of times the most common element has appeared
		int index;

		// Zero length arrays don't contain anything
		if (arrayReference.length == 0) {
			return null;
		}

		// Sort the array
		Arrays.sort(arrayReference);

		mostCommon = arrayReference[0]; // Make sure method works with 1-element arrays
		times = 0;
		mostTimes = 0;

		// Start loop at one in order to use index - 1
		for (index = 1; index < arrayReference.length; ++index) {

			// The next element matches the last one
			if (arrayReference[index - 1].equals(arrayReference[index])) {
				++times;
			} else { // new element was found, restart the count
				times = 0;
			}

			// Check to see if old element was most common so far
			if (times > mostTimes) {
				mostTimes = times;
				mostCommon = arrayReference[index - 1];
			}
		}

		return mostCommon;
	}

	public static int removeFirst(int[] arrayRef, int arraySize, int targetVal) {
		boolean targetFound;
		int index;

		targetFound = false;

		// Step through the array one element at a time
		for (index = 0; index < arraySize; ++index) {
			// If matching element found, move each element to the previous index
			if (targetFound) {
				arrayRef[index - 1] = arrayRef[index];
			}

			// Check if matching element found
			if (arrayRef[index] == targetVal) {
				targetFound = true;
			}
		}

		// If matching element found, array size is one element smaller
		// otherwise array size hasn't changed
		if (targetFound) {
			return arraySize - 1;
		} else {
			return arraySize;
		}
	}

}
