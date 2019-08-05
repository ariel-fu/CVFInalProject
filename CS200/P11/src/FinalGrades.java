import java.util.ArrayList;
import java.util.Arrays;

public class FinalGrades {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] grades = { { 2, 3, 4 }, { 5, 6, 2 }, { 3, 2, 5 } };
		int[] maxPoints = { 6, 6, 6 };
		System.out.println(computeRawFinalGrades(grades, maxPoints)
				+ "  --> Expected: 0.5, 0.7222222, 0.5555556");
		grades = new double[][] { { 90, 3, 2 }, { 90, 5, 4 }, { 50, 3, 2 },
				{ 88, 5, 5 } };
		maxPoints = new int[] { 90, 5, 5 };
		ArrayList<Double> x = computeRawFinalGrades(grades, maxPoints);
		String[] letters = new String[] { "A", "AB", "B", "D", "F" };
		int[] breakdown = new int[] { 2, 1, 0, 1 };
		System.out.println(Arrays.toString(computeLetterGrades(x, letters,
				breakdown)) + " --> Expected: AB, A, D, A");

	}

	public static ArrayList<Double> computeRawFinalGrades(double[][] grades,
			int[] maxPoints) {
		ArrayList<Double> finalGrades = new ArrayList<Double>();
		int maxPoint = 0;
		for (int i = 0; i < maxPoints.length; i++) {
			maxPoint += maxPoints[i];
		}
		
		//TODO
		//a/A+b/B is not the same as (a+b)/(A+B)

		for (int i = 0; i < grades.length; i++) {
			double gradePoints = 0;
			for (int j = 0; j < grades[i].length; j++) {
				gradePoints += grades[i][j];
			}
			finalGrades.add(gradePoints / maxPoint);
		}

		return finalGrades;

	}

	//TODO - fix scope issue. vars should be declare as needed, not always at the outer most scope
	public static String[] computeLetterGrades(ArrayList<Double> rawGrades,
			String[] letters, int[] breakdown) {
		String[] letterGrade = new String[rawGrades.size()];
		double maxGrade = 0;
		int arraySize = rawGrades.size();
		int index = 0;
		// run through all the times we have to assign a grade
		for (int i = 0; i < breakdown.length; i++) {
			// run through each time we have to assign a specific grade
			for (int j = 0; j < breakdown[i]; j++) {
				
				//TODO - junit, what are the inputs and what is the output?
				for (int k = 0; k < rawGrades.size(); k++) {
					// run through the ArrayList
					// -- find the max
					// -- set the index of the max to the curr index
					if (maxGrade < rawGrades.get(k)) {
						maxGrade = rawGrades.get(k);
						index = k;
					}
				}
				// set the String array of grades at the curr index of the
				// maxGrade to be equal to the letter at position i (the first
				// for loop)
				letterGrade[index] = letters[i];
				// set the curr max to a negative number so we won't count again
				rawGrades.set(index, -1.0);
				// reset maxGrades
				maxGrade = 0;
			}
		}

	//TODO - letterGrade[i]=="" -> no letter assigned
		// fill in the remaining (need to fix this)
		while (rawGrades.get(index) >= 0) {
			index = 0;
			// check if rawGrades at the curr index is greater than 0, if it is, assign that index the last grade
			if (rawGrades.get(index) >= 0) {
				letterGrade[index] = letters[letters.length - 1];
			}  
			index++;
		}
		// return the String array!
		return letterGrade;
	}
