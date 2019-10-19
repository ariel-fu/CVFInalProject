import java.io.File;
import java.io.IOException;
import java.util.stream.LongStream;

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

    FourBytes testNumber = new FourBytes((int) Math.pow(2, 11));
    if(testNumber.getBits(1, 11) != 1) {
      return false;
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
    int a = 1;
    int r = 2;
    int g = 4;
    int b = 8;
    Color test = new Color(0, 0, 0, 0);
    test.setAlpha(a);
    test.setRed(r);
    test.setGreen(g);
    test.setBlue(b);

    if(test.getAlpha() != a) {
      System.out.println(test.getAlpha() + " " + a);
      return false;
    }

    if(test.getRed() != r) {
      System.out.println(test.getRed() + " " + r);
      return false;
    }

    if(test.getGreen() != g) {
      System.out.println(test.getGreen() + " " + g);
      return false;
    }

    if(test.getBlue() != b) {
      System.out.println(test.getBlue() + " " + b);
      return false;
    }

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
   * Tests getMeme with a regular text String, an empty String, and a String that
   * is as long as the picture.
   * 
   * @return - true if all the tests pass, more info about test passing is in the
   *         JavaDocs of the private methods.
   */
  public static boolean testMemeage() {

    // test regular text string
    if(!testMemeageString("Is this message going thru?")) {
      return false;
    }
    // test empty string
    if(!testMemeageString("")) {
      return false;
    }

    // test long string
    if(!testLongString()) {
      return false;
    }

    return true;
  }

  /**
   * Tests getMeme(), saveAs(File), and returns whether the same message is
   * outputted as the message inputted
   * 
   * @param testMessage - message to test
   * @return - true if testMessage equals to getMeme
   */
  private static boolean testMemeageString(String testMessage) {
    File memeFile = new File("image02.png");
    File saveAsFile = new File("image02SaveAs.png");
    Memeage test;
    String testcase = "testMemeageString";
    String getMeme = testMessage + "_default";

    // test regular text string
    try {
      test = new Memeage(memeFile, testMessage);
    } catch (Exception e) {
      System.out.println(testcase + " load image " + e.toString());
      return false;
    }

    // test getMeme when setting the message
    try {
      getMeme = test.getMeme();
      if(!getMeme.equals(testMessage)) {
        System.out.println(testcase + " regular " + testMessage + " " + getMeme);
        return false;
      }
    } catch (Exception e) {
      System.out.println(testcase + " regular " + testMessage + " " + e.toString());
      return false;
    }

    // test save image
    try {
      test.saveAs(saveAsFile);
    } catch (Exception e) {
      System.out.println(testcase + " save as " + e.toString());
      return false;
    }

    // test save image and then get the message from the saved file
    try {
      test = new Memeage(saveAsFile);
      getMeme = test.getMeme();
      if(!getMeme.equals(testMessage)) {
        System.out.println(testcase + " save as " + testMessage + " " + getMeme);
        return false;
      }
    } catch (Exception e) {
      System.out.println(testcase + " save as " + testMessage + " " + e.toString());
      return false;
    }

    return true;
  }

  /**
   * Tests a string that is as long as the area of the picture (length = area of
   * picture -1)
   * 
   * @return true if no errors are thrown
   */
  private static boolean testLongString() {
    String longString = "";
    boolean result = false;
    Memeage test = null;
    String testcase = "testLongString";

    File file = new File("testImage.png");
    try {
      // first set the message to null.
      test = new Memeage(file, "");
      result = true;
    } catch (Exception e) {
      System.out.println(testcase + ", load file, " + e.toString());
      result = false;
    }

    // if an exception was thrown in the try-catch block, return false.
    if(!result) {
      return result;
    }

    // Set the longString to spaces, but the length of longString will be the area
    // of the picture - 1.
    int len = test.getWidth() * test.getHeight() - 1;
    for(int i = 0; i < len; i++) {
      longString += " ";
    }

    try {
      // set the message to be the longString
      test.setMeme(longString);
      result = true;
    } catch (IllegalArgumentException e) {
      System.out.println(testcase + ", set max string, " + e.toString());
      result = false;
    }

    // if an exception is thrown in the try-catch block, return false.
    if(!result) {
      return result;
    }

    longString += " ";
    try {
      test = new Memeage(file, longString);
      result = false;
      System.out.println(testcase + ", load max+1 string, " + longString);
    } catch (Exception e) {
      result = true;
    }

    try {
      test.setMeme(longString);
      result = false;
      System.out.println(testcase + ", set max+1 string, " + longString);
    } catch (IllegalArgumentException e) {
      result = true;
    }

    if(!result) {
      return result;
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
    System.out.println(testMemeage() + " --> Memeage");
  }
}
