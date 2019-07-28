import java.util.Scanner;

//TO ASK: 
//- what to return
//- divide by 0, is infinity or NaN okay? 
//- sqrt of negative numbers 
//- log of 0 or negative numbers
//- sum of extremely large numbers i.e. 10E100 10E1000 10E1000000
//- can array print out with brackets and commas?
public class DefinitelyNotACalculator {

	public static String PLUS = "+";
	public static String SUBSTRACT = "-";
	public static String MULTIPLY = "*";
	public static String DIVIDE = "/";
	public static String MAX = "max";
	public static String LOG = "log";
	public static String ABS = "abs";
	public static String POW = "pow";
	public static String SQRT = "sqrt";
	public static String RAND = "rand";
	public static String SUM = "sum";
	public static String SORT = "sort";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String stringInputed = "";
		do {

			System.out.println("Please input an expression: ");
			stringInputed = sc.nextLine();
			stringInputed.toLowerCase();
			String[] splitString = stringInputed.split(" ");

			String operator = splitString[0];

			// TODO

		} while (!stringInputed.equals("quit"));

		System.out.println("Have a nice day, Mr. Finch!");
		sc.close();

	}

	public static double calculate(String[] splitString) {

		String operator = splitString[0];
		if (operator.equals(PLUS)) {
			return addNumber(splitString);
		} else if (operator.equals(SUBSTRACT)) {
			return subtract(splitString);
		} else if (operator.equals(MULTIPLY)) {
			return multiply(splitString);
		} else if (operator.equals(DIVIDE)) {
			return divide(splitString);
		} else if (operator.equals(MAX)) {
			return max(splitString);
		} else if (operator.equals(ABS)) {
			return abs(splitString);
		} else if (operator.equals(LOG)) {
			return log(splitString);
		} else if (operator.equals(POW)) {
			return pow(splitString);
		} else if (operator.equals(SQRT)) {
			return sqrt(splitString);
		} else if (operator.equals(RAND)) {
			return rand(splitString);
		} else if (operator.equals(SUM)) {
			return sum(splitString);
		}
		return 0;
//sort can't be in here since it has to print. so sort is a condition in main.
	}

	public static boolean isValidArgument(String[] splitString) {
		String operator = splitString[0];

		if (operator.equals(PLUS)) {
			return isTwoNumbers(splitString);
		} else if (operator.equals(SUBSTRACT)) {
			return isTwoNumbers(splitString);
		} else if (operator.equals(MULTIPLY)) {
			return isTwoNumbers(splitString);
		} else if (operator.equals(DIVIDE)) {
			return isTwoNumbers(splitString);
		} else if (operator.equals(MAX)) {
			return isTwoNumbers(splitString);
		} else if (operator.equals(ABS)) {
			return isOneNumber(splitString);
		} else if (operator.equals(LOG)) {
			return isOneNumber(splitString);
		} else if (operator.equals(POW)) {
			return isTwoNumbers(splitString);
		} else if (operator.equals(SQRT)) {
			return isOneNumber(splitString);
		} else if (operator.equals(RAND)) {
			return isValidRandArgument(splitString);
		} else if (operator.equals(SUM)) {
			return isValidEndlessArgument(splitString);
		} else if (operator.equals(SORT)) {
			return isValidEndlessArgument(splitString);
		}

		return false;
	}

	// only used for sum and sort
	public static boolean isValidEndlessArgument(String[] splitString) {
		return true;
	}

	public static boolean isValidRandArgument(String[] splitString) {
		return true;
	}

	public static boolean isInt(double val) {
		return true;
	}

	// two numbers checks if the length is three and if both the doubles are numeric
	// or not
	public static boolean isTwoNumbers(String[] splitString) {
		return true;
	}

//one number checks if the length is two, and if the double is numeric	
	public static boolean isOneNumber(String[] splitString) {
		return true;
	}

	public static boolean isValidOperator(String operator) {
		// +
		if (operator.equals(PLUS)) {
			return true;
		}

		// -
		if (operator.equals(SUBSTRACT)) {
			return true;
		}

		// *
		if (operator.equals(MULTIPLY)) {
			return true;
		}

		// /
		if (operator.equals(DIVIDE)) {
			return true;
		}

		// max
		if (operator.equals(MAX)) {
			return true;
		}

		// log
		if (operator.equals(LOG)) {
			return true;
		}

		// abs
		if (operator.equals(ABS)) {
			return true;
		}

		// pow
		if (operator.equals(POW)) {
			return true;
		}

		// sqrt
		if (operator.equals(SQRT)) {
			return true;
		}

		// rand
		if (operator.equals(RAND)) {
			return true;
		}

		// sum
		if (operator.equals(SUM)) {
			return true;
		}

		// sort
		if (operator.equals(SORT)) {
			return true;
		}

		return false;

	}

	// create another double array to sort and print out all the numbers
	public static void sort(String[] splitString) {

		double val1 = 0;
		double[] doubleArray = new double[splitString.length - 1];
		double[] result = new double[1];
		for (int i = 1; i < splitString.length; i++) {
			if (isNumeric(splitString[i], result)) {
				val1 = result[0];
			}
			doubleArray[i - 1] = val1;
		}

		// otherwise look through every array element to see which one is out of order
		for (int i = 0; i < doubleArray.length; i++) {
			for (int j = 0; j < doubleArray.length - 1; j++) {
				// when we find an element out of order, swap it with the one smaller than it!

				if (doubleArray[j + 1] < doubleArray[j]) {
					double replace = doubleArray[j + 1];
					doubleArray[j + 1] = doubleArray[j];
					doubleArray[j] = replace;
				}
			}
		}

		for (int i = 0; i < doubleArray.length; i++) {
			System.out.print(doubleArray[i] + " ");
		}
	}

	public static double sum(String[] splitString) {
		double sum = 0;
		double val1 = 0;

		double[] result = new double[1];
		for (int i = 1; i < splitString.length; i++) {
			if (isNumeric(splitString[i], result)) {
				val1 = result[0];
			}
			sum += val1;
		}

		return sum;

	}

	// if it is a one number input, use the randOne, and same for two number input
	public static double rand(String[] splitString) {
		return 0;
	}

	// shifts Math.random by val1

	public static double randOne(String[] splitString) {
		return 0;
	}

	// shift Math.Random by the difference between the max and the min then add the
	// min
	public static int randTwo(String[] splitString) {
		return 0;
	}

	public static double sqrt(String[] splitString) {
		return 0;
	}

	public static double pow(String[] splitString) {
		return 0;
	}

	public static double abs(String[] splitString) {
		return 0;
	}

	public static double log(String[] splitString) {
		return 0;
	}

	public static double max(String[] splitString) {
		return 0;
	}

	public static double divide(String[] splitString) {
		return 0;
	}

	public static double multiply(String[] splitString) {
		return 0;
	}

	public static double subtract(String[] splitString) {
		return 0;
	}

	public static double addNumber(String[] splitString) {
		return 0;
	}

	public static boolean isNumeric(String string, double[] result) {
		return true;
	}

}
