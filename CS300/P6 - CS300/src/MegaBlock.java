//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           MegaBlock
// Files:           MegaBlock, LinkedMegaBlock, LinkedListMegaBlock, Color, MegaBlockBuilderTester
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class models a Mega Block
 * 
 */
public class MegaBlock {
  private final Color COLOR; // color of this Mega Block
  private char label; // label of this Mega Block

  /**
   * Creates a new MegaBlock object with a specific color index and a specific label
   * 
   * @param color color of this MegaBlock object
   * @param label of this MegaBlock object
   */
  public MegaBlock(Color color, char label) {
    this.COLOR = color;
    this.label = label;
  }

  /**
   * Returns the label of this MegaBlock object
   * 
   * @return the label
   */
  public char getLabel() {
    return label;
  }

  /**
   * Sets the label of this MegaBlock object to a specific one
   * 
   * @param label the label to set
   */
  public void setLabel(char label) {
    this.label = label;
  }

  /**
   * Returns the color of this MegaBlock object
   * 
   * @return the color of this Megablock object
   */
  public Color getColor() {
    return COLOR;
  }


  /**
   * Returns a String representation of this megaBlock object
   * 
   * @return a String representation of this megaBlock object
   */
  @Override
  public String toString() {
    return COLOR.toString() + " " + this.label;
  }

  /**
   * Returns true if this megaBlock is equal to otherBlock, false otherwise
   * 
   * @param otherBlock object to which this megaBlock will be compared
   * @return true if otherBlock refers to an instance of MegaBlock and the color of this megablock
   *         equals the color of the provided otherBlock.
   */
  @Override
  public boolean equals(Object otherBlock) {
    return (otherBlock instanceof MegaBlock && this.COLOR.equals(((MegaBlock) otherBlock).COLOR));
  }
  

}