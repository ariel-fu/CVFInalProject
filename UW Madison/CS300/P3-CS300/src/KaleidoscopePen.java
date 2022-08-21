
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           KaleidoscopePen.java
// Files:           TrianglePens.java, Point.java, Triangle.java, KaleidoscopePen.java
// Course:          (CS300, Fall, 2019)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecturer's Name: (Mouna AYARI BEN HADJ KACEM)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (N/A)
// Partner Email:   (N/A)
// Partner Lecturer's Name: (N/A)
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
// Persons:         (NONE)
// Online Sources:  NONE
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import processing.core.PApplet;
import java.util.ArrayList;

/**
 * KaleidoscopePen draws out the user inputed amount of Triangles each rotated
 * at an angle determined by how many TrianglePens there are.
 * 
 * @author Ariel Fu
 */
public class KaleidoscopePen {

  private PApplet drawTo;
  private int numberOfTrianglePens;
  private ArrayList<TrianglePen> pens = new ArrayList<TrianglePen>();

  /**
   * Constructor for KaleidoscopePen, also initializes & declares the ArrayList of
   * TrianglePen (size will be the number of TrianglePens) inputed by the user
   * 
   * @param drawTo
   * @param numberOfTrianglePens
   */
  public KaleidoscopePen(PApplet drawTo, int numberOfTrianglePens) {
    this.drawTo = drawTo;
    this.numberOfTrianglePens = numberOfTrianglePens;
    pens = new ArrayList<TrianglePen>(numberOfTrianglePens);
    // initialize the insides of the ArrayList
    for(int i = 0; i < numberOfTrianglePens; i++) {
      if(i == 0) {
        TrianglePen currentTriangle = new TrianglePen(drawTo, true);
        pens.add(currentTriangle);
      } else {
        TrianglePen currentTriangle = new TrianglePen(drawTo, false);
        pens.add(currentTriangle);
      }
    }
  }

  /**
   * Rotates x & y coordinates then updates them
   * 
   * @param mouseX
   * @param mouseY
   * @param mousePressed
   * @param keyPressed
   */
  public void update(int mouseX, int mouseY, boolean mousePressed, char keyPressed) {
    for(int i = 0; i < numberOfTrianglePens; i++) {

      // rotate then update x and y coordinates
      int[] rotatedCoordinates = rotate(mouseX, mouseY, (i * (2 * Math.PI)) / numberOfTrianglePens);
      int x = rotatedCoordinates[0];
      int y = rotatedCoordinates[1];
      pens.get(i).update(x, y, mousePressed, keyPressed);
    }

  }

  /**
   * Rotates a position around the center of an 800x600 screen by the specified
   * amount, and then returns an array containing the resulting position.
   * 
   * @param x     position of the point to be rotated (0 to 800 pixels)
   * @param y     position of the point to be rotated (0 to 600 pixels)
   * @param angle amount of rotation to apply (angle in radians units: 0 to 2*PI)
   * @return the rotated position array: x @ index 0, y @ index 1
   */
  private static int[] rotate(int x, int y, double angle) {
    x -= 400; // translate center of screen to origin (0,0)
    y -= 300;
    int[] rotatedPosition = new int[] { // rotate around origin
        (int) (x * Math.cos(angle) - y * Math.sin(angle)),
        (int) (x * Math.sin(angle) + y * Math.cos(angle)) };
    rotatedPosition[0] += 400; // return to center of screen
    rotatedPosition[1] += 300;
    return rotatedPosition;
  }
}
