
/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Honey {
	public static void main(String[] args) {
		System.out.println(numMoves(2) + " should return 6");
		System.out.println(numMoves(3) + " should return 12");
		System.out.println(numMoves(4) + " should return 20");
		System.out.println(numMoves(5) + " should return 30");
		System.out.println(numMoves(6) + " should return 42");

	}

	public static int numMoves(int n) {
		// base case is if n is equal to one
		if (n == 1) {
			return 2;
		} else
			// the formula is two times n and then plus the previous number
			return (2 * n) + numMoves(n - 1);
	}

}
