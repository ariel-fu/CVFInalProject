<<<<<<< HEAD
/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Challenge30 {

	public static void main(String[] args) {
		for (int i = 2; i <= 10; i++) {
			System.out.println("is " + i + " prime? " + isPrime(i));
		}

		for (int i = 2; i < 100; i++) {
			System.out.println("The factorization of " + i + " is: " + factor(i));
		}
	}

	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}

		}
		return true;
	}

	public static String factor(int n) {
		if (isPrime(n)) {
			return ""+n ;
		}

		else {
			for(int i =2; i<100000; i++) {
				if(isPrime(i) && (n%i==0)) {
				return i+"*" +factor(n/i);
				}
			}
		}
		return "";

	}

}
=======
/* 
- boolean isPrime(int n) (2 points)
– String factor(int n) (6 points)
– main (2 points)
*/
>>>>>>> branch 'master' of https://github.com/alvickyfun/Ariel.git
