package P4;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class VerySimplePattern {
	public static void main(String[] args) {
		// run 71 times to print out 71 lines.
		for (int i = 1; i <= 71; i++) {
			// use method evenOrOdd to change color every other
			evenOrOdd(i);
		}

	}

	public static void evenOrOdd(int x) {
		if (x % 2 != 0) {
			// mod 2 will not be 0 if odd
			System.out.println(x + ") red");
		}

		else {
			// otherwise print yellow
			System.out.println(x + ") yellow");
		}
	}
}
