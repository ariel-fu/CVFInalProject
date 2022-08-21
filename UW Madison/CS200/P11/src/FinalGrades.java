import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class FinalGrades {

	public static void main(String[] args) {
		
		double[][] grades = { { 5, 6, 7 }, { 0, 0, 0 }, { 10, 12, 14 } };
		int[] maxPoints = { 10, 12, 14 };
		grades = new double[][] { { 1, 2, 3, 4 }, { 2, 4, 6, 8 } };
		maxPoints = new int[] { 3, 6, 9, 12 };

		System.out.println(computeRawFinalGrades(grades, maxPoints) + "  --> Expected: 0.5, 0.7222222, 0.5555556");
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.0);
		rawGrades.add(1.0);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 1, 1, 1 };
		String[] return1 = { "B", "C", "A" };
		System.out.println(Arrays.toString(computeLetterGrades(rawGrades, letters, breakdown)));
		System.out.println(return1.equals(computeLetterGrades(rawGrades, letters, breakdown)));
	}

	public static ArrayList<Double> computeRawFinalGrades(double[][] grades, int[] maxPoints) {
		ArrayList<Double> finalGrades = new ArrayList<Double>();
		int maxPoint = 0;
		//calculates the possible points
		for (int i = 0; i < maxPoints.length; i++) {
			maxPoint += maxPoints[i];
		}

		// i runs through each student, j runs through each of their grade. 
		for (int i = 0; i < grades.length; i++) {
			double gradePoints = 0;
			for (int j = 0; j < grades[i].length; j++) {
				gradePoints += grades[i][j];
			}
			finalGrades.add(gradePoints / maxPoint);
		}

		return finalGrades;

	}

	public static String[] computeLetterGrades(ArrayList<Double> rawGrades, String[] letters, int[] breakdown) {
		String[] letterGrade = new String[rawGrades.size()];
		int arraySize = rawGrades.size();

		// run through all the times we have to assign a grade
		for (int i = 0; i < breakdown.length; i++) {
			// run through each time we have to assign a specific grade
			for (int j = 0; j < breakdown[i]; j++) {
				double maxGrade = -1;
				int index = -1;
				
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
				if (index >= 0) {
					letterGrade[index] = letters[i];
					// set the curr max to a negative number so we won't count again
					rawGrades.set(index, -1.0);
					// reset maxGrades
					maxGrade = 0;
				}
			}
		}

		String gradeLetter = letters[letters.length - 1];
		for (int i = 0; i < rawGrades.size(); i++) {
			// if letterGrade at index i is not filled in, fill it in with the last grade
			if (letterGrade[i] == null) {
				letterGrade[i] = gradeLetter;
			}

		}
		// return the String array!
		return letterGrade;

	}

}
