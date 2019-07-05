/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class BoatBestBest {

	public static void main(String[] args) {
		int bestBet = 0;
		int greatestProfit = 0;
		int numHeightChanges = 0;
		int currentProfit = 0;

		for (int bet = 2; bet <= 10; bet++) {
			System.out.println("Initial Bet: $" + bet);

			// compute the number of height changes and print
			numHeightChanges = computeNumberOfChanges(bet);
			System.out.println("Number of Height Changes: " + numHeightChanges);

			// compute the profit from each bet and print
			currentProfit = computeCurrentProfit(bet, numHeightChanges);
			System.out.println("Total Profit: $" + currentProfit);

			// compare each profit and print the largest
			if (currentProfit > greatestProfit) {
				// change greatest profit to the current profit
				greatestProfit = currentProfit;
				// change best bet to current bet
				bestBet = bet;
			}
			System.out.println("");

		}
		System.out.println("Sky's best bet is $" + bestBet
				+ ", which would earn him a profit of $" + greatestProfit);
	}

	// checks if even or odd
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

	public static int computeNumberOfChanges(int n) {
		// make a variable to hold the new numbers
		int x = evenOrOdd(n);
		// make a variable to hold number of height changes
		int numHeightChanges = 0;
		// while the the water level is not 1, add it to the print sequence and
		// check again
		while (x != 1) {
			// add one to the number of height changes
			numHeightChanges++;
			// checks again to make sure it doesn't change to 1
			x = evenOrOdd(x);
		}
		numHeightChanges++;
		return numHeightChanges;

	}

	// calculates the profit made from the bet
	public static int computeCurrentProfit(int numberBet, int numHeightChanges) {
		int profit = numHeightChanges - numberBet;
		return profit;
	}

}
