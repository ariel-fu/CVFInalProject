package P4;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class LessSimplePattern {

	public static void main(String[] args) {
		int count = 0;
		for (int i = 1; i <= 93; i++) {
			switchColors(i);
			count++;
		}
		colorCounter(count);

	}

	public static void colorCounter(int numLoops) {
		
		int redCount = 0;
		int yellowCount = 0;
		int greenCount = 0;
		int brownCount = 0;

		// run through as many times as neccesary
		for (int i = 1; i <= numLoops; i++) {
			int modFour = i%4;
			// since it starts at 1, every "red" pattern moded by 4 is equal to
			// one!
			if (modFour == 1) {
				// add one to red count
				redCount++;
			}

			// every yellow pattern moded by 4 is equal to two
			if (modFour == 2) {
				// add one to yellow
				yellowCount++;
			}

			// every green pattern moded by 4 is equal to 3!
			if (modFour == 3) {
				// add one to green count
				greenCount++;
			}

			// every brown pattern is a multiple of 4 so %4 = 0
			if (modFour == 0) {
				// add one to brown count
				brownCount++;
			}
		}
		// print out the total count for each one
		System.out.println("total red: " + redCount);
		System.out.println("total yellow: " + yellowCount);
		System.out.println("total green: " + greenCount);
		System.out.println("total brown: " + brownCount);

	}

	public static void switchColors(int numLoops) {
		int modFour = numLoops%4;
		// since it starts at 1, every "red" pattern moded by 4 is equal to one!
		if (modFour == 1) {
			// print loop number and red
			System.out.println(numLoops + ") red");

		}

		// every yellow pattern moded by 4 is equal to two
		if (modFour == 2) {
			// print out loop number and yellow
			System.out.println(numLoops + ") yellow");
		}

		// every green pattern moded by 4 is equal to 3!
		if (modFour == 3) {
			System.out.println(numLoops + ") green");
		}

		// every brown pattern is a multiple of 4 so %4 = 0
		if (modFour == 0) {
			System.out.println(numLoops + ") brown");
		}
	}

}
