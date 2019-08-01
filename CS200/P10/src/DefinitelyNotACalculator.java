
/*
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
*/

import java.util.Scanner;

public class DefinitelyNotACalculator {

	public static String PLUS = "+";
	public static String SUBTRACT = "-";
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
		System.out.println("Good day, Mr. Finch! Welcome to your totally-not-a-calculator.");

		Scanner sc = new Scanner(System.in);
		String stringInputed = "";
		do {

			System.out.println("Please input an expression: ");
			stringInputed = sc.nextLine();
			stringInputed.trim();
			String[] splitString = stringInputed.split(" ");
			
			String operator = splitString[0];
			operator.toLowerCase();
			if (operator.equalsIgnoreCase("quit")) {
				break;
			}
			if (!isValidOperator(operator)) {
				System.out.println("Error! Command not found.");
				continue;
			}
			if (!isValidArgument(splitString)) {
				System.out.println("Error! Incorrect amount of arguments");
				continue;
			}
			if (operator.equalsIgnoreCase(SORT)) {
				sort(splitString);
				System.out.println();
			} else {
				System.out.println(calculate(splitString));
			}
			// TODO

		} while (!stringInputed.equalsIgnoreCase("quit"));

		System.out.println("Have a nice day, Mr. Finch!");
		sc.close();

	}

	public static double calculate(String[] splitString) {

		String operator = splitString[0];
		if (operator.equalsIgnoreCase(PLUS)) {
			return addNumber(splitString);
		} else if (operator.equalsIgnoreCase(SUBTRACT)) {
			return subtract(splitString);
		} else if (operator.equalsIgnoreCase(MULTIPLY)) {
			return multiply(splitString);
		} else if (operator.equalsIgnoreCase(DIVIDE)) {
			return divide(splitString);
		} else if (operator.equalsIgnoreCase(MAX)) {
			return max(splitString);
		} else if (operator.equalsIgnoreCase(ABS)) {
			return abs(splitString);
		} else if (operator.equalsIgnoreCase(LOG)) {
			return log(splitString);
		} else if (operator.equalsIgnoreCase(POW)) {
			return pow(splitString);
		} else if (operator.equalsIgnoreCase(SQRT)) {
			return sqrt(splitString);
		} else if (operator.equalsIgnoreCase(RAND)) {
			return rand(splitString);
		} else if (operator.equalsIgnoreCase(SUM)) {
			return sum(splitString);
		}
		return 0;
//sort can't be in here since it has to print. so sort is a condition in main.
	}

	public static boolean isValidArgument(String[] splitString) {
		String operator = splitString[0];

		if (operator.equalsIgnoreCase(PLUS)) {
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(SUBTRACT)) {
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(MULTIPLY)) {
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(DIVIDE)) {
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(MAX)) {
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(ABS)) {
			return isOneNumber(splitString);
		} else if (operator.equalsIgnoreCase(LOG)) {
			return isOneNumber(splitString);
		} else if (operator.equalsIgnoreCase(POW)) {
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(SQRT)) {
			return isOneNumber(splitString);
		} else if (operator.equalsIgnoreCase(RAND)) {
			return isValidRandArgument(splitString);
		} else if (operator.equalsIgnoreCase(SUM)) {
			return isValidEndlessArgument(splitString);
		} else if (operator.equalsIgnoreCase(SORT)) {
			return isValidEndlessArgument(splitString);
		}

		return false;
	}

	// only used for sum and sort
	public static boolean isValidEndlessArgument(String[] splitString) {
		return true;
	}

	public static boolean isValidRandArgument(String[] splitString) {
		if (isOneNumber(splitString) || isTwoNumbers(splitString)) {
			if (isTwoNumbers(splitString)) {
				double var1 = 0;
				double var2 = 0;
				double[] result = new double[1];
				if (isNumeric(splitString[1], result)) {
					var1 = result[0];
				}
				if (isNumeric(splitString[2], result)) {
					var2 = result[0];
				}
				if (isInt(var1) && isInt(var2)) {
					return true;
				} else {
					return false;
				}
			}
			
			if(isOneNumber(splitString)) {
			return true;
			}
		}

		return false;

	}

	public static boolean isInt(double val) {
		int test = (int) val;
		return test == val;

	}

	// two numbers checks if the length is three and if both the doubles are numeric
	// or not
	public static boolean isTwoNumbers(String[] splitString) {
		int numWhiteSpaces=0;
		if(isWhiteSpace(splitString)) {
			numWhiteSpaces++;
		}
		if ((splitString.length-numWhiteSpaces) != 3) {
			return false;
		}
		double[] test = new double[1];
		if (!isNumeric(splitString[1], test)) {
			return false;
		}
		if (!isNumeric(splitString[2], test)) {
			return false;
		}
		return true;
	}

