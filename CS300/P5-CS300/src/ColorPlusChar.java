//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           ColorPlusChar
// Files:           (Color.java, FourBytes.java, Image.java, ColorPlusChar.java, Memeage.java, MemeageTests.java)
// Course:          (CS300, Fall, 2019)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecturer's Name: (Mouna AYARI BEN HADJ KACEM)
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources: NONE
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Purpose: hide a single 8-bit ASCII character code within the two least
 * significant bits of each color component (alpha, red, green, and blue)
 * 
 * @author Ariel Fu
 * 
 */
public class ColorPlusChar extends Color {
  /**
   * Calls constructor of Color class that takes in another Color object as the
   * parameter and hids the character given in as input
   * 
   * 
   * @param color     - color parameter of the pixel
   * @param character - character that constructor is supposed to hide.
   */
  public ColorPlusChar(Color color, char character) {
    super(color);

    // call hideChar to prevent the same code being written again!
    hideChar(character);

  }

  /**
   * Calls constructor of Color class that takes in another Color object as the
   * parameter
   * 
   * @param color- Color object
   */
  public ColorPlusChar(Color color) {
    super(color);
  }

  /**
   * Stores 8-bit character within the least significant bits of color components
   * 
   * @param character - character to hide
   */
  public void hideChar(char character) {

    FourBytes charsPiecedTogether = new FourBytes(character);
    // split up the character into 4 sets of 2 bits and replace the least
    // significant bits of the colors with the 2bits.
    int brokenUpChars = charsPiecedTogether.getBits(2, 6);
    this.setBits(2, 24, brokenUpChars);
    brokenUpChars = charsPiecedTogether.getBits(2, 4);
    this.setBits(2, 16, brokenUpChars);
    brokenUpChars = charsPiecedTogether.getBits(2, 2);
    this.setBits(2, 8, brokenUpChars);
    brokenUpChars = charsPiecedTogether.getBits(2, 0);
    this.setBits(2, 0, brokenUpChars);

  }

  /**
   * Retrieves 8-bit character from the least significant bits of color components
   * 
   * @return character that was given in by the constructor
   */
  public char revealChar() {
    char convertedChar = ' ';
    FourBytes object = new FourBytes(0);
    // Piece back teh 8bit broken up into 4 sets of 2 inside the 32bit
    object.setBits(2, 6, this.getBits(2, 24));
    object.setBits(2, 4, this.getBits(2, 16));
    object.setBits(2, 2, this.getBits(2, 8));
    object.setBits(2, 0, this.getBits(2, 0));

    convertedChar = (char) object.getBits(8, 0);
    return convertedChar;

  }
}
