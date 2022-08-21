import java.io.File;
import java.io.IOException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Memeage
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
// Online Sources: Google search for making Files to test
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * Represents the image that may possible include a String meme hidden within
 * its colors
 * 
 * @author Ariel
 *
 */
public class Memeage extends Image {
  /**
   * Constructor that takes in a file as the parameter
   * 
   * @param file - file to read
   * @throws IOException - if there is trouble reading the file
   */
  public Memeage(File file) throws IOException {
    super(file);
  }

  /**
   * Constructor that takes in a file as the parameter and a String to hide inside
   * the picture
   * 
   * @param file - file to read
   * @param meme - message to hide
   * @throws IOException              - if there is trouble reading the file
   * @throws IllegalArgumentException - if the
   */
  public Memeage(File file, String meme) throws IOException, IllegalArgumentException {
    super(file);

    // To avoid code rewriting, reuse setMeme(String meme) since they
    // would be the same code
    setMeme(meme);
  }

  /**
   * Stores each character in the String into a single pixel.
   * 
   * @param meme - String to be hidden
   * @throws IllegalArgumentException - if the length is larger than the width *
   *                                  the height of the number of pixels - or if
   *                                  the character's code is greater than 127
   */
  public void setMeme(String meme) throws IllegalArgumentException {
    // Check String length
    if(meme.length() >= (super.getHeight() * super.getWidth())) {
      throw new IllegalArgumentException("Your meme is too long! ");
    }
    // Check if all characters in the String are acceptable
    for(int i = 0; i < meme.length(); i++) {
      char currChar = meme.charAt(i);
      if(currChar > 127) {
        throw new IllegalArgumentException("Probably not a ASCII character...");
      }
    }
    // Run through the whole picture and add each character of the meme String to
    // each pixel
    int index = 0;
    Color currentPixelInPicture;
    ColorPlusChar currCharInString;

    for(int y = 0; y < super.getHeight(); y++) {
      for(int x = 0; x < super.getWidth(); x++) {
        currentPixelInPicture = super.getColor(x, y);
        if(meme.length() <= index) {
          // after the last character in the meme String, add the null character and quit
          // the inner loop.
          currCharInString = new ColorPlusChar(currentPixelInPicture, '\0');
          super.setColor(x, y, currCharInString);
          break;
        }

        currCharInString = new ColorPlusChar(currentPixelInPicture, meme.charAt(index));
        super.setColor(x, y, currCharInString);
        index++;
      }
      if(meme.length() <= index) {
        // break out of the outer for loop
        break;
      }

    }

  }

  /**
   * Recovers the String that was previously stored in this image
   * 
   * @return the meme that was hidden in the picture
   * @throws IllegalStateException if any character's code is greater than 127 or
   *                               if the last character isn't \0 (the null
   *                               character)
   */
  public String getMeme() throws IllegalStateException {
    String hiddenMessage = "";
    Color currentPixelInPicture;
    ColorPlusChar currCharInMessage;
    boolean imdone = false;
    boolean hasNullChar = false;
    for(int y = 0; y < super.getHeight(); y++) {
      for(int x = 0; x < super.getWidth(); x++) {

        currentPixelInPicture = super.getColor(x, y);
        currCharInMessage = new ColorPlusChar(currentPixelInPicture);
        char revealedChar = currCharInMessage.revealChar();

        if(revealedChar > 127 || revealedChar < 0) {
          throw new IllegalStateException("Not an ACSII Character.");
        }

        if(revealedChar == '\0') {
          imdone = true;
          hasNullChar = true;
          break;
        }

        hiddenMessage += revealedChar;
      }
      if(imdone) {
        break;
      }
    }

    // If the last char isn't the null character, throw an IllegalStateException
    if(!hasNullChar) {
      throw new IllegalStateException("The last character must be '\0' ");
    }

    return hiddenMessage;
  }

  public static void main(String[] args) {
    File file = new File("image01.png");
    try {
      Memeage test = new Memeage(file);
      System.out.println(test.getMeme());
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
