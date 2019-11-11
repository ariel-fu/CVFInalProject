import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DrawingChange
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
 * This class represents an ACII - art image.
 * 
 * @author Ariel
 *
 */
public class Canvas {
  private final int width; // width of the canvas
  private final int height; // height of the canvas
  private char[][] drawingArray; // 2D character array to store the drawing
  private final DrawingStack undoStack; // store previous changes for undo.
  private final DrawingStack redoStack; // store undone changes for redo

  /**
   * Constructor creates a new canvas which is initially blank. (Uses spaces for
   * empty space.)
   * 
   * @param width  - width of the canvas
   * @param height - height of the canvas
   * @throws IllegalArgumentException - if the height is 0 or less OR if the width
   *                                  is 0 or less
   */
  public Canvas(int width, int height) {
    if(width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Out of bounds for width or height: " + width
              + " width value " + height + " height value.");
    }
    // TODO : review if width and height matches up!
    drawingArray = new char[height][width];
    for(int row = 0; row < height; row++) {
      for(int col = 0; col < width; col++) {
        drawingArray[row][col] = ' ';
      }
    }
    this.width = width;
    this.height = height;
    undoStack = new DrawingStack();
    redoStack = new DrawingStack();
  }

  /**
   * Draws a character at the given position drawingArray[row][col]. If there is
   * already a char, overwrite that char
   * 
   * @param row - row of where to draw the character
   * @param col - col of where to draw the character
   * @param c   - character to be placed at [row][col]
   * @throws IllegalArgumentException - if the row and the col are out of bounds /
   *                                  off the canvas
   */
  public void draw(int row, int col, char c) {
    // determine if the rol & col given are in-bounds
    if(row >= width || col >= height || row < 0 || col < 0) {
      throw new IllegalArgumentException("The row and the column given are out of bounds.");
    }
    // determine the previous character at [row][col]
    if(drawingArray[row][col] != ' ') {
      DrawingChange currChange = new DrawingChange(row, col, drawingArray[row][col], c);
    }
    DrawingChange currChange = new DrawingChange(row, col, ' ', c);
    // create the new drawing change, and set the character at [row][col] to the new
    // character - c.

    drawingArray[row][col] = c;
    // push the recent drawing change to the undo stack
    undoStack.push(currChange);
    // After making a new change, clear the redoStack.
    while (!redoStack.isEmpty()) {
      redoStack.pop();
    }
  }

  /**
   * Undo the most recent drawing change, then updates the contents of the
   * drawingArray appropriately.
   * 
   * @return true if the undone DrawingChange is popped off the undoStack and if
   *         the DrawingChange is pushed onto the redoStack.
   */
  public boolean undo() {
    // get the most recent drawing change
    DrawingChange recentDrawingChange = undoStack.pop();

    // push the undo DrawingChange onto the redoStack.
    redoStack.push(recentDrawingChange);

    // set the new character at [row][col] to the previous character
    drawingArray[recentDrawingChange.row][recentDrawingChange.col] = recentDrawingChange.prevChar;

    // check if the undoStack and the redoStack have been changed appropriately.
    try {
      // top of the stack should not be equal to the most recent change.
      if(undoStack.peek() == recentDrawingChange) {
        return false;
      }
    } catch (EmptyStackException e) {
    } // should throw an EmptyStackException if the stack is empty after the undo.
    catch (Exception e) {
      return false; // if any other EmptyStackException is thrown, return false.
    }
    if(redoStack.peek() != recentDrawingChange) {
      return false;
    }
    if(drawingArray[recentDrawingChange.row][recentDrawingChange.col] != recentDrawingChange.prevChar) {
      return false;
    }

    return true;
  }

  /**
   * Redo the most recent undone drawing change, then updates the drawingArray
   * accordingly.
   * 
   * @return true if the redone DrawingChange is popped off the redoStack and if
   *         the Drawing Change is pushed (back) onto the redoStack.
   */
  public boolean redo() {
    // get the most recent undo change from the redoStack
    DrawingChange mostRecentUndo = redoStack.pop();

    // push the most recent undo change (back) onto the undoStack.
    undoStack.push(mostRecentUndo);

    // update the drawingArray to the new character given at the draw method (?)
    drawingArray[mostRecentUndo.row][mostRecentUndo.col] = mostRecentUndo.newChar;

    try {
      // checks if the changes were made, the top of the stack should not be the one
      // that was just popped off.
      if(redoStack.peek() == mostRecentUndo) {
        return false;
      }
    } catch (EmptyStackException e) { // this peek method should throw a EmptyStackException if the
                                      // stack is
      // empty after the redo.
    } catch (Exception e) {
      return false;
    }
    if(undoStack.peek() != mostRecentUndo) {
      return false;
    }
    if(drawingArray[mostRecentUndo.row][mostRecentUndo.col] != mostRecentUndo.newChar) {
      return false;
    }
    // otherwise, it was a successful redo!
    return true;
  }

  /**
   * Prints out the history of redos and undos
   */
  public void printHistory() {

    int i = undoStack.size();
    for(DrawingChange changes : undoStack) {
      System.out.println("#" + i + ". draw '" + changes.newChar + "' on (" + changes.row + ", "
              + changes.col + ")");
      i--;

    }
  }

  /**
   * Prints out the String version of the Canvas
   */
  public void printDrawing() {
    System.out.println(this.toString());
  }

  /**
   * Returns a printable string version of the Canvas
   * 
   * @return String version of the Canvas
   */
  public String toString() {
    String stringVersion = "";
    for(int row = 0; row < drawingArray.length; row++) {
      for(int column = 0; column < drawingArray[row].length; column++) {

        stringVersion += drawingArray[row][column];

      }
      // add a new line
      stringVersion += "\r\n";
    }
    return stringVersion;
  }

}