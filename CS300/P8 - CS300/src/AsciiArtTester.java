import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           AsciiArtTester
//Files:           DrawingChange, StackADT, LinkedNode, DrawingStack, AsciiArtTester, Canvas, AsciiArtDriver
//Course:          300, Fall, and 2019
//
//Author:          (Ariel Fu)
//Email:           afu5@wisc.edu
//Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         NONE
//Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class is a Tester class for methods created in DrawingChange,
 * DrawingStack, Canvas
 * 
 * @author Ariel
 *
 */
public class AsciiArtTester {

	/**
	 * Tests undo method from Canvas Class
	 * 
	 * @return true if undo works as expected
	 */
	public static boolean testUndo() {
		Canvas test = new Canvas(5, 5);
		test.draw(0, 0, 'p');
		if (!test.undo()) {
			return false;
		}
		return true;
	}

	/**
	 * Tests redo method from Canvas Class
	 * 
	 * @return true if redo works as expected
	 */
	public static boolean testRedo() {
		Canvas test = new Canvas(5, 5);
		try {
			test.redo();

			return false;
		} catch (Exception e) {
		}
		test.draw(0, 0, 'p');

		test.undo();
		if (!test.redo()) {
			return false;
		}
		return true;
	}

	/**
	 * Tests the DrawingStackIterator class for hasNext & next.
	 * 
	 * @return true if hasNext & next work as expected
	 */
	public static boolean testDrawingStackIterator() {
		DrawingChange x = new DrawingChange(0, 0, ' ', 'e');
		DrawingStack stack = new DrawingStack();
		Iterator<DrawingChange> iterate = stack.iterator();
		if (iterate.hasNext()) {
			return false;
		}

		stack.push(x);
		iterate = stack.iterator();
		if (!iterate.next().equals(x)) {
			return false;
		}
		try {
			iterate.next();

		} catch (NoSuchElementException e) {
			// should throw an exception for trying to get the next element (null)
		} catch (Exception e) {
			// should not throw any other exceptions
			return false;
		}

		DrawingChange[] changes = new DrawingChange[3];
		for (int i = 0; i < changes.length; i++) {
			changes[i] = new DrawingChange(i, i + 1, ' ', 'a');
		}
		if (changes[1].newChar != 'a') {
			return false;
		}
		if (changes[0].row != 0) {
			return false;
		}
		if (changes[2].col != 3) {
			return false;
		}
		return true;
	}

