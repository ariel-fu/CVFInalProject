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
 * This class represents a collection of a row field, a column field, a previous
 * char field, and a new char field.
 * 
 * @author Ariel
 *
 */
public class DrawingChange {
  public final int row; // a row (y-coordinate) for this DrawingChange
  public final int col; // a col (x-coordinate) for this DrawingChange
  public final char prevChar; // previous character in the (row, col) position
  public final char newChar; // new character in the (row, col) position

  /**
   * Constructor for DrawingChange class
   * 
   * @param row      - y-coordinate
   * @param col      - x-coordinate
   * @param prevChar - previous char in this position (row, col)
   * @param newChar  - current / new char in this position (row, col)
   */
  public DrawingChange(int row, int col, char prevChar, char newChar) {
    this.row = row;
    this.col = col;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }

}
