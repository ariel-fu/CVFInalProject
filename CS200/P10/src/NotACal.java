import java.util.Arrays;
import java.util.Scanner;

//TO ASK: 
//- what to return
//- divide by 0, is infinity or NaN okay? 
//- sqrt of negative numbers 
//- log of 0 or negative numbers
//- sum of extremely large numbers i.e. 10E100 10E1000 10E1000000
//- can array print out with brackets and commas?
public class NotACal {

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
			stringInputed.strip();

			do {
				stringInputed = stringInputed.replace("  ", " ");
			} while (stringInputed.contains("  "));

			String[] splitString = stringInputed.split(" ");

			String operator = splitString[0];

			if (operator.equals("quit")) {
				break;
			}

			// TODO add sort,sum
			if (!isValidOperator(operator)) {
				System.out.println("Error! Command not found.");
				continue;
			}

			// TODO add sort,sum
			if (!isValidArgument(splitString)) {
				System.out.println("Error! invalid arguments.");
				continue;
			}

			// TODO add sort,sum
			if (operator.equals(SORT)) {
				sort(splitString);
				System.out.println();
			} else {
				System.out.println(calculate(splitString));
			}
		} while (!stringInputed.equals("quit"));

		System.out.println("Have a nice day, Mr. Finch!");
		sc.close();

	}

	public static double calculate(String[] splitString) {
		// method where we put in other methods to calculate
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
		// sort can't go into calculate, it has to be in main as a condition
	}

	public static boolean isValidArgument(String[] splitString) {
		String operator = splitString[0];
		// checks which ones use two inputs, which ones uses one input
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

	public static boolean isValidEndlessArgument(String[] splitString) {
		// this one is for sum and sort
		return true;
	}

	public static boolean isValidRandArgument(String[] splitString) {
		// if there is only one number in the argument, check if it is a double and if
		// so true
		if (isOneNumber(splitString)) {
			return true;
		} else if (isTwoNumbers(splitString)) {
			// parse both to a double and check if both are ints
			double val1 = 0;
			double val2 = 0;
			double[] result = new double[1];

			if (isNumeric(splitString[1], result)) {
				val1 = result[0];
			}

			if (isNumeric(splitString[2], result)) {
				val2 = result[0];
			}

			if (isInt(val1) && isInt(val2)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInt(double val) {
		// cast to an int and if val is an int, it would be equal to val casted to an
		// int
		int test = (int) val;
		return (val == test);
	}

	public static boolean isTwoNumbers(String[] splitString) {
		// if the length is three, and both 1 & 2 are numberic, it is a valid two number
		// input
		double[] result = { 0 };

		if (splitString.length != 3) {
			return false;
		}
		if (!isNumeric(splitString[1], result)) {
			return false;
		}

		if (!isNumeric(splitString[2], result)) {
			return false;
		}

		return true;
	}

	public static boolean isOneNumber(String[] splitString) {
		double[] result = { 0 };
		if (splitString.length != 2) {
			return false;
		}
		if (!isNumeric(splitString[1], result)) {
			return false;
		}

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

	public static void sort(String[] splitString) {
		// parse the String array into a double array to sort
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
			// parses the element at i to a double and adds it on to the total sum
			if (isNumeric(splitString[i], result)) {
				val1 = result[0];
			}
			sum += val1;
		}

		return sum;

	}

	public static double rand(String[] splitString) {
//checks if it is one number or two, and returns the corresponding random method
		if (isOneNumber(splitString)) {
			return randOne(splitString);
		} else if (isTwoNumbers(splitString)) {
			return randTwo(splitString);
		}
		return 0;
	}

	public static double randOne(String[] splitString) {
		// randOne is a double, and only takes in one input
		// so check if numeric once and if it is, shift it by val1
		double val1 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}
		return val1 * Math.random();
	}

	public static int randTwo(String[] splitString) {
		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}

		return (int) (((val2 - val1) * Math.random()) + val1);
	}

	public static double sqrt(String[] splitString) {
		double val1 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}
		return Math.sqrt(val1);

	}

	public static double pow(String[] splitString) {
		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}
		return Math.pow(val1, val2);
	}

	public static double abs(String[] splitString) {
		double val1 = 0;

		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (val1 > 0) {
			return val1;
		} else {
			return val1 * (-1);
		}

	}

	public static double log(String[] splitString) {
		double val1 = 0;

		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		return Math.log10(val1);

	}

	public static double max(String[] splitString) {

		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}

		if (val1 >= val2) {
			return val1;
		} else if (val2 > val1) {
			return val2;
		} else {
			return 0;
		}

	}

	public static double divide(String[] splitString) {
		// TODO Auto-generated method stub
		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}

		return val1 / val2;
	}

	public static double multiply(String[] splitString) {
		// TODO Auto-generated method stub
		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}

		return val1 * val2;
	}

	public static double subtract(String[] splitString) {
		// TODO Auto-generated method stub
		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}

		return val1 - val2;
	}

	public static double addNumber(String[] splitString) {
		// TODO Auto-generated method stub
		double val1 = 0;
		double val2 = 0;
		double[] result = new double[1];

		if (isNumeric(splitString[1], result)) {
			val1 = result[0];
		}

		if (isNumeric(splitString[2], result)) {
			val2 = result[0];
		}

		return val1 + val2;
	}

	public static boolean isNumeric(String string, double[] result) {
		try {
			double test = Double.parseDouble(string);
			result[0] = test;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
