import java.util.EmptyStackException;

public class Canvas {
  private final int width; // width of the canvas
  private final int height; // height of the canvas
  private char[][] drawingArray; // 2D character array to store the drawing
  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

//Constructor creates a new canvas which is initially blank (use the default value
//for char type or you can use spaces)
  public Canvas(int width, int height) {
    if (width == 0) {
      throw new IllegalArgumentException("Invalid width");
    } else if (height <= 0) {
      throw new IllegalArgumentException("invalid height");
    } else {
      this.width = width;
      this.height = height;
      this.drawingArray = new char[width][height];
      this.undoStack = new DrawingStack();
      this.redoStack = new DrawingStack();
    }
  }

//Draw a character at the given position drawingArray[row][col]
  /**
   * Draws a character at the given pos in the drawingArray
   * 
   * @param row - row of the designated pos
   * @param col - col of the designated pos
   * @param c   - new character at [row][col]
   * @throws IllegalArgumentException - if the row or the col is out of the
   *                                  canvas
   */
  public void draw(int row, int col, char c) {
    if (row > width || row < 0) {
      throw new IllegalArgumentException("Row is out of the canvas");
    } else if (col > height || col < 0) {
      throw new IllegalArgumentException("Column is out of the canvas");
    } else {
      // get the char that is currently at [row][col]
      char prevChar = drawingArray[row][col];
      // add it to the undoStack
      undoStack.push(new DrawingChange(row, col, prevChar, c));

      // override it with the new char
      drawingArray[row][col] = c;
      // delete all the elements in the redo stack
      while (!redoStack.isEmpty()) {
        redoStack.pop();
      }

    }
  }

//Undo the most recent drawing change.
  /**
   * Undos the most recent drawing change
   * 
   * @return true if the undoStack was not empty and the undo was successful,
   *         false otherwise
   */
  public boolean undo() {
    DrawingChange undoChange;
    try {
      undoChange = undoStack.pop();
    } catch (EmptyStackException e) {
      return false; // nothing in the Stack to pop
    }
    // add the change to the redoStack
    redoStack.push(undoChange);

    // change the contents of drawingArray
    int row = undoChange.row;
    int col = undoChange.col;
    char prevChar = undoChange.prevChar;
    drawingArray[row][col] = prevChar;
    // return true
    return true;
  }

//Redo the most recent undone drawing change.
  /**
   * Redos the most recent undo
   * 
   * @return true if the Stack was not empty and the redo was successful, false
   *         otherwise
   */
  public boolean redo() {
    DrawingChange redoChange;
    try {
      redoChange = redoStack.pop();
    } catch (EmptyStackException e) {
      return false; // nothing in the redo stack
    }
    // add the change to the undoStack
    undoStack.push(redoChange);

    int row = redoChange.row;
    int col = redoChange.col;
    char newChar = redoChange.newChar;
    drawingArray[row][col] = newChar;
    return true;
  }

//Return a printable string version of the Canvas.
  /**
   * Returns the board as a String
   * 
   * @return String version of the Canvas
   */
  public String toString() {
    String output = "";
    for (int row = 0; row < drawingArray.length; row++) {
      for (int col = 0; col < drawingArray[row].length; col++) {
        output += drawingArray[row][col];
      }
      output += System.lineSeparator();
    }
    return output;

    /*
     * Format example: [_ is blank. Use System.lineSeparator() to put a newline
     * character between rows] X___X _X_X_ __X__ _X_X_ X___X
     */
  }

  public void printDrawing() {
    System.out.println(toString());
  }

  public void printHistory() {

  }
}
