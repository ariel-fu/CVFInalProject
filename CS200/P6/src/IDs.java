/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class IDs {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println("The number of " + i + "-digit IDs is: " + countIDs(i));
		}

	}

	public static int countIDs(int numDigits) {
		if (numDigits == 0) {
			return 1;
		} else if (numDigits == 1) {
			return 8;
		} else {
			int startsWithOne = 1 * 8 * countIDs(numDigits - 2);
			int startsWith7 = 1 * 2 * countIDs(numDigits - 2);
			int other = 7 * countIDs(numDigits - 1);

			// number that start with ones... number that start with 7s...numbers that start
			// with neither
			return startsWithOne + startsWith7 + other;
		}
	}

}
