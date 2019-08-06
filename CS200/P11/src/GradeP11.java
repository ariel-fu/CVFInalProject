import java.util.ArrayList;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class GradeP11 {

	public static void main(String[] args) {
		int numComputeRawFinalGradesPass = 0;
		int numTests = 1;
		double[][] grades = new double[][] { { 5, 6, 7 }, { 0, 0, 0 }, { 10, 12, 14 } };
		int[] maxPoints = new int[] { 10, 12, 14 };
		ArrayList<Double> expectedResult = new ArrayList<Double>();
		expectedResult.add(0.5);
		expectedResult.add(0.0);
		expectedResult.add(1.0);
		ArrayList<Double> result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numComputeRawFinalGradesPass++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");
			numTests++;
		}

		// -------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 1, 2, 3, 4 }, { 2, 4, 6, 8 } };
		maxPoints = new int[] { 3, 6, 9, 12 };
		// TODO: find out how to chop decimals to 10 places!
		expectedResult.add(0.3333333333);
		expectedResult.add(0.6666666666);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		if (equal(expectedResult, result)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numComputeRawFinalGradesPass++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");
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
		// help with .equals! not working for some no reason. plz help
		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numComputeRawFinalGradesPass++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");

			numTests++;
		}

		// -------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { { 5, 1 }, { 10, 20 }, { 30, 30 }, { 11, 10 } };
		maxPoints = new int[] { 30, 30 };
		expectedResult.add(0.1);
		expectedResult.add(0.5);
		expectedResult.add(1.0);
		expectedResult.add(0.35);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		// help with .equals! not working for some no reason. plz help
		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numComputeRawFinalGradesPass++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");

			numTests++;
		}
		
		//-------------------------------------------------------------------------------//
		expectedResult.clear();
		grades = new double[][] { {4,10,18,19,15,39,21 } };
		maxPoints = new int[] { 5,10,20,20,20,40,30 };
		expectedResult.add(0.86896551724);
		result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		//also need to cut this one. cut it off at 11?
		System.out.println(result + " vs " + expectedResult);
		// help with .equals! not working for some no reason. plz help
		if (equal(result, expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numComputeRawFinalGradesPass++;
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");

			numTests++;
		}

	}

	// at least one is equal or all of them are equal?
	public static boolean equal(ArrayList<Double> a, ArrayList<Double> b) {
		boolean result = false;
		for (int i = 0; i < a.size(); i++) {
			// TODO - what is a.get(i)? can it be compared using ==?

			if (a.get(i).equals(b.get(i))) {
				result = true;
			}
		}

		return result;

	}
}