//one number checks if the length is two, and if the double is numeric	
	public static boolean isOneNumber(String[] splitString) {
	
		if (splitString.length!= 2) {
			return false;
		}
		double[] test = new double[1];
		if (!isNumeric(splitString[1], test)) {
			return false;
		}
		return true;

	}

	public static boolean isValidOperator(String operator) {
		// +
		if (operator.equalsIgnoreCase(PLUS)) {
			return true;
		}

		// -
		if (operator.equalsIgnoreCase(SUBTRACT)) {
			return true;
		}

		// *
		if (operator.equalsIgnoreCase(MULTIPLY)) {
			return true;
		}

		// /
		if (operator.equalsIgnoreCase(DIVIDE)) {
			return true;
		}

		// max
		if (operator.equalsIgnoreCase(MAX)) {
			return true;
		}

		// log
		if (operator.equalsIgnoreCase(LOG)) {
			return true;
		}

		// abs
		if (operator.equalsIgnoreCase(ABS)) {
			return true;
		}

		// pow
		if (operator.equalsIgnoreCase(POW)) {
			return true;
		}

		// sqrt
		if (operator.equalsIgnoreCase(SQRT)) {
			return true;
		}

		// rand
		if (operator.equalsIgnoreCase(RAND)) {
			return true;
		}

		// sum
		if (operator.equalsIgnoreCase(SUM)) {
			return true;
		}

		// sort
		if (operator.equalsIgnoreCase(SORT)) {
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
		if (isOneNumber(splitString)) {
			return randOne(splitString);
		}
		if (isTwoNumbers(splitString)) {
			return randTwo(splitString);
		}
		return 0;
	}

	// shifts Math.random by val1

	public static double randOne(String[] splitString) {
		double var1 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}

		return var1 * Math.random();
	}

	// shift Math.Random by the difference between the max and the min then add the
	// min
	public static int randTwo(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isInt(var1) && isInt(var2)) {
			if (isNumeric(splitString[1], result)) {
				var1 = result[0];
			}
			if (isNumeric(splitString[2], result)) {
				var2 = result[0];
			}
			return (int) ((var2 - var1+1) * Math.random() + var1);
		}

		return 0;
	}

	public static double sqrt(String[] splitString) {
		double var1 = 0;

		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		return Math.sqrt(var1);
	}

	public static double pow(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		return Math.pow(var1, var2);
	}

	public static double abs(String[] splitString) {
		double var1 = 0;

		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		return Math.abs(var1);
	}

	public static double log(String[] splitString) {
		double var1 = 0;

		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		return Math.log10(var1);
	}

	public static double max(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		if (var1 >= var2) {
			return var1;
		}
		return var2;
	}

	public static double divide(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		return var1 / var2;
	}

	public static double multiply(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		return var1 * var2;
	}

	public static double subtract(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		return var1 - var2;
	}

	public static double addNumber(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		return var1 + var2;
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

	public static boolean isWhiteSpace(String[] splitString) {
		
		for(int i=0; i<splitString.length;i++) {
			String c = splitString[i];
			if(c.equals(" ")) {
				return true;
			}
		}
		return false;
	}


}
