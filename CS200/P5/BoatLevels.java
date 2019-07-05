/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class BoatLevels {

	public static void main(String[] args) {
		// print out every sequence from 2-200
		for (int i = 2; i <= 200; i++) {
			printBoatLevels(i);
			// print a new line
			System.out.println("");
		}

	}

	// checks if the number is an even number or an odd number
	public static int evenOrOdd(int numberBet) {
		int newNumber = numberBet;
		// if it is an even number, divide by 2
		if (newNumber % 2 == 0) {
			newNumber = numberBet / 2;
		} else if (newNumber % 2 != 0) {
			// if it is an odd number, multiply by 3 and add 1
			newNumber = (numberBet * 3) + 1;
		}
		// return the new number
		return newNumber;
	}

	public static void printBoatLevels(int n) {
		// print the initial bet
		System.out.println("Initial Bet: $" + n);
		// print Level sequence and the initial bet
		System.out.print("Level Sequence: " + n + ", ");
		// make a variable to hold the new numbers
		int x = evenOrOdd(n);
		// while the the water level is not 1, add it to the print sequence and
		// check again
		while (x != 1) {
			// adds it to the print sequence
			System.out.print(x + ", ");
			// checks again to make sure it doesn't change to 1
			x = evenOrOdd(x);
		}
		// after it becomes 1 and quits the while loop, add 1 to the print
		// sequence
		System.out.print("1");
	}

}
