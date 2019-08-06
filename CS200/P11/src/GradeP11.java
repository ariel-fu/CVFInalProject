import java.util.ArrayList;

/**
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
 */
public class GradeP11 {

	public static void main(String[] args) {
		int numTests = 1;
		double[][] grades = new double[][] {{5,6,7}, {0,0,0}, {10,12,14}};
		int[] maxPoints = new int[] {10,12,14};
		ArrayList<Double> expectedResult = new ArrayList<Double>();
		expectedResult.add(0.5);
		expectedResult.add(0.0);
		expectedResult.add(1.0);
		System.out.println(expectedResult);
		ArrayList<Double> result = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		if(equal(result,expectedResult )) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");
			numTests++;
		}
		expectedResult.clear();
		grades = new double[][] {{1,2,3,4},{2,4,6,8}};
		maxPoints = new int[] {3,6,9,12};
		
		expectedResult.add(0.3333333333);
		expectedResult.add(0.6666666666);
		if(equal(expectedResult,result)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");
			numTests++;
		}
		
		expectedResult.clear();
		grades = new double[][] {{0,10}, {5,5}, {7,3}};
		maxPoints = new int[] {10,10};
		expectedResult.add(0.5);
		expectedResult.add(0.5);
		expectedResult.add(0.5);
		//help with .equals! not working for some no reason. plz help
		if(result.equals(expectedResult)) {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " passed");
			numTests++;
		} else {
			System.out.println("computeRawFinalGrades: Test #" + numTests + " failed");
			numTests++;
		}

	}

	
	public static boolean equal(ArrayList<Double> a, ArrayList<Double>b) {
		boolean result = false;
		for(int i=0; i<a.size(); i++) {
			if(a.get(i) == b.get(i)) {
				result = true;
			}
		}
		
		return result;
		
	}
}
