import java.util.EmptyStackException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Canvas
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
    // if the height or the width is less than or equal to , throw an
    // IllegalArgumentException.
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Out of bounds for width or height: " + width
          + " width value " + height + " height value.");
    }
    drawingArray = new char[height][width];
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        drawingArray[row][column] = ' ';
      }
    }
    // set the width and the height accordingly.
    this.width = width;
    this.height = height;
    // then create two new DrawingStack.
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
    // determine if the row & col given are in-bounds, throw
    // IllegalArgumentException if the row and the col are out of bounds.
    if (!validRowCol(row, col)) {
      throw new IllegalArgumentException("The row and the column given are out of bounds.");
    }
    // determine the previous character at [row][col]
    DrawingChange currChange = new DrawingChange(row, col, drawingArray[row][col], c);
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
   * Checks if the row and the col are within bounds.
   * 
   * @param row - row
   * @param col - col
   * @throws IllegalArgumentException if the row or the col input are out of
   *                                  bounds
   * @return true if the row and the col given are within bounds.
   */
  private boolean validRowCol(int row, int col) {
    if (row >= width || col >= height || row < 0 || col < 0) {
      return false;
    }

    return true;
  }

  /**
   * Undo the most recent drawing change, then updates the contents of the
   * drawingArray appropriately.
   * 
   * @return true if the undone DrawingChange is popped off the undoStack and if
   *         the DrawingChange is pushed onto the redoStack.
   */
  public boolean undo() {
    DrawingChange recentDrawingChange;
    try {
      // get the most recent drawing change
      recentDrawingChange = undoStack.pop();
    } catch (EmptyStackException e) {
      return false;
    }
    // push the undo DrawingChange onto the redoStack.
    redoStack.push(recentDrawingChange);

    // set the new character at [row][col] to the previous character
    drawingArray[recentDrawingChange.row][recentDrawingChange.col] = recentDrawingChange.prevChar;
    // return if the undo method worked as expected.
    return evaluate(undoStack, recentDrawingChange);
  }

  /**
   * Evaluates if the undo or redo method popped and pushed onto the Stack the
   * correct DrawingChanges.
   * 
   * @param stack         - undo or redo stack
   * @param drawingChange - the most recent DrawingChange done to the Canvas
   * @return
   */
  private boolean evaluate(DrawingStack stack, DrawingChange drawingChange) {

    // check if the undoStack and the redoStack have been changed appropriately.
    try {
      // top of the stack should not be equal to the most recent change.
      if (stack.peek() == drawingChange) {
        return false;
      }
    } catch (EmptyStackException e) {
    } // should throw an EmptyStackException if the stack is empty after the undo.
    catch (Exception e) {
      return false; // if any other EmptyStackException is thrown, return false.
    }
    if (drawingArray[drawingChange.row][drawingChange.col] != drawingChange.prevChar) {
      return false;
    }
    // if all test cases pass, return true.
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
    DrawingChange recentDrawingChange;
    try {
      // get the most recent undo change from the redoStack
      recentDrawingChange = redoStack.pop();
    } catch (EmptyStackException e) {
      return false;
    }
    // push the most recent undo change (back) onto the undoStack.
    undoStack.push(recentDrawingChange);

    // update the drawingArray to the new character given at the draw method (?)
    drawingArray[recentDrawingChange.row][recentDrawingChange.col] = recentDrawingChange.newChar;

    // otherwise, it was a successful redo!
    return evaluate(redoStack, recentDrawingChange);
  }

  /**
   * Prints out the history of redos and undos
   */
  public void printHistory() {

    int i = undoStack.size();
    for (DrawingChange changes : undoStack) {
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
  @Override
  public String toString() {
    String stringVersion = "";
    for (int row = 0; row < drawingArray.length; row++) {
      for (int column = 0; column < drawingArray[row].length; column++) {

        stringVersion += drawingArray[row][column];

      }
      // add a new line
      stringVersion += System.lineSeparator();
    }
    return stringVersion;
  }

  /**
   * Returns the char[][] that represents the Canvas
   * 
   * @return drawingArray - representation of the Canvas
   */
  public char[][] getCanvas() {
    return drawingArray;
  }

}