import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class GradeP11 {

	public static void main(String[] args) {
		int numPassedTestsRawFinalGrades = 1;
		int numTests = 1;
		double[][] grades = new double[][] { { 5, 6, 7 }, { 0, 0, 0 }, { 10, 12, 14 } };
		int[] maxPoints = new int[] { 10, 12, 14 };
		ArrayList<Double> expectedResult = new ArrayList<Double>();
		expectedResult.add(0.5);
		expectedResult.add(0.0);
		expectedResult.add(1.0);
		ArrayList<Double> result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 1, 2, 3, 4 }, { 2, 4, 6, 8 } };
		maxPoints = new int[] { 3, 6, 9, 12 };

		expectedResult.add(1.0 / 3.0);
		expectedResult.add(20 / 30.0);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		if (equal(expectedResult, result)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// --------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 0, 10 }, { 5, 5 }, { 7, 3 } };
		maxPoints = new int[] { 10, 10 };
		expectedResult.add(0.5);
		expectedResult.add(0.5);
		expectedResult.add(0.5);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// -------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 5, 1 }, { 10, 20 }, { 30, 30 }, { 11, 10 } };
		maxPoints = new int[] { 30, 30 };
		expectedResult.add(6 / 60.0);
		expectedResult.add(30.0 / 60.0);
		expectedResult.add(60.0 / 60.0);
		expectedResult.add(21.0 / 60.0);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// -------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 4, 10, 18, 19, 15, 39, 21 } };
		maxPoints = new int[] { 5, 10, 20, 20, 20, 40, 30 };
		expectedResult.add(126.0 / 145.0);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// -----------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 97, 92, 84, 86, 25, 82, 93, 94, 97 }, { 87, 90, 84, 82, 39, 89, 91, 92, 98 },
				{ 25, 96, 35, 78, 79, 89, 88, 84, 96 }, { 95, 89, 91, 96, 95, 96, 98, 89, 94 },
				{ 64, 59, 89, 87, 85, 92, 94, 96, 98 }, { 89, 85, 86, 87, 95, 94, 99, 95, 93 },
				{ 97, 92, 84, 86, 25, 82, 93, 84, 97 }, { 93, 92, 94, 86, 45, 82, 93, 94, 87 },
				{ 97, 72, 84, 86, 52, 82, 93, 94, 92 }, { 27, 82, 94, 96, 95, 82, 93, 95, 97 },
				{ 92, 94, 84, 86, 65, 82, 93, 99, 92 } };
		maxPoints = new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100 };
		expectedResult.add(5.0 / 6);
		expectedResult.add(752.0 / 900);
		expectedResult.add(670 / 900.0);
		expectedResult.add(843 / 900.0);
		expectedResult.add(764 / 900.0);
		expectedResult.add(823 / 900.0);
		expectedResult.add(740 / 900.0);
		expectedResult.add(766 / 900.0);
		expectedResult.add(752 / 900.0);
		expectedResult.add(761 / 900.0);
		expectedResult.add(787 / 900.0);

		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// -----------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 7, 9, 8, 9.5, 9.5, 8.5, 9.5, 7.5, 9 }, { 8, 7, 0, 8, 4, 8, 2, 3, 9 },
				{ 5, 9, 6, 3, 5, 7, 8, 7, 9 }, { 9, 5, 8, 9, 9, 1, 9, 6, 9 }, { 6, 4, 5, 9, 8, 9, 8, 7, 8 },
				{ 8, 9, 8, 5, 8, 6, 8, 7, 9 }, { 9, 7, 9, 2, 8, 4, 8, 6, 5 }, { 9, 3, 9, 2, 9, 4, 8, 6, 4 },
				{ 9, 7, 7, 2, 8, 4, 8, 6, 5 }, { 2, 7, 8, 2, 9, 4, 9, 6, 9 }, { 9, 2, 9, 4, 8, 4, 8, 6, 6 } };
		maxPoints = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		expectedResult.add(77.5 / 90);
		expectedResult.add(58 / 90.0);
		expectedResult.add(59 / 90.0);
		expectedResult.add(65 / 90.0);
		expectedResult.add(64 / 90.0);
		expectedResult.add(68 / 90.0);
		expectedResult.add(58 / 90.0);
		expectedResult.add(57 / 90.0);
		expectedResult.add(56 / 90.0);
		expectedResult.add(56 / 90.0);
		expectedResult.add(56 / 90.0);

		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// -----------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 15, 12, 9, 13, 10, 18, 20, 16, 17 }, { 18, 7, 20, 18, 12, 18, 20, 13, 19 },
				{ 15, 19, 16, 13, 15, 10, 18, 11, 9 }, { 19, 15, 18, 19, 9, 11, 9, 16, 9 },
				{ 16, 14, 15, 9, 18, 9, 8, 17, 8 }, { 18, 9, 18, 15, 8, 16, 18, 17, 19 },
				{ 9, 17, 19, 12, 8, 14, 18, 6, 15 }, { 9, 13, 19, 12, 19, 14, 8, 16, 14 },
				{ 9, 7, 7, 20, 8, 14, 8, 6, 15 }, { 20, 7, 8, 20, 9, 14, 19, 6, 19 },
				{ 9, 20, 19, 4, 8, 14, 8, 16, 16 } };
		maxPoints = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		expectedResult.add(130 / 180.0);
		expectedResult.add(145 / 90.0);
		expectedResult.add(126 / 90.0);
		expectedResult.add(125 / 90.0);
		expectedResult.add(114 / 90.0);
		expectedResult.add(138 / 90.0);
		expectedResult.add(118 / 90.0);
		expectedResult.add(124 / 90.0);
		expectedResult.add(94 / 90.0);
		expectedResult.add(122 / 90.0);
		expectedResult.add(114 / 90.0);

		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// -----------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 12, 22, 13, 29, 30, 18, 23, 26, 27 }, { 28, 17, 30, 28, 24, 18, 22, 23, 19 },
				{ 15, 29, 16, 23, 25, 17, 18, 17, 29 }, { 29, 25, 18, 19, 19, 21, 29, 26, 19 },
				{ 16, 14, 25, 19, 28, 29, 18, 27, 28 }, { 18, 19, 28, 15, 28, 16, 28, 17, 29 },
				{ 19, 27, 19, 20, 28, 14, 28, 16, 15 }, { 19, 30, 29, 20, 19, 4, 18, 26, 14 },
				{ 29, 17, 27, 12, 28, 14, 30, 26, 30 }, { 12, 27, 28, 20, 29, 14, 19, 26, 19 },
				{ 19, 22, 19, 24, 28, 14, 28, 26, 16 } };
		maxPoints = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30 };
		expectedResult.add(200 / 270.0);
		expectedResult.add(209 / 270.0);
		expectedResult.add(189 / 270.0);
		expectedResult.add(205 / 270.0);
		expectedResult.add(204 / 270.0);
		expectedResult.add(198 / 270.0);
		expectedResult.add(186 / 270.0);
		expectedResult.add(179 / 270.0);
		expectedResult.add(213 / 270.0);
		expectedResult.add(194 / 270.0);
		expectedResult.add(196 / 270.0);

		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);

		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed!");
			numPassedTestsRawFinalGrades++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed!");

			numTests++;
		}

		// ---------------------End of computeRawFinalGrades Testing----------------//

		System.out.println("computeRawFinalGrades: Total number passed: " + (numPassedTestsRawFinalGrades - 1) + "/"
				+ (numTests - 1));
		System.out.println();

		// --------------Start of computeLetterGrades Testing----------------------//
		numTests = 1;
		int numPassedTests = 1;

		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.0);
		rawGrades.add(1.0);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 1, 1, 1 };
		String[] a = { "B", "C", "A" };
		String[] b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.95);
		rawGrades.add(0.99);
		rawGrades.add(0.55);
		rawGrades.add(0.98);
		letters = new String[] { "A", "AB", "B", "D", "F" };
		breakdown = new int[] { 2, 1, 0, 1 };
		a = new String[] { "AB", "A", "D", "A" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.95);
		rawGrades.add(0.99);
		rawGrades.add(0.55);
		rawGrades.add(0.98);
		letters = new String[] { "A", "AB", "B", "D", "F" };
		breakdown = new int[] { 2, 1, 0, 0 };
		a = new String[] { "AB", "A", "F", "A" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.1);
		rawGrades.add(0.2);
		rawGrades.add(0.3);
		rawGrades.add(0.15);
		letters = new String[] { "X", "Q", "LL" };
		breakdown = new int[] { 1, 2 };
		a = new String[] { "LL", "Q", "X", "Q" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.1);
		rawGrades.add(0.2);
		rawGrades.add(0.3);
		rawGrades.add(0.15);
		letters = new String[] { "X", "Q", "LL" };
		breakdown = new int[] { 4, 0 };
		a = new String[] { "X", "X", "X", "X" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//
		rawGrades.clear();
		rawGrades.add(0.1);
		rawGrades.add(0.2);
		rawGrades.add(0.3);
		rawGrades.add(0.15);
		letters = new String[] { "X", "Q", "LL" };
		breakdown = new int[] { 0, 4 };
		a = new String[] { "Q", "Q", "Q", "Q" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.1);
		rawGrades.add(0.2);
		rawGrades.add(0.3);
		rawGrades.add(0.15);
		letters = new String[] { "X", "Q", "LL" };
		breakdown = new int[] { 0, 0 };
		a = new String[] { "LL", "LL", "LL", "LL" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.92);
		rawGrades.add(0.20);
		rawGrades.add(0.31);
		rawGrades.add(0.99);
		rawGrades.add(0.51);
		rawGrades.add(0.12);
		rawGrades.add(0.25);
		rawGrades.add(0.72);
		rawGrades.add(0.32);
		rawGrades.add(0.92);
		rawGrades.add(0.55);
		letters = new String[] { "A", "AB", "B", "C", "D", "F" };
		breakdown = new int[] { 1, 4, 0, 5, 1 };
		a = new String[] { "AB", "C", "C", "A", "C", "D", "C", "AB", "C", "AB", "AB" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.22);
		rawGrades.add(0.10);
		rawGrades.add(0.91);
		rawGrades.add(0.92);
		rawGrades.add(0.13);
		rawGrades.add(0.12);
		rawGrades.add(0.28);
		rawGrades.add(0.72);
		rawGrades.add(0.94);
		rawGrades.add(0.92);
		rawGrades.add(0.50);
		letters = new String[] { "A", "AB", "B", "C", "D", "F" };
		breakdown = new int[] { 0, 0, 0, 5, 4 };
		a = new String[] { "D", "F", "C", "C", "D", "F", "D", "C", "C", "C", "D" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.22);
		rawGrades.add(0.90);
		rawGrades.add(0.51);
		rawGrades.add(0.69);
		rawGrades.add(0.31);
		rawGrades.add(0.92);
		rawGrades.add(0.22);
		rawGrades.add(0.82);
		rawGrades.add(0.62);
		rawGrades.add(0.92);
		rawGrades.add(0.45);
		letters = new String[] { "R", "T", "S", "U", "V", "F" };
		breakdown = new int[] { 1, 2, 0, 1, 2 };
		a = new String[] { "F", "T", "F", "V", "F", "R", "F", "U", "V", "T", "F" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);

		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		// -------------------------------------------------------------------------------------//

		rawGrades.clear();
		rawGrades.add(0.12);
		rawGrades.add(0.0);
		rawGrades.add(0.91);
		rawGrades.add(0.49);
		rawGrades.add(0.31);
		rawGrades.add(0.42);
		rawGrades.add(0.82);
		rawGrades.add(0.76);
		rawGrades.add(0.26);
		rawGrades.add(0.28);
		rawGrades.add(0.81);
		letters = new String[] { "R", "T", "S", "U", "V", "F" };
		breakdown = new int[] { 7, 0, 1, 0, 2 };
		a = new String[] { "V", "F", "R", "R", "R", "R", "R", "R", "V", "S", "R" };
		b = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);

		if (stringEqual(a, b)) {
			System.out.println("computeLetterGrades: Test #" + numTests + " passed!");
			numPassedTests++;
			numTests++;
		} else {
			System.out.println("computeLetterGrades: Test #" + numTests + " failed!");
			numTests++;
		}

		System.out.println("computeLetterGrades: Total number passed: " + (numPassedTests - 1) + "/" + (numTests - 1));
		// -------------------------------------------------------------------------------------//
	}

	public static boolean equal(ArrayList<Double> a, ArrayList<Double> b) {
		boolean result = false;
		for (int i = 0; i < a.size(); i++) {

			if (a.get(i).equals(b.get(i))) {
				result = true;
			}
		}

		return result;

	}

	public static boolean stringEqual(String[] a, String[] b) {
		for (int i = 0; i < a.length; i++) {
			if (!(a[i].equals(b[i]))) {
				return false;
			}
		}

		return true;
	}
}
