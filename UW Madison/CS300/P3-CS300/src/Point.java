import processing.core.PApplet;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Point.java
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

/**
 * The Point class creates circles/points on the PApplet pop-up that will serve
 * as vertices for Triangles, created in the Triangle class
 * 
 * @author Ariel Fu
 */
public class Point {
  private int x;
  private int y;
  private static final int POINT_DIAMETER = 8;

  /**
   * Constructor for the Point class. Takes in inputs of x and y position and sets
   * them accordingly.
   * 
   * @param x
   * @param y
   */

  public Point(int x, int y) {
    this.x = x;
    this.y = y;

  }

  /**
   * Returns x position of current Point object
   * 
   * @return current x position of Point
   */
  public int getX() {
    return x;
  }

  /**
   * Returns y position of current Point object
   * 
   * @return current y position of Point
   */
  public int getY() {
    return y;
  }

  /**
   * Takes in x & y and sets current x & y to new x & y.
   * 
   * @param x
   * @param y
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Draws a circle with the center at x, y and a diameter of POINT_DIAMETER (8)
   * 
   * @param drawTo
   */
  public void draw(PApplet drawTo) {
    
    drawTo.fill(-1);
    drawTo.circle(x, y, POINT_DIAMETER);
  }

  /**
   * returns true when the position x, y is within the circle drawn to visualize
   * this point, otherwise returns false
   * 
   * @param x
   * @param y
   * @return true when position x, y are within the circle, false otherwise
   */
  public boolean isOver(int x, int y) {
    boolean withinCircle = false;
    double distanceX = Math.pow((x - this.x), 2);
    double distanceY = Math.pow((y - this.y), 2);
    double distance = Math.sqrt(distanceX + distanceY);
    if(distance < POINT_DIAMETER / 2) {
      withinCircle = true;
    }
    return withinCircle;

  }
}
