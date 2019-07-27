
/**
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
 */
import java.util.Random;
import java.util.Scanner;

public class DefinitelyNotACalculator {

	public static void main(String[] args) {
		System.out.println("Good day, Mr. Finch! Welcome to your totally-not-a-calculator.");
		System.out.println("Please input an expression: ");
		Scanner sc = new Scanner(System.in);
		String stringInputed = sc.nextLine();
		stringInputed.toLowerCase();
		String[] splitString = stringInputed.split(" ");

		do {
			String operator = splitString[0];
			if (isNotValidOperator(splitString)) {
				System.out.println("Error! Command not found.");
			}

			if (isNotValidAmountOfArguments(splitString)) {
				System.out.println("Error! Incorrect number of arguments");
			}
			
			if(operator.equalsIgnoreCase("+")) {
				System.out.println(add());
			}

			System.out.println("Please input an expression: ");
			stringInputed = sc.nextLine();
			stringInputed.toLowerCase();
			splitString = stringInputed.split(" ");

		} while (!splitString[0].equals("quit"));

		System.out.println("Have a nice day, Mr. Finch!");

	}

	public static boolean isNotValidAmountOfArguments(String[] x) {
		String operator = x[0];
		operator.toLowerCase();
		int numberOfInputs = x.length - 1;
		if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")
				|| operator.equals("max") || operator.equals("pow") || operator.equals("rand")) {
			if (numberOfInputs != 2) {
				return true;
			}
		} else if (operator.equals("log") || operator.equals("sqrt") || operator.equals("abs")
				|| operator.equals("rand")) {
			if (numberOfInputs != 1) {
				return true;
			}
		}

		return false;

	}

	public static boolean isNotValidOperator(String[] splitString) {
		String operator = splitString[0];
		operator.toLowerCase();
		if (!operator.equals("+")) {
			return true;
		} else if (!operator.equals("-")) {
			return true;
		} else if (!operator.equals("*")) {
			return true;
		} else if (!operator.equals("/")) {
			return true;
		} else if (!operator.equals("max")) {
			return true;
		} else if (!operator.equals("pow")) {
			return true;
		} else if (!operator.equals("abs")) {
			return true;
		} else if (!operator.equals("log")) {
			return true;
		} else if (!operator.equals("sqrt")) {
			return true;
		} else if (!operator.equals("rand")) {
			return true;
		} else if (!operator.equals("sum")) {
			return true;
		} else if (!operator.equals("sort")) {
			return true;
		} else {
			return false;
		}
	}

	public static double add(double x, double y) {
		return x + y;
	}

	public static double subtract(double x, double y) {
		return x - y;
	}

	public static double multiply(double x, double y) {
		return x * y;
	}

	public static double divide(double x, double y) {
		return x / y;
	}

	public static double max(double x, double y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public static double pow(double x, double y) {
		return Math.pow(x, y);
	}

	public static double abs(double x) {
		return Math.abs(x);
	}

	public static double log(double x) {
		return Math.log10(x);
	}

	public static double sqrt(double x) {
		return Math.sqrt(x);
	}

	public static int rand(int x, int y) {
		Random rand = new Random();
		int randomNumber= Random.ints(x, y);
		return randomNumber;
	}

	public static double rand(double x) {
//fix this
	}

	public static double sum(double[] x) {
		int y = 0;
		for (int i = 1; i < x.length; i++) {
			y += x[i];
		}
		return y;
	}

	public static double[] sort(double[] x) {
		int arrLength = x.length;
		// copied from P9
		for (int i = 0; i < arrLength; i++) {
			for (int j = 0; j < arrLength - 1; j++) {
				// when we find an element out of order, swap it with the one smaller than it!
				if (x[j + 1] < x[j]) {
					double replace = x[j + 1];
					x[j + 1] = x[j];
					x[j] = replace;
				}
			}
		}

		return x;
	}

}
