
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Color
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
 * Represents the color of a single pixel within an image. Includes setters and
 * getters
 * 
 * @author Ariel Fu
 * 
 */
public class Color extends FourBytes {
  /**
   * Constructor with parameter of argb (Alpha, red, green, blue)
   * 
   * @param argb - order of most significant bits to least significant bits
   */
  public Color(int argb) {
    super(argb);
    this.setARGB(argb);
  }

  /**
   * Constructor that takes in 3 colors (RGB) and alpha (opacity level) Sets each
   * color individually in FourBytes
   * 
   * @param alpha - most significant bit
   * @param red   - 2nd most significant bit
   * @param green - 2nd least significant bit
   * @param blue  - least significant bit
   */
  public Color(int alpha, int red, int green, int blue) {
    // initialize the bits to 00000000 00000000 00000000 00000000
    super(0);
    // set each color individually
    this.setAlpha(alpha);
    this.setRed(red);
    this.setGreen(green);
    this.setBlue(blue);

  }

  /**
   * Constructor that takes in another Color as input!
   * 
   * @param other - another Color object
   */
  public Color(Color other) {
    // initialize the bits to 00000000 00000000 00000000 00000000
    super(0);

    // get the ARGB of the other (Color type)
    int otherARGB = other.getARGB();
    // set the ARGB
    this.setARGB(otherARGB);

  }

  /**
   * Getter method of ARGB
   * 
   * @return int representation of all 32 bits
   */
  public int getARGB() {
    return super.getBits(32, 0);
  }

  /**
   * Getter method of Alpha (bits)
   * 
   * @return int representation of the first 8 bits
   */
  public int getAlpha() {
    return super.getBits(8, 24);
  }

  /**
   * Getter method of Red (bits)
   * 
   * @return int representation of second set of 8 bits
   */
  public int getRed() {
    return super.getBits(8, 16);
  }

  /**
   * Getter method of Green (bits)
   * 
   * @return int representation of third set of 8 bits
   */
  public int getGreen() {
    return super.getBits(8, 8);
  }

  /**
   * Getter method of Blue (bits)
   * 
   * @return int representation of fourth set of 8 bits
   * 
   */
  public int getBlue() {
    return super.getBits(8, 0);
  }

  /**
   * Sets all 32 bits to argb (input)
   * 
   * @param argb - 32bit that represents the color of one pixel
   */
  public void setARGB(int argb) {
    super.setBits(32, 0, argb);
  }

  /**
   * Sets the opacity of the pixel
   * 
   * @param alpha - opacity of pixel
   */
  public void setAlpha(int alpha) {
    super.setBits(8, 24, alpha);
  }

  /**
   * Sets the redness of the pixel
   * 
   * @param red - redness of pixel
   */
  public void setRed(int red) {
    super.setBits(8, 16, red);
  }

  /**
   * Sets the greeness of the pixel
   * 
   * @param green - greeness of the pixel
   */
  public void setGreen(int green) {
    super.setBits(8, 8, green);
  }

  /**
   * Sets the blueness of the pixel
   * 
   * @param blue - blueness of pixel
   */
  public void setBlue(int blue) {
    super.setBits(8, 0, blue);
  }
}
