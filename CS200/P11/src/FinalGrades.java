import java.util.ArrayList;
import java.util.Arrays;
public class FinalGrades {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] grades = { { 2, 3, 4 }, { 5, 6, 2 }, { 3, 2, 5 } };
		int[] maxPoints = { 6, 6, 6 };
		System.out.println(computeRawFinalGrades(grades, maxPoints) + "  --> Expected: 0.5, 0.7222222, 0.5555556");
		grades = new double[][] {{90,3,2}, {90,5,4}, {50,3,2},{88,5,5}};
		maxPoints = new int[] {90,5,5};
		ArrayList<Double> x = computeRawFinalGrades(grades, maxPoints);
		String[] letters = new String[] {"A", "AB", "B", "D", "F"};
		int[] breakdown = new int[] {2,1,0,1};
		System.out.println(Arrays.toString(computeLetterGrades(x, letters, breakdown)) + " --> Expected: AB, A, D, A");

	}

	public static ArrayList<Double> computeRawFinalGrades(double[][] grades, int[] maxPoints) {
		ArrayList<Double> finalGrades = new ArrayList<Double>();
		int maxPoint = 0;
		for (int i = 0; i < maxPoints.length; i++) {
			maxPoint += maxPoints[i];
		}

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
		double maxGrade = 0;
		int arraySize = rawGrades.size();
		int index = 0;
		for (int i = 0; i < breakdown.length; i++) {
			for (int j = 0; j < breakdown[i]; j++) {
				for (int k=0; k<rawGrades.size(); k++) {
					if (maxGrade < rawGrades.get(k)) {
						maxGrade = rawGrades.get(k);
						index = k;
					}
				}

				letterGrade[index] = letters[i];
				rawGrades.set(index, -1.0);
				maxGrade =0;
			}
		}
		
		
		while(rawGrades.get(index)>=0) {
			letterGrade[index] = letters[letters.length - 1];
			index++;
		}

		return letterGrade;
	}

}
