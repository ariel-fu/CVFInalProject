/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Challenge30 {

	public static void main(String[] args) {
		// print out numbers from 2-100,000
		for (int i = 2; i < 100001; i++) {
			System.out.println("The factorization of " + i + " is: " + factor(i));
		}
	}

	public static boolean isPrime(int n) {
		/*
		 * if n is not a prime number, there will be at least one number between 2 and n
		 * that could divide n.
		 */
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
			/*
			 * if i^2 is greater than n, then there will be no more numbers to test because
			 * n would already be divisible by a number
			 */
			if (i * i > n) {
				break;
			}

		}
		return true;
	}

	public static String factor(int n) {
		/*
		 * if "n" is a prime number, concat it a string and return "n"
		 */
		if (isPrime(n)) {
			return "" + n;
		}

		/*
		 * otherwise, run through all the numbers from 2 to n if "i" is a prime number
		 * and "n" is divisible, return "i" (the first factor of "n") and factor the
		 * (new) "n"... running through again.
		 */

		else {
			for (int i = 2; i < n; i++) {
				if (isPrime(i) && (n % i == 0)) {
					return i + "*" + factor(n / i);
				}
			}
		}
		// return a empty String, because all my return statements are
		// inside conditional statements!!!
		return "";

	}

}