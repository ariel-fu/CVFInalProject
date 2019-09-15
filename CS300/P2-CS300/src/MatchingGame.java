
//TODO: find header and place here!

import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class MatchingGame {
//Congratulations message
  private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
//Cards not matched message
  private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
//Cards matched message
  private final static String MATCHED = "CARDS MATCHED! Good Job!";
//2D-array which stores cards coordinates on the window display
  private final static float[][] CARDS_COORDINATES = new float[][] { { 170, 170 }, { 324, 170 },
      { 478, 170 }, { 632, 170 }, { 170, 324 }, { 324, 324 }, { 478, 324 }, { 632, 324 },
      { 170, 478 }, { 324, 478 }, { 478, 478 }, { 632, 478 } };
//Array that stores the card images filenames
  private final static String[] CARD_IMAGES_NAMES = new String[] { "apple.png", "ball.png",
      "peach.png", "redFlower.png", "shark.png", "yellowFlower.png" };

  private static PApplet processing; // PApplet object that represents
//the graphic display window
  private static Card[] cards; // one dimensional array of cards
  private static PImage[] images; // array of images of the different cards
  private static Random randGen; // generator of random numbers
  private static Card selectedCard1; // First selected card
  private static Card selectedCard2; // Second selected card
  private static boolean winner; // boolean evaluated true if the game is won,
//and false otherwise
  private static int matchedCardsCount; // number of cards matched so far
//in one session of the game
  private static String message; // Displayed message to the display window

  public static void main(String[] args) {
    Utility.runApplication();

  }

  /**
   * Defines the initial environment properties of this game as the program starts
   */
  public static void setup(PApplet processing) {
    MatchingGame.processing = processing;
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    images = new PImage[CARD_IMAGES_NAMES.length];
    // Load the array with images
    for(int i = 0; i < images.length; i++) {
      //// load the image file at index i as PImage object and store its reference
      //// into images[i]
      images[i] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[i]);
    }
    initGame();

  }

  /**
   * Initializes the game
   * 
   */

  public static void initGame() {
    randGen = new Random(Utility.getSeed());
    selectedCard1 = null;
    selectedCard2 = null;
    matchedCardsCount = 0;
    winner = false;
    message = "";
    cards = new Card[CARD_IMAGES_NAMES.length * 2];
    //array of unused coordinates
    int[] unusedCoordinates = new int[cards.length];
    for(int i = 0; i < unusedCoordinates.length; i++) {
      unusedCoordinates[i] = i;
    }
    for(int i = 0; i < cards.length; i++) {
      int x = 0;
      int randomNumber = 0;
      if(unusedCoordinates.length > 1) {
        randomNumber = randGen.nextInt(unusedCoordinates.length - 1);
        x = unusedCoordinates[randomNumber];
        unusedCoordinates[randomNumber] = -1; // sets element to taken and returns value
        //clean up array if set an element to -1 (clean up -1 in array)
        int oldIndex = 0;
        int[] copy = new int[unusedCoordinates.length - 1];
        for(int index = 0; index < unusedCoordinates.length; index++) {
          if(unusedCoordinates[index] == -1) {
            continue;
          } else {
            copy[oldIndex] = unusedCoordinates[index];
            oldIndex++;
          }
        }
        unusedCoordinates = copy;
      } else {
        x = unusedCoordinates[0];
      }



      int y = 0;

      cards[i] = new Card(images[i % 6], CARDS_COORDINATES[x][y], CARDS_COORDINATES[x][y + 1]);
      cards[i].setVisible(true);
      cards[i].draw();
    }

    /*
     * for(int i = 0; i < cards.length; i++) { int randCoordinates =
     * getCoordinates(nums); cards[i] = new Card(images[i % 6],
     * CARDS_COORDINATES[randCoordinates][0],
     * CARDS_COORDINATES[randCoordinates][1]); cards[i].setVisible(true);
     * cards[i].draw(); }
     */
  }

  //
  private static int getCoordinates(int[] nums) {

    return returnValue;
  }
}
