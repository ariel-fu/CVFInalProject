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

	//checks if even or odd
	public static boolean evenOrOdd(int numberBet) {
		// if it is an even number, return true
		if (numberBet % 2 == 0) {
			return true;
		} else {
			//return false if it is not an odd number
			return false;
		}
	}


	public static void printBoatLevels(int n) {
		// print the initial bet
		System.out.println("Initial Bet: $" + n);
		// print Level sequence and the initial bet
		System.out.print("Level Sequence: " + n + ", ");
		// make a variable to hold the new numbers
		int x = changeValueOfANumber(n);
		// while the the water level is not 1, add it to the print sequence and
		// check again
		while (x != 1) {
			// adds it to the print sequence
			System.out.print(x + ", ");
			// checks again to make sure it doesn't change to 1
			x = changeValueOfANumber(x);
		}
		// after it becomes 1 and quits the while loop, add 1 to the print
		// sequence
		System.out.print("1");
	}
	
	//changes the value of the number based on if it was even or odd
	public static int changeValueOfANumber(int num){
		if(evenOrOdd(num)){
			//if even, divide by 2
			return num/2;
		} else{
			//if odd, multiply by 3 and add one
			return (num*3)+1;
		}
	}

}
