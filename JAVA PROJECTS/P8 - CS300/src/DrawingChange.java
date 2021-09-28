
/**
 * This class models a drawing change done to the board
 * 
 * @author Ariel
 *
 */
public class DrawingChange {
  public final int row; // y-coord
  public final int col; // x-coord
  public final char prevChar; // prev char in (row, col)
  public final char newChar; // new char in (row, col)

  /**
   * Constructor for DrawingChange that sets all the variables
   * 
   * @param row      - y-coordinate for this char
   * @param col      - x-coordinate for this char
   * @param prevChar - the char that was in this position before this char
   * @param newChar  - curr char in this coordinate
   */
  public DrawingChange(int row, int col, char prevChar, char newChar) {
    this.row = row;
    this.col = col;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }

}
