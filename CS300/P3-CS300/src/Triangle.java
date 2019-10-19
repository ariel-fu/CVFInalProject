import processing.core.PApplet;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Triangle.java
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
 * The Triangle class creates a Triangle with three Points and a color
 * (colorIndex) (set to default --> white). Users can set the color by pressing
 * a number key from 0-7 (inclusive) and test if the current Point is inside a
 * Triangle previously created
 * 
 * @author Ariel Fu
 */
public class Triangle {
  private static final int[] COLORS = new int[] { // int packed w/8 bits of ARGB
      // WHITE, RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
      -1, -766643, -752563, -723891, -11668348, -11696908, -8106508, -766476 };
  private Point point1;
  private Point point2;
  private Point point3;
  private int colorIndex;

  /**
   * Constructor for Triangle class, sets inputed Points of the Triangle and the
   * color, which will be the default white
   * 
   * @param point1
   * @param point2
   * @param point3
   * @param colorIndex
   */
  public Triangle(Point point1, Point point2, Point point3, int colorIndex) {
    this.point1 = point1;
    this.point2 = point2;
    this.point3 = point3;
    this.colorIndex = colorIndex;
  }

  /**
   * Sets the color of the Triangle to the colorIndex (number between 0-7,
   * inclusive)
   * 
   * @param colorIndex
   */
  public void setColor(int colorIndex) {
    this.colorIndex = colorIndex;

  }

  /**
   * Sets the color of the Triangle first, then draws the Triangle given three
   * Points' x & y values
   * 
   * @param drawTo
   */
  public void draw(PApplet drawTo) {
    drawTo.fill(COLORS[colorIndex]);
    drawTo.triangle(point1.getX(), point1.getY(), point2.getX(), point2.getY(), point3.getX(),
            point3.getY());
  }

  /**
   * Returns true if x & y given are in between the three Points of the current
   * Triangle
   * 
   * @param x
   * @param y
   * @return x & y (input) are in between three Points of the current Triangle
   */
  public boolean isOver(int x, int y) {
    boolean withinTriangle = false;
    if(isPointInTriangle(x, y, point1.getX(), point1.getY(), point2.getX(), point2.getY(),
            point3.getX(), point3.getY())) {
      withinTriangle = true;
    }
    return withinTriangle;

  }

  // GIVEN
  private static boolean isPointInTriangle(int px, int py, int t1x, int t1y, int t2x, int t2y,
          int t3x, int t3y) {
    px -= t1x; // don't worry about this arithmetic
    py -= t1y;
    t2x -= t1x;
    t2y -= t1y;
    t3x -= t1x;
    t3y -= t1y;
    double dotp2 = px * t2x + py * t2y;
    double dotp3 = px * t3x + py * t3y;
    double dot22 = t2x * t2x + t2y * t2y;
    double dot23 = t2x * t3x + t2y * t3y;
    double dot33 = t3x * t3x + t3y * t3y;
    double invDen = 1 / (dot33 * dot22 - dot23 * dot23);
    double a = (dot22 * dotp3 - dot23 * dotp2) * invDen;
    double b = (dot33 * dotp2 - dot23 * dotp3) * invDen;
    return (a >= 0) && (b >= 0) && (a + b < 1);
  }
}
