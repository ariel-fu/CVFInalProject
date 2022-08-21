
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TrianglePens.java
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
 * TrianglePen draws Points and Triangles. The user is also able to drag the
 * Triangle's Points and set the color of the Triangle.
 * 
 * @author Ariel Fu
 */
public class TrianglePen {
  private PApplet processing;
  private boolean showPoints;
  private ArrayList<Point> numOfPoints = new ArrayList<Point>();
  private ArrayList<Triangle> trianglesMade = new ArrayList<Triangle>();
  private boolean mouseWasPressed = false; // mousePressed from previous update() call
  private char keyWasPressed = '\0'; // keyPressed from previous update() call
  private Point drag = null;

  /**
   * Sets processing of TrianglePen to the input processing Sets showPoints of
   * TrianglePen to the input showPoints
   * 
   * @param processing
   * @param showPoints
   */
  public TrianglePen(PApplet processing, boolean showPoints) {
    this.processing = processing;
    this.showPoints = showPoints;
  }

  // GIVEN
  public void update(int mouseX, int mouseY, boolean mousePressed, char keyPressed) {

    // process mouse-based user input
    if(mousePressed != mouseWasPressed) {
      if(mousePressed) {
        handleMousePress(mouseX, mouseY);
      } else {
        handleMouseRelease(mouseX, mouseY);
      }
    }
    if(mousePressed) {
      handleMouseDrag(mouseX, mouseY);
    }
    mouseWasPressed = mousePressed;
    // process keyboard-based user input
    if(keyPressed != keyWasPressed) {
      handleKeyPress(mouseX, mouseY, keyPressed);
    }
    keyWasPressed = keyPressed;
    // draw everything in its current state
    draw();
  }

  /**
   * Checks if it is a Point to drag, otherwise it creates a new Point. Every
   * three Points it creates a new Triangle with the previous three Points as the
   * Triangle's vertices.
   * 
   * @param mouseX
   * @param mouseY
   */
  private void handleMousePress(int mouseX, int mouseY) {
    // run through all Points made, if the mouse is over a Point, set the Point to
    // the Point being dragged and quit out of the method.

    for(int i = 0; i < numOfPoints.size(); i++) {
      if(numOfPoints.get(i).isOver(mouseX, mouseY)) {
        // track the point as being the dragged point
        drag = numOfPoints.get(i);
        // skip making any new Points or Triangles
        return;
      }
    }
    // if there are no Points being dragged, make a new Point or Triangle
    int colorIndex = 0;
    Point currentPoint = new Point(mouseX, mouseY);
    numOfPoints.add(currentPoint);
    // every three Points creates a new Triangle
    if(numOfPoints.size() % 3 == 0) {

      // current size in Points ArrayList
      int size = numOfPoints.size();

      // Shift the size of the ArrayList down by 3 to get the first point of the
      // Triangle, shift by 2 for the second point, and shift by 1 for the third point of the Triangle
      Triangle currTriangle = new Triangle(numOfPoints.get(size - 3), numOfPoints.get(size - 2),
              numOfPoints.get(size - 1), colorIndex);

      trianglesMade.add(currTriangle);

    }

  }

  /**
   * Once the mouse releases a point, if it was not already a point, it won't do
   * anything. However, if it was already a point, it would track it as the
   * dragging point and set its position to the current position and set the
   * dragging point to null.
   * 
   * @param mouseX
   * @param mouseY
   */
  private void handleMouseRelease(int mouseX, int mouseY) {
    // if the drag point (point being dragged) is a significant value, set the
    // point's x & y to the curr x & y of the mouse, and set the drag point back to
    // null.
    if(drag != null) {
      drag.setPosition(mouseX, mouseY);
      drag = null;
    }
  }

  /**
   * Based on what number from 0-7 the user presses, it changes the color of the
   * Triangle(s) that the mouse is over
   * 
   * @param mouseX
   * @param mouseY
   * @param keyPressed
   * 
   */
  private void handleKeyPress(int mouseX, int mouseY, char keyPressed) {
    // make sure the user pressed a valid key, otherwise quit
    int colorIndex = keyPressed - '0';
    if(colorIndex < 0 || colorIndex > 7) {
      return;
    }
    // if correct key, set all triangles that the user's mouse is over to the
    // colorIndex (the key they pressed)
    ArrayList<Triangle> triangles = triangleOver(mouseX, mouseY);
    for(int i = 0; i < triangles.size(); i++) {
      if(triangles.get(i) != null) {

        triangles.get(i).setColor(colorIndex);

      }
    }

  }

  /**
   * Given the mouse's x and y values, it runs through all the Triangles created
   * and scans if the mouse is over any of the Triangles
   * 
   * @param mouseX
   * @param mouseY
   * @return all Triangles that the mouse is over
   */
  private ArrayList<Triangle> triangleOver(int mouseX, int mouseY) {
    ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    for(int i = 0; i < trianglesMade.size(); i++) {
      if(trianglesMade.get(i).isOver(mouseX, mouseY)) {
        triangles.add(trianglesMade.get(i));
      }
    }
    // returns an ArrayList of the Triangle(s) that the mouse is over
    return triangles;
  }

  /**
   * Calls the dragged point's x & y values. Constantly changing the x & y values
   * to the mouse's x & y values
   * 
   * NOTE:Depending on where the first (the one with the points) Triangle starts
   * will determine if it will overlap any Triangles or be overlapped by a
   * Triangle.
   * 
   * @param mouseX
   * @param mouseY
   */
  private void handleMouseDrag(int mouseX, int mouseY) {
    if(drag != null) {
      drag.setPosition(mouseX, mouseY);
    }
  }

  /**
   * Runs through both Points (ArrayList) and Triangles (ArrayList) to draw them.
   * Uses .draw() from each of their class
   */
  private void draw() {
    // if the user inputed showPoints, draw out the Points
    if(showPoints) {
      for(int pointsIndex = 0; pointsIndex < numOfPoints.size(); pointsIndex++) {

        numOfPoints.get(pointsIndex).draw(processing);
      }
    }

    for(int trianglesIndex = 0; trianglesIndex < trianglesMade.size(); trianglesIndex++) {

      trianglesMade.get(trianglesIndex).draw(processing);
    }

  }

}
