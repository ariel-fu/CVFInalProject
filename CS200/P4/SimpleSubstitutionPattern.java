package P4;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class SimpleSubstitutionPattern {

	public static void main(String[] args) {
		// run a for loop 121 times
		for (int i = 1; i <= 121; i++) {
			changeColor(i);
		}
	}

	public static void changeColor(int x) {
		// test if it is divisible by 3
		if (x % 3 == 0) {
			// if true, print out silver
			System.out.println(x + ") silver");
		}
		// check if it is divisible by 2
		else if (x % 2 == 0) {
			// if true, print out yellow
			System.out.println(x + ") yellow");
		}

		else {
			// print out red otherwise
			System.out.println(x + ") red");
		}
	}

}
