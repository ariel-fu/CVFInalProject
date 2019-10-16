import java.io.File;
import java.io.IOException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           MemeageTests
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
 * Tests the methods created in Color, FourBytes, Image, and ColorPlusChar
 * 
 * @author Ariel Fu
 * 
 */
public class MemeageTests {
  /**
   * Tests FourBytes' bits getter method
   * 
   * @return true if 13 returned from getBits(4,10);
   */

  public static boolean testFourBytesGetBits() {
    boolean returnValue = false;
    FourBytes testSetTo1 = new FourBytes(0);

    for(int i = 0; i < 32; i++) {
      testSetTo1.setBits(1, i, 1);
      if(testSetTo1.getBits(1, i) != 1) {
        System.out.println(testSetTo1.getBits(1, i));
        returnValue = false;
      }

    }
    returnValue = true;

    FourBytes testJavaDocExample = new FourBytes(0);
    testJavaDocExample.setBits(4, 10, 13);
    int testResult = testJavaDocExample.getBits(4, 10);
    if(testResult != 13) {
      returnValue = false;
    }
    return returnValue;

  }

  /**
   * Tests FourBytes' bits setter method
   * 
   * @return true if sets bit to 5 and the getter method returns 5.
   */
  public static boolean testFourBytesSetBits() {
    FourBytes testSetTo1 = new FourBytes(0);
    boolean returnValue = false;
    testSetTo1.setBits(1, 24, 1);
    testSetTo1.setBits(1, 16, 1);
    testSetTo1.setBits(1, 8, 1);
    testSetTo1.setBits(1, 0, 1);
    for(int i = 0; i < 24; i += 8) {
      int getBit = testSetTo1.getBits(1, i);
      if(getBit != 1) {
        returnValue = false;
      }
    }
    returnValue = true;

    FourBytes testJavaDocExample = new FourBytes(0);
    testJavaDocExample.setBits(3, 8, 13);
    int testResult = testJavaDocExample.getBits(4, 8);
    if(testResult != 5) {
      returnValue = false;
    }
    return returnValue;

  }

  /**
   * Tests Color's constructor with Color as the parameter and tests getARGB &
   * setARGB
   * 
   * @return true if it correctly sets the bits
   */
  public static boolean testColor() {
    int a = 0;
    int r = 0;
    int g = 0;
    int b = 0;
    Color test = new Color(a, r, g, b);
    test.setAlpha(1);
    a = test.getAlpha();
    test.setRed(10);
    r = test.getRed();
    test.setGreen(100);
    g = test.getGreen();
    test.setBlue(1000);
    b = test.getBlue();

    // Calculate the ARGB
    int argb = (int) ((a * Math.pow(2, 24)) + (r * Math.pow(2, 16)) + (g * Math.pow(2, 8))
            + (b * Math.pow(2, 0)));
    if(test.getARGB() != argb) {
      System.out.println(a + " " + r + " " + g + " " + b);
      System.out.println(test.getARGB() + " " + argb);
      return false;
    }
    return true;

  }

  /**
   * Tests Image's getters and getColor (Green, Blue, Red)
   * 
   * @return true if the width + height are both 3, Green = 255, Blue = 255, Red=0
   */

  public static boolean testImage() {
    // TODO: fix the file(?)
    File imageFile = new File("testImage.png");
    try {
      Image x = new Image(imageFile);
      // Height should be three
      if(x.getHeight() != 3) {
        return false;
      }
      // width should be three
      if(x.getWidth() != 3) {
        return false;
      }
      Color centerColor = x.getColor(1, 1);

      // Amount of blue in the middle box should be maxed out
      if(centerColor.getBlue() != 255) {
        return false;
      }
      // Amount of green in the middle box should be maxed out
      if(centerColor.getGreen() != 255) {
        return false;
      }

      // Amount of red in the middle box should be 0
      if(centerColor.getRed() != 0) {
        return false;
      }

    } catch (IOException e) {
      // if any exceptions are caught, return false
      return false;
    }
    // else return true;
    return true;

  }

  /**
   * Tests ColorPlusChar's revealChar and hideChar (not shown, it is implemented
   * in the constructor)
   * 
   * @return true if the char is 'A'
   */
  public static boolean testColorPlusChar() {
    Color color = new Color(13312);
    char character = 'A';
    ColorPlusChar test = new ColorPlusChar(color, character);
    if(test.revealChar() != ('A')) {
      System.out.println(test.revealChar());
      return false;
    }
    test.hideChar('d');
    if(test.revealChar() != ('d')) {
      System.out.println(test.revealChar());
      return false;
    }
    return true;
  }

  /**
   * Prints out the result from the testing methods.
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(testFourBytesGetBits() + " --> getBits");
    System.out.println(testFourBytesSetBits() + " --> setBits");
    System.out.println(testColor() + " --> color");
    System.out.println(testImage() + " --> IMAGE");
    System.out.println(testColorPlusChar() + " --> colorPlusChar");
  }
}
