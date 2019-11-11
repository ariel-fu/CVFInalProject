import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

public class P08 {

	@Test
	public void testCanvasConstructor() {
		// throws an IllegalArgument exception if the width or height <= 0
		Canvas test;
		try {
			test = new Canvas(-2, 5);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			test = new Canvas(5, -2);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			test = new Canvas(-2, -5);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			test = new Canvas(2, 5);
			assertTrue(true);
		} catch (IllegalArgumentException e) {
			assertTrue(false);
		}
	}

	public void testCanvasDraw() {
		Canvas test = new Canvas(5, 5);
		String before = test.toString();
		// throws an IllegalArgumentException if the drawing position is out of the
		// canvas's width or height
		try {
			test.draw(2, 5, 'e');
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			test.draw(5, 2, 'e');
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		try {
			test.draw(5, 5, 'e');
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			test.draw(2, 2, 'e');
			assertTrue(true);
		} catch (IllegalArgumentException e) {
			assertTrue(false);
		}
		
		String after = test.toString();

		// does it overwrite the char at the current position? (if the toString before
		// equals to the toString after, it did not overwrite it!)
		assertTrue(after.equals(before));
		


	}

	public void testCanvasRedo() {
		Canvas test = new Canvas(2, 2);
		// throws an emptyStackException if the redoStack is empty and try to redo
		// before there is anything to redo.
		try {
			test.redo();
			assertTrue(false);
		} catch(EmptyStackException e) {
			assertTrue(true);
		}
		

		// does it overwrite the curr char? (use toString logic)
		test.draw(0, 0, 'e');
		String before = test.toString();
		test.undo();
		assertTrue(test.redo());
		String after = test.toString();
		assertTrue(before.equals(after));
		
	}

	public void testCanvasUndo() {
		// throws an emptyStackException if the undoStack is empty and try to undo
		// before drawing anything
		Canvas test = new Canvas(2, 2);
		// throws an emptyStackException if the redoStack is empty and try to redo
		// before there is anything to redo.
		try {
			test.undo();
			assertTrue(false);
		} catch(EmptyStackException e) {
			assertTrue(true);
		}
		

		String before = test.toString();
		// does it overwrite the curr char? (use toString logic)
		test.draw(0, 0, 'e');
		test.undo();
		String after = test.toString();
		// does it go back to what it was? --> toString logic
		assertTrue(before.contentEquals(after));
	}

}
