import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


import java.util.Arrays;

public class FinalGradesTest {

	@Test
	public void testComputeRawFinalGrades() {
		double[][] grades = { { 5, 6, 7 }, { 0, 0, 0 }, { 10, 12, 14 } };
		int[] maxPoints = { 10, 12, 14 };
		ArrayList<Double> b = new ArrayList<Double>();
		b.add(0.5);
		b.add(0.0);
		b.add(1.0);
		ArrayList<Double> a = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		assertTrue(a.equals(b));
		
		
		
	}

	@Test
	public void testComputeLetterGrades() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.0);
		rawGrades.add(1.0);
		String[] letters = {"A", "B", "C", "F"};
		int[] breakdown = {1,1,1};
		String[] return1 = {"B", "C", "A"};
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		
	}

}
