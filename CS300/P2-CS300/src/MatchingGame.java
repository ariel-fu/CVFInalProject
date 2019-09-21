//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Matching game of 2X6 images on the board
// Files:           CS300MatchingGame.JAR
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

import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * This is a game. The player picks two cards at a time and if they match, 
 * the player can pick two more cards. If they don't match, the player has to pick a different 
 * set of two cards. Once all the cards are matched, the player wins.
 * @author Ariel
 *
 */
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
   * 
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
    // array of unused coordinates
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
        // clean up array if set an element to -1 (clean up -1 in array)
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
      // set cards visible here (if needed)

    }
  }

  /**
   * Callback method called each time the user presses a key If the user presses
   * the 'n' key (uppercase or lowercase) it will restart the game
   */
  public static void keyPressed() {
    if(processing.key == 'N' || processing.key == 'n') {
      initGame();
    }
  }

  /**
   * Callback method draws continuously this application window display
   */
  public static void draw() {
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    for(int i = 0; i < cards.length; i++) {
      cards[i].draw();
    }

    displayMessage(message);
  }

  /**
   * Displays a given message to the display window
   * 
   * @param message to be displayed to the display window
   */
  public static void displayMessage(String message) {
    processing.fill(0);
    processing.textSize(20);
    processing.text(message, processing.width / 2, 50);
    processing.textSize(12);
  }

  /**
   * Checks whether the mouse is over a given Card
   * 
   * @return true if the mouse is over the storage list, false otherwise
   */
  public static boolean isMouseOver(Card card) {
    int cardHeight = card.getHeight();
    int cardWidth = card.getWidth();
    float cardX = card.getX();
    float cardY = card.getY();
    // if mouseX is between the two far ends of the card, it is within the width
    if(processing.mouseX > cardX - cardWidth / 2 && processing.mouseX < cardX + cardWidth / 2) {
      // if mouseY is between the two far ends of the card, it is within the height
      if(processing.mouseY > cardY - cardHeight / 2 && processing.mouseY < cardY + cardHeight / 2) {
        return true;
      }
    }
    return false;
  }
  /**
   * @return the current card when the mouse is over it
   * if the mouse isn't over a card, it returns null
   */

  private static Card CardClicked() {
    for(int i = 0; i < cards.length; i++) {
      if(isMouseOver(cards[i])) {
        return cards[i];
      }
    }
    return null;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    displayMessage(message);

    /*
     * gets current selected card
     * @return 
     */
    Card currentSelectedCard = CardClicked();
    //if no selected card or select a selected card, do nothing
    if(currentSelectedCard == null) {
      return;
    }
    if (currentSelectedCard==selectedCard1 || currentSelectedCard ==selectedCard2) {
      return;
    }

    // not all cards are matched yet
    if(matchedCardsCount < 6) {
      // hide 2 cards that did not match
      if(selectedCard1 != null && selectedCard2 != null) {
        selectedCard1.setVisible(false);
        selectedCard2.setVisible(false);
        selectedCard1.deselect();
        selectedCard2.deselect();
        // reset selected cards
        selectedCard1 = null;
        selectedCard2 = null;
      }

      // show the current selected cards
      currentSelectedCard.setVisible(true);
      currentSelectedCard.select();
      if(selectedCard1 == null) {
        selectedCard1 = currentSelectedCard;
      } else if(selectedCard2 == null) {
        selectedCard2 = currentSelectedCard;
      }
    }

    // 2 cards selected
    if(selectedCard1 != null && selectedCard2 != null) {

      if(!matchingCards(selectedCard1, selectedCard2)) {
        message = NOT_MATCHED;
        // leave the cards as-is, deselect when the next card is selected
      } else {
        message = MATCHED;
        matchedCardsCount++;
        // reset matched cards, no need to hide them anymore
        selectedCard1 = null;
        selectedCard2 = null;
      }
    }

    //update message if all cards are matched!!!
    if(matchedCardsCount > 5) {
      message = CONGRA_MSG;
      winner = true;
    }
  }

  /**
   * Checks whether two cards match or not
   * 
   * @param card1 reference to the first card
   * 
   * @param card2 reference to the second card
   * 
   * @return true if card1 and card2 image references are the same, false
   *         otherwise
   */
  public static boolean matchingCards(Card card1, Card card2) {
    boolean match = false;
    if(card1.getImage().equals(card2.getImage())) {
      match = true;
    }
    return match;
  }

}
