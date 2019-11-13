import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hamcrest.core.IsEqual;

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
 * DrawingStack, Canvas, and DrawingStackIterator
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
    if (!iterate.hasNext()) {
      return false; // if the new iterator doesn't have next, return false.
    }
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
    try {
      int count = 0;
      for (DrawingChange current : stack) {
        count++;
      }
      if (count != stack.size()) {
        return false;
      }
    } catch (Exception e) {
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

    // test again with more specific bounds
    test = new Canvas(10, 15); // width of 10, height of 15
    try {
      test.draw(14, 9, 'e'); // should draw at [14][9] --> [row][col]
    } catch (Exception e) {
      return false;
    }

    try {
      test.draw(16, 11, 'q'); // should throw IllegalArgumentException
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      return false;
    }

    try {
      test.draw(15, 10, 'n'); // should also throw IllegalArgumentException
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
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
    DrawingChange head;
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
        head = new DrawingChange(0, 1, ' ', 'p');
        test.push(head);
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
      if (!compare(change, head))
        ;
      // make sure there is nothing left in the stack after removing the only change
      // pushed onto the stack.
      if (!test.isEmpty()) {
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
   * Tests undo method from Canvas Class
   * 
   * @return true if undo works as expected
   */
  public static boolean testUndo() {
    Canvas test = new Canvas(5, 5);
    // draw 'p' at [0][0]
    test.draw(0, 0, 'p');
    if (test.getCanvas()[0][0] != 'p') {
      return false;
    }
    // remove 'p' at [0][0] by using undo, adding one to the redoStack.
    test.undo();
    if (test.getCanvas()[0][0] == 'p') {
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
      if (test.redo()) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    test.draw(0, 0, 'p');
    if (test.getCanvas()[0][0] != 'p') {
      return false;
    }
    test.undo();
    if (test.getCanvas()[0][0] == 'p') {
      return false;
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
    if (!testDrawingStackPop()) {
      System.out.println("Pop method from DrawingStack");
      return false;
    }
    if (!testDrawingStackIterator()) {
      System.out.println("DrawingStackIterator Class");
      return false;
    }
    if (!testConstructorCanvas()) {
      System.out.println("canvas constructor");
      return false;
    }

    if (!testCanvasDraw()) {
      System.out.println("Canvas draw method");
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

    return true;
  }

  /**
   * Main method to print out AsciiArtTestSuite's result, true if all tests
   * passed, false and the name of the method if it failed.
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(runAsciiArtTestSuite());
  }

}
