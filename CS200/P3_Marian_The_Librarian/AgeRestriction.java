package P3_Marian_The_Librarian;

//author: Ariel Fu
//Net ID: afu5
//Student ID: 908 168 5910
public class AgeRestriction {

	public static String NOBOOK = "You cannot borrow any books.";
	public static String SOMEBOOK = "You cannot borrow sensitive material.";
	public static String ANYBOOK = "You can borrow any books!";
	public static String INVALID = "INVALID AGE";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("Expected: true --> " + isOver13(14));
		// System.out.println("Expected: false --> " + isOver13(12));
		// System.out.println("Expected: true --> " + isOver13(13));
		// System.out.println("Expected: false --> " + isOver13(0));

	}

	public static boolean isOver13(int age) {
		// make a variable to change due to a condition and return this var
		boolean oldEnough = false;
		if (age >= 13) {
			// if their age is 13 or older, they are old enough to borrow books
			oldEnough = true;
		}
		// return if their are old enough or not
		return oldEnough;
	}

	public static boolean isValid(int age) {
		if (age > 100) {
			return false;
		}
		if (age < 0) {
			return false;
		}
		return true;
	}

	public static void whatCanIBorrow(int age) {
		System.out.println(whatCanIBorrowString(age));
	}

	// created a test case method to test the void method!
	public static String whatCanIBorrowString(int age) {

		if (!isValid(age)) {
			return INVALID;
		}

		else if (!isOver13(age)) {
			// if age is under 13, they cannot borrow any books
			return NOBOOK;

		}

		else if (age >= 13 && age < 18) {
			// if they are 13 or older but under 18, they can borrow specific
			// books
			return SOMEBOOK;

		}

		else if (age >= 18 && age <= 100) {
			// if they are older than 18, they can borrow all books
			return ANYBOOK;

		}

		return "";
	}

}
