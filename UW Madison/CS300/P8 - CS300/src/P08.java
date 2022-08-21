import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

	@Test
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
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			test.draw(5, 2, 'e');
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

		try {
			test.draw(5, 5, 'e');
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			test.draw(2, 2, 'e');
			assertTrue(true);
		} catch (IllegalArgumentException e) {
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}

		String after = test.toString();

		// does it overwrite the char at the current position? (if the toString before
		// equals to the toString after, it did not overwrite it!)
		assertTrue(!after.equals(before));

	}

	@Test
	public void testCanvasRedo() {
		Canvas test = new Canvas(2, 2);
		// throws an emptyStackException if the redoStack is empty and try to redo
		// before there is anything to redo.
		try {
			test.redo();
			assertTrue(false);
		} catch (EmptyStackException e) {
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

	@Test
	public void testCanvasUndo() {
		// throws an emptyStackException if the undoStack is empty and try to undo
		// before drawing anything
		Canvas test = new Canvas(2, 2);
		// throws an emptyStackException if the redoStack is empty and try to redo
		// before there is anything to redo.
		try {
			test.undo();
			assertTrue(false);
		} catch (EmptyStackException e) {
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

	@Test
	public void testIterator() {
		LinkedNode<DrawingChange> x = new LinkedNode<DrawingChange>(new DrawingChange(0, 0, ' ', 'e'));
		DrawingStack stack = new DrawingStack();
		stack.push(x.getData());
		Iterator<DrawingChange> iterate = stack.iterator();
		assertTrue(iterate.hasNext());
		assertTrue(iterate.next().equals(x.getData()));
		try {
			iterate.next();
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}

		DrawingChange[] changes = new DrawingChange[3];
		for (int i = 0; i < changes.length; i++) {
			changes[i] = new DrawingChange(i, i + 1, ' ', 'a');
		}
		assertTrue(changes[1].newChar == 'a');
		assertTrue(changes[0].row == 0);
		assertTrue(changes[2].col == 3);

	}

	@Test
	public void testDrawingStack() {
		DrawingStack test = new DrawingStack();
		assertTrue(test.isEmpty());
		DrawingChange value1 = new DrawingChange(0, 2, 'e', 'j');
		test.push(value1);
		assertTrue(test.size() == 1);
		DrawingChange value2 = new DrawingChange(3, 2, ' ', 'f');
		test.push(value2);
		assertTrue(test.size() == 2);
		
		DrawingChange peek = test.peek();
		assertTrue(equals(peek, value2 ));
		peek = test.pop();
		assertTrue(equals(peek, value2));
		
		peek = test.pop();
		assertTrue(equals(peek, value1));
		
	}
	
	
	
	
	private boolean equals(DrawingChange a, DrawingChange b) {
		if(a.newChar != b.newChar) {
			return false;
		}
		if(a.prevChar != b.prevChar) {
			return false;
		}
		if(a.row != b.row) {
			return false;
		}
		if(a.col != b.col) {
			return false;
		}
		
		return true;
	}

}
