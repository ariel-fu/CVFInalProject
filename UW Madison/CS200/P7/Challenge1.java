/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */

public class Challenge1 {

	public static void main(String[] args) {
		// print out the sum of the digits for every number from 2-100,000
		for (int i = 1; i <= 100000; i++) {
			System.out.println("The sum of the digits of " + i + " is: " + computeDigitSum(i));
		}

	}

	public static int computeDigitSum(int n) {
		/*
		 * start by finding the remainder after dividing by 10 aka the first digit of
		 * "n"
		 */
		int sum = n % 10;
		if (n < 10) {
			/*
			 * if "n" is less than 10, return that number there is no need to add anything,
			 * for obvious reasons :))
			 */
			return n;
		} else {
			/*
			 * return the digit of the ones, and compute the digit of the tens then the
			 * hundreds, until it n becomes less than 10
			 */
			return sum + computeDigitSum(n / 10);
		}
	}

}