	/**
	 * Tests Canvas's draw method. Tests: out of bounds positive and negative,
	 * adding in one new character, overriding a character.
	 * 
	 * @return true if the draw method works as expected.
	 */
	public static boolean testCanvasDraw() {
		Canvas test = new Canvas(5, 5);
		try {
			test.draw(9, 9, 'p');
			return false;
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {

			return false;
		}

		try {
			test.draw(-5, -5, 'e');
			return false;
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {

			return false;
		}
		String before = test.toString();
		test.draw(0, 0, 'e');
		String after = test.toString();
		if (before.equals(after)) {

			return false;
		}

		test.draw(0, 0, 'p');
		String override = test.toString();
		if (after.equals(override)) {

			return false;
		}

		return true;

	}

	/**
	 * Tests if the constructor for the Canvas class will throw the appropriate
	 * exception --> IllegalArgumentExeption, if any other exceptions are thrown, it
	 * returns false.
	 * 
	 * @return true if the IllegalArgumentExceptions are thrown when the height or
	 *         the width are 0 or less.
	 */
	public static boolean testConstructorCanvas() {
		Canvas test;
		try {
			test = new Canvas(0, 0);
		} catch (IllegalArgumentException e) {
			// should throw an IllegalArgumentException, width & height must be greater than
			// 0
		} catch (Exception e) {
			// there shouldn't be any other exceptions thrown.
			return false;
		}

		try {
			test = new Canvas(-2, -2);

		} catch (IllegalArgumentException e) {
			// should throw an IllegalArgumentException, row & col must be greater than 0
		} catch (Exception e) {
			return false;
		}

		try {
			test = new Canvas(-2, 4);
		} catch (IllegalArgumentException e) {
			// should throw an IllegalArgumentException, row & col must be greater than 0
		} catch (Exception e) {
			return false;
		}

		try {
			test = new Canvas(2, -2);
		} catch (IllegalArgumentException e) {
			// should throw an IllegalArgumentException, row & col must be greater than 0
		} catch (Exception e) {
			return false;
		}

		try {
			test = new Canvas(5, 5);
		} catch (Exception e) {
			// no exceptions should be thrown.
			return false;
		}
		return true;

	}

	/**
	 * Tests the correct implementation of the pop method
	 * 
	 * @return true if the pop method works as expected
	 */
	public static boolean testDrawingStackPop() {
		try {
			DrawingStack test = new DrawingStack();
			try {
				test.pop();
				return false;
			} catch (EmptyStackException e) {
				// the stack is empty so the pop method shouldn't be able to work.
			} catch (Exception e) {
				return false;
			}
			try {
				test.push(new DrawingChange(0, 1, ' ', 'p'));
			} catch (Exception e) {
				return false; // no exceptions should be thrown from this push method.
			}
			DrawingChange change;
			try {
				change = test.pop(); // if any exceptions are thrown, return false.
			} catch (Exception e) {
				return false;
			}
			// check if the removed change has the same values as the changed pushed in
			if (change.row != 0) {
				return false;
			}
			if (change.col != 1) {
				return false;
			}
			if (change.prevChar != ' ') {
				return false;
			}
			if (change.newChar != 'p') {
				return false;
			}
			// make sure there is nothing left in the stack after removing the only change
			// pushed onto the stack.
			try {
				test.peek();
				return false;
			} catch (EmptyStackException e) {
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			// if any exceptions are thrown from this tester method, return false
			return false;
		}
		// otherwise return true
		return true;
	}

	/**
	 * Tests an DrawingStack.push() and DrawingStack.peek()
	 * 
	 * @return true: if push & peek work as expected
	 */
	public static boolean testStackPushPeek() {
		try {
			DrawingChange other = new DrawingChange(0, 0, 'p', 'e');
			DrawingStack test = new DrawingStack();
			try {
				test.push(other); // no exceptions should be thrown from this line of code.
			} catch (Exception e1) {
				return false;
			}

			try {
				DrawingChange peek = test.peek();
				if (!compare(peek, other)) {
					// the top of the stack should be equal to the DrawingChange pushed on to the
					// stack.
					return false;
				}
			} catch (Exception e2) {
				// no exceptions should be thrown.
				return false;
			}

			try {
				DrawingChange pop = test.pop();
				if (!test.isEmpty()) {
					// the Stack should not be empty yet.
					return false;
				}
				if (!compare(pop, other)) {
					return false;
				}

			} catch (Exception e3) {
				return false;
			}
			// peek empty
			// check return value - should be an exception

			test = new DrawingStack();
			try {
				DrawingChange emptyPeek = test.peek();
				return false;
			} catch (EmptyStackException e) {
				// should throw an exception from peeking an empty stack.
			} catch (Exception e) {
				return false;
			}

			// pop empty
			// check return value - should throw exception
			test = new DrawingStack();

			try {
				DrawingChange emptyPop = test.pop();

				return false;

			} catch (EmptyStackException e) {
				// should throw an exception from popping an empty stack.
			} catch (Exception e) {
				return false; // any other exception other than EmptyStackException will be wrong
			}

			// push a null

			test = new DrawingStack();
			try {
				DrawingChange nullPush = null;
				test.push(nullPush);
			} catch (IllegalArgumentException e) {
				// should throw an IllegalArgumentException from trying to push a null.
			} catch (Exception e) {
				return false; // any other exception other than IllegalArgumentException will be wrong.
			}
		} catch (Exception e) {
			return false; // if any exceptions were thrown inside this tester method, return false.
		}

		return true;
	}

	/**
	 * Hyper-method for comparing two DrawingChanges
	 * 
	 * @param a - DrawingChange 1.
	 * @param b - DrawingChange 2.
	 * @return true if a & b have the same values
	 */
	private static boolean compare(DrawingChange a, DrawingChange b) {
		if (a == null || b == null) {
			return false;
		}
		if (a.row != b.row) {
			return false;
		}
		if (a.col != b.col) {
			return false;
		}
		if (a.prevChar != b.prevChar) {
			return false;
		}
		if (a.newChar != b.newChar) {
			return false;
		}
		return true;
	}

	/**
	 * Runs multiple other test methods
	 * 
	 * @return true if all test methods succeed, false if any of them fail.
	 */
	public static boolean runAsciiArtTestSuite() {
		if (!testStackPushPeek()) {
			System.out.println("push and peek.");
			return false;
		}
		if (!testUndo()) {
			System.out.println("undo");
			return false;
		}
		if (!testRedo()) {
			System.out.println("redo");
			return false;
		}
		if (!testConstructorCanvas()) {
			System.out.println("canvas constructor");
			return false;
		}
		if (!testDrawingStackIterator()) {
			System.out.println("DrawingStackIterator Class");
			return false;
		}
		if (!testCanvasDraw()) {
			System.out.println("Canvas draw method");
			return false;
		}
		if (!testDrawingStackPop()) {
			System.out.println("Pop method from DrawingStack");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(runAsciiArtTestSuite());
	}

}
