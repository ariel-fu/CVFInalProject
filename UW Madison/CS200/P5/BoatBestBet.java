/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class BoatBestBet {

	public static void main(String[] args) {
		int bestBet = 0;
		int greatestProfit = 0;
		int numHeightChanges = 0;
		int currentProfit = 0;

		for (int bet = 2; bet <= 200; bet++) {
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



	public static int computeNumberOfChanges(int n) {
		// make a variable to be whether "n" is even or odd
		int x = changeValueOfANumber(n);
		// make a variable to hold number of height changes
		int numHeightChanges = 0;
		// while the the water level is not 1, add it to the print sequence and
		// check again
		while (x != 1) {
			// add one to the number of height changes
			numHeightChanges++;
			// checks again to make sure it doesn't change to 1
			x = changeValueOfANumber(x);
		}
		//add one for 1, then return the number of height changes
		numHeightChanges++;
		return numHeightChanges;

	}
	
	// calculates the profit made from the bet
		public static int computeCurrentProfit(int numberBet, int numHeightChanges) {
			int profit = numHeightChanges - numberBet;
			return profit;
		}

		//under is copied from BoatLevels.java
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
		
		// checks if the number is an even number or an odd number
		public static boolean evenOrOdd(int numberBet) {
		
			// if it is an even number, return true
			if (numberBet % 2 == 0) {
				return true;
			} else {
				//return false if it is not an odd number
				return false;
			}
		}
	
	
	
	

}
