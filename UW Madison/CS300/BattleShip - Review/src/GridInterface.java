
public interface GridInterface {
  /**
   * Mark a hit in this location by calling the markHit method on the Location
   * object.
   * 
   * @param row
   * @param col
   */
  public void markHit(int row, int col);

//Mark a miss on this location.    
  public void markMiss(int row, int col);

//Set the status of this location object.
  public void setStatus(int row, int col, int status);

//Get the status of this location in the grid  
  public int getStatus(int row, int col);

  /**
   * Return whether or not this Location has already been guessed.
   * 
   * @param row
   * @param col
   * @return
   */
  public boolean alreadyGuessed(int row, int col);

  /**
   * Set whether or not there is a ship at this location to the val
   * 
   * @param row
   * @param col
   * @param val
   */
  public void setShip(int row, int col, boolean val);

  /**
   * Return whether or not there is a ship here
   * 
   * @param row
   * @param col
   * @return
   */
  public boolean hasShip(int row, int col);

  /**
   * Get the Location object at this row and column position
   * 
   * @param row
   * @param col
   * @return
   */
  public Location get(int row, int col);

  /**
   * Return the number of rows in the Grid
   * 
   * @return
   */
  public int numRows();

  /**
   * Return the number of columns in the grid
   * 
   * @return
   */
  public int numCols();

  /**
   * Print the Grid status including a header at the top
   * 
   * that shows the columns 1-10 as well as letters across the side for A-J If
   * there is no guess print a - If it was a miss print a O If it was a hit, print
   * an X A sample print out would look something like this:
   * 
   * 1 2 3 4 5 6 7 8 9 10 A - - - - - - - - - - B - - - - - - - - - - C - - - O -
   * - - - - - D - O - - - - - - - - E - X - - - - - - - - F - X - - - - - - - - G
   * - X - - - - - - - - H - O - - - - - - - - I - - - - - - - - - - J - - - - - -
   * - - - -
   * 
   */
  public void printStatus();

//Print the grid and whether there is a ship at each location.
//If there is no ship, you will print a - and if there is a
//ship you will print a X. You can find out if there was a ship
//by calling the hasShip method.
//
//  1 2 3 4 5 6 7 8 9 10 
//A - - - - - - - - - - 
//B - X - - - - - - - - 
//C - X - - - - - - - - 
//D - - - - - - - - - - 
//E X X X - - - - - - - 
//F - - - - - - - - - - 
//G - - - - - - - - - - 
//H - - - X X X X - X - 
//I - - - - - - - - X - 
//J - - - - - - - - X - 
  public void printShips();
}
