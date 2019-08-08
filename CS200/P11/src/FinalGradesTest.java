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

		grades = new double[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
		maxPoints = new int[] { 25, 25, 25, 25 };
		b.clear();
		b.add((0 + 0 + 0 + 0) / (25.0 + 25 + 25 + 25));
		b.add((0 + 0 + 0 + 0) / (25.0 + 25 + 25 + 25));
		a.clear();
		a = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		assertTrue(a.equals(b));

		grades = new double[][] { { 10, 10, 10 }, { 10, 10, 10 }, { 10, 10, 10 } };
		maxPoints = new int[] { 10, 10, 10 };
		b.clear();
		b.add((10 + 10 + 10) / (10.0 + 10.0 + 10));
		b.add((10 + 10 + 10) / (10.0 + 10.0 + 10));
		b.add((10 + 10 + 10) / (10.0 + 10.0 + 10));
		a.clear();
		a = FinalGrades.computeRawFinalGrades(grades, maxPoints);
		assertTrue(a.equals(b));
	}

	@Test
	public void testComputeLetterGrades() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.0);
		rawGrades.add(1.0);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 1, 1, 1 };
		String[] return1 = { "B", "C", "A" };
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	@Test
	public void testLastGrade() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.0);
		rawGrades.add(1.0);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 0, 0, 0 };
		String[] return1 = { "F", "F", "F" };
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	@Test
	public void testFirstGrade() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.0);
		rawGrades.add(1.0);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 3, 0, 0 };
		String[] return1 = { "A", "A", "A" };
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	@Test
	public void testSameGrade() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.5);
		rawGrades.add(0.5);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 1, 1, 0 };
		String[] return1 = { "A", "B", "F" };
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	@Test
	public void testMoreGrade() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.5);
		rawGrades.add(0.5);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 1, 1, 3 };
		String[] return1 = { "A", "B", "C" };
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	@Test
	public void test3rdGrade() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		rawGrades.add(0.5);
		rawGrades.add(0.5);
		rawGrades.add(0.5);
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 0, 0, 3 };
		String[] return1 = { "C", "C", "C" };
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	@Test
	public void testNoGrade() {
		ArrayList<Double> rawGrades = new ArrayList<Double>();
		String[] letters = { "A", "B", "C", "F" };
		int[] breakdown = { 3, 3, 3 };
		String[] return1 = {};
		String[] result = FinalGrades.computeLetterGrades(rawGrades, letters, breakdown);
		assertTrue(compareArrays(return1, result));
	}

	public boolean compareArrays(String[] src, String[] dst) {
		if (src.length != dst.length) {
			return false;
		}
		for (int i = 0; i < src.length; i++) {

			if (src[i] == null) {
				if (dst[i] != null) {
					return false;
				}
			} else {
				if (!src[i].equals(dst[i])) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean compareArrays(ArrayList<Object> src, ArrayList<Object> dst) {
		if (src.size() != dst.size()) {
			return false;
		}
		for (int i = 0; i < src.size(); i++) {

			if (src.get(i) == null) {
				if (dst.get(i) != null) {
					return false;
				}
			} else {
				if (!src.get(i).equals(dst.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

}
