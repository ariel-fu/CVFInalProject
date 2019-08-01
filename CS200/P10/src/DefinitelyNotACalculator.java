
/*
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
*/

import java.util.Scanner;

public class DefinitelyNotACalculator {
	// static vars to make it less typing!
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
			// ask for input!
			System.out.println("Please input an expression: ");
			stringInputed = sc.nextLine();
			// modify the string input
			stringInputed = modifyOriginalString(stringInputed);
			// split the input by spaces
			String[] splitString = stringInputed.split(" ");
			// set the operator to the first element
			String operator = splitString[0];
			// if the user inputted quit, break out of the do/while loop then say goodbye
			if (operator.equalsIgnoreCase("quit")) {
				break;
			}
			// if it is not a valid operator, print out an error then continue
			if (!isValidOperator(operator)) {
				System.out.println("Error! Command not found.");
				continue;
			}
			// if it is not a valid amount of arguments/inputs, print out an error then
			// continue
			if (!isValidArgument(splitString)) {
				System.out.println("Error! Incorrect amount of arguments");
				continue;
			}
			// since sort is printing out, we put it in main. So if the operator is equal to
			// sort, call the method and print another line
			// if the operator is something else, run the calculate method which will
			// determine which operator the user is calling
			if (operator.equalsIgnoreCase(SORT)) {
				sort(splitString);
				System.out.println();
			} else {
				System.out.println(calculate(splitString));
			}

		} while (!stringInputed.equalsIgnoreCase("quit"));

		System.out.println("Have a nice day, Mr. Finch!");
		sc.close();

	}

	public static String modifyOriginalString(String string) {
		// this modifies the String inputed by the user
		// --trimming it
		// --changing them all to lowercase
		// -- replacing all the extra spaces (whitespace if you will) with single spaces
		// --it runs until the string doesn't contain anymore double spaces
		// --return the modified String
		string = string.trim();
		string = string.toLowerCase();
		do {
			string = string.replace("  ", " ");
		} while (string.contains("  "));
		return string;
	}

	public static double calculate(String[] splitString) {
		// depending on what the operator is, it calls on the corresponding method and
		// returns the value
		// covering +, -, *, /, max, abs, log, pow, sqrt, rand (both), and sum
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
		// sort can't be in here since it has to print. so sort is a condition in main.
	}

	public static boolean isValidArgument(String[] splitString) {
		// checks, based on the input, if it is a valid number of arguments.
		String operator = splitString[0];

		if (operator.equalsIgnoreCase(PLUS)) {
			// should be two numbers inputed
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(SUBTRACT)) {
			// should be two numbers inputed
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(MULTIPLY)) {
			// should be two numbers inputed
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(DIVIDE)) {
			// should be two numbers inputed
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(MAX)) {
			// should be two numbers inputed
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(ABS)) {
			// should be one number inputed
			return isOneNumber(splitString);
		} else if (operator.equalsIgnoreCase(LOG)) {
			// should be one number inputed
			return isOneNumber(splitString);
		} else if (operator.equalsIgnoreCase(POW)) {
			// should be two numbers inputed
			return isTwoNumbers(splitString);
		} else if (operator.equalsIgnoreCase(SQRT)) {
			// should be one number inputed
			return isOneNumber(splitString);
		} else if (operator.equalsIgnoreCase(RAND)) {
			// checks if it is a valid random argument
			// (depending on type, one input or two!)
			return isValidRandArgument(splitString);
		} else if (operator.equalsIgnoreCase(SUM)) {
			// it is an endless argument, so any amount of arguments will be okay
			return isValidEndlessArgument(splitString);
		} else if (operator.equalsIgnoreCase(SORT)) {
			// also an endless argument, so any amount of arguments will be okay
			return isValidEndlessArgument(splitString);
		}
		// if none of these are true, then it is not a valid argument
		return false;
	}

	public static boolean isValidEndlessArgument(String[] splitString) {
		// only used for sum and sort
		// if it is an endless amount of arguments, it will always be true
		return true;
	}

	public static boolean isValidRandArgument(String[] splitString) {
		// first checks if it is is has the correct amount of input(s)
		if (isOneNumber(splitString) || isTwoNumbers(splitString)) {
			// if there are two inputs, check to see if both are ints
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
					// if one of them is not an int, then it is not a valid random argument
					return false;
				}
			}
			// but if it is a one number, they can be doubles. So it is a valid random
			// argument
			if (isOneNumber(splitString)) {
				return true;
			}
		}
		// if it has more than 2 arguments, it will say it is not a valid random
		// argument
		return false;

	}

	public static boolean isInt(double val) {
		// checks if it is an int
		int test = (int) val;
		return test == val;

	}

	// two numbers checks if the length is three and if both the doubles are numeric
	// or not
	public static boolean isTwoNumbers(String[] splitString) {
		// checks first if the length is three.
		if (splitString.length != 3) {
			return false;
		}
		double[] test = new double[1];
		// if the length is three, check if both of the following elements are numeric
		if (!isNumeric(splitString[1], test)) {
			return false;
		}
		if (!isNumeric(splitString[2], test)) {
			return false;
		}
		// if they both are, it is a valid two number input
		return true;
	}

	// checks if it is a valid one number input
	public static boolean isOneNumber(String[] splitString) {
		// checks first if the length is two (operator and first element)
		if (splitString.length != 2) {
			return false;
		}
		double[] test = new double[1];
		// if the length is two, check if the element is numeric
		if (!isNumeric(splitString[1], test)) {
			return false;
		}
		// if both check out as true, it is a valid one number input
		return true;

	}

	// checks if it is a valid operator
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

	// sort the array
	public static void sort(String[] splitString) {
		// run through all the elements and check if it is numeric, parse it to a double
		// and, add it to the new double array
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

		// print it out.
		for (int i = 0; i < doubleArray.length; i++) {
			System.out.print(doubleArray[i] + " ");
		}
	}

	// sum of all elements
	public static double sum(String[] splitString) {
		double sum = 0;
		double val1 = 0;
		// run through every element, parse it to a double, and add it to the total sum
		double[] result = new double[1];
		for (int i = 1; i < splitString.length; i++) {
			if (isNumeric(splitString[i], result)) {
				val1 = result[0];
			}
			sum += val1;
		}

		return sum;

	}

	// check to use which randomizer
	public static double rand(String[] splitString) {
		// for a one number input, use the one number input method for rand
		if (isOneNumber(splitString)) {
			return randOne(splitString);
		}
		// if it is a two number input, use the two number input method for rand
		if (isTwoNumbers(splitString)) {
			return randTwo(splitString);
		}

		return 0;
	}

	// randomizer of one input
	public static double randOne(String[] splitString) {
		double var1 = 0;
		double[] result = new double[1];
		// check if it is numeric first
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		// shift the randomizer (between 0-1) by var1 (the input)
		return var1 * Math.random();
	}

	// randomizer of two inputs
	public static int randTwo(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// check if both are ints, then if both are numeric
		if (isInt(var1) && isInt(var2)) {
			if (isNumeric(splitString[1], result)) {
				var1 = result[0];
			}
			if (isNumeric(splitString[2], result)) {
				var2 = result[0];
			}
			// shift the randomizer by max-min + 1 then add the min
			return (int) ((var2 - var1 + 1) * Math.random() + var1);
		}

		return 0;
	}

	// square root
	public static double sqrt(String[] splitString) {
		double var1 = 0;
		// check if the input is numeric
		double[] result = new double[1];
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		// use Math.sqrt to get the square root of the input
		return Math.sqrt(var1);
	}

	// to the power of the second input
	public static double pow(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// check if inputs are numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		// return var1 to the power of var2
		return Math.pow(var1, var2);
	}

	// absolute value
	public static double abs(String[] splitString) {
		double var1 = 0;
		double[] result = new double[1];
		// check if the input is numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		// return the absolute value of the input
		return Math.abs(var1);
	}

	// log base ten of the number
	public static double log(String[] splitString) {
		double var1 = 0;
		double[] result = new double[1];
		// check if the input is numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		// return the log (base 10) of the input
		return Math.log10(var1);
	}

	// the max number
	public static double max(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// check if both inputs are numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		// if the first input is greater or equal to the second input, return the first
		// input
		if (var1 >= var2) {
			return var1;
		}
		// otherwise, return the second input
		return var2;
	}

	// division
	public static double divide(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// check if both inputs are numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		// return first input divided by second input
		return var1 / var2;
	}

	// multiplication
	public static double multiply(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// checks if both inputs are numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		// return first input times second input
		return var1 * var2;

	}

	// subtracting
	public static double subtract(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// checks if both inputs are numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		// return the first input minus the second input
		return var1 - var2;
	}

	// adding
	public static double addNumber(String[] splitString) {
		double var1 = 0;
		double var2 = 0;
		double[] result = new double[1];
		// checks if both inputs are numeric
		if (isNumeric(splitString[1], result)) {
			var1 = result[0];
		}
		if (isNumeric(splitString[2], result)) {
			var2 = result[0];
		}
		// returns the sum of the two inputs
		return var1 + var2;
	}

	// checks if the element (String) is numeric
	public static boolean isNumeric(String string, double[] result) {
		// try to parse to a double
		try {
			double test = Double.parseDouble(string);
			result[0] = test;
		} catch (Exception e) {
			// if there is an exception, it is not numeric
			return false;
		}
		return true;
	}

}
