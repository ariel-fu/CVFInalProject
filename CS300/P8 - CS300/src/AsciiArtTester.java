import java.util.EmptyStackException;
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
	 * Tests an DrawingStack.push() and DrawingStack.peek()
	 * 
	 * @return true: if push & peek work as expected
	 */
	public static boolean testStackPushPeek() {
		StackADT<DrawingChange> test = new DrawingStack(null);
		try {
			test.peek(); // peek at an empty stack --> should throw an NoSuchElement Exception.
			
		} catch (EmptyStackException e) {
		} catch (Exception e) {
			System.out.println("Failed at empty peek");
			// if a different exception than NoSuchElementException was thrown, return
			// false.
			return false;
		}
		try {
			test.push(null); // push a null --> should throw a NoSuchElementException.

		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			System.out.println("Failed at pushing a null object.");
			// if a different exception than NoSuchElementException was thrown, return
			// false.
			return false;
		}

		if (!test.isEmpty()) {
			System.out.println("Failed at pushing an instance that is not of the correct type.");
			return false;
		}
		LinkedNode<DrawingChange> linkedNode = new LinkedNode<DrawingChange>(new DrawingChange(8, 1, 'e', '2'));
		DrawingChange object = linkedNode.getData();
		try {
			test.push(object);
		} catch (Exception e) {
			System.out.println("Failed at pushing a valid object");
			return false;
		}
		if (!test.peek().equals(object)) {
			System.out.println("These two objects should be equal to each other.");
			return false;
		}
		return true;
	}

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
	 * Tests if the constructor for the Canvas class will throw the appropriate
	 * exception --> IllegalArgumentExeption, if any other exceptions are thrown, it returns false.
	 * 
	 * @return true if the IllegalArgumentExceptions are thrown when the height or
	 *         the width are 0 or less.
	 */
	public static boolean testConstructorCanvas() {
		Canvas test;
		try {
			test = new Canvas(0, 0);
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			
			return false;
		}

		try {
			test = new Canvas(-2, -2);

		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			return false;
		}

		try {
			test = new Canvas(-2, 4);
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			return false;
		}

		try {
			test = new Canvas(2, -2);
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
			return false;
		}

		try {
			test = new Canvas(5, 5);
		} catch (Exception e) {
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
		return true;
	}

	public static void main(String[] args) {
		System.out.println(runAsciiArtTestSuite());
	}

}
