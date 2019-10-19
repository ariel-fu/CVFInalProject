import javax.print.PrintService;

/**
 * Grid Class that represents a Battleship Grid both for the user player and the
 * computer
 * 
 * @author Ariel
 *
 */
public class Grid implements GridInterface {
  public static final int NUM_ROWS = 10;
  public static final int NUM_COLS = 10;
  private Location[][] grid = new Location[NUM_ROWS][NUM_COLS];

  public Grid() {
    for(int i = 0; i < NUM_ROWS; i++) {
      for(int j = 0; j < NUM_COLS; j++) {
        grid[i][j] = new Location();
      }
    }
  }

  public void markHit(int row, int col) {
    
    grid[row][col].markHit();

  }

  public void markMiss(int row, int col) {
    
    grid[row][col].markMiss();

  }

  public void setStatus(int row, int col, int status) {
    
    grid[row][col].setStatus(status);
  }

  public int getStatus(int row, int col) {
    return grid[row][col].getStatus();

  }

  public boolean alreadyGuessed(int row, int col) {
    return !grid[row][col].isUnguessed();
  }

  public void setShip(int row, int col, boolean val) {
    grid[row][col].setShip(val);

  }

  public boolean hasShip(int row, int col) {
    return grid[row][col].hasShip();
  }

  public Location get(int row, int col) {
    return grid[row][col];
  }

  public int numRows() {
    return NUM_ROWS;
  }

  public int numCols() {
    return NUM_COLS;
  }

  public void printStatus() {
    System.out.println(" 1 2 3 4 5 6 7 8 9 10");
    for(int i = 0; i < grid.length; i++) {
      int convertToAscii = 65 + i;
      System.out.print((char) convertToAscii);
      for(int j = 0; j < grid[i].length; j++) {
        if(grid[i][j].checkHit()) {
          System.out.print(" X");
        } else if(grid[i][j].checkMiss()) {
          System.out.print(" O");
        } else {
          System.out.print(" -");
        }
      }
      System.out.println();
    }
  }

  public void printShips() {
    System.out.println("1 2 3 4 5 6 7 8 9 10");
    for(int i = 0; i < NUM_ROWS; i++) {
      int convertToAscii = 65 + i;
      System.out.print((char) convertToAscii);
      for(int j = 0; j < NUM_COLS; j++) {
        if(hasShip(i, j)) {
          System.out.print(" X");
        } else {
          System.out.print(" - ");
        }
      }
      System.out.println();
    }

  }

  public static void main(String[] args) {
    Grid test = new Grid();
    test.printShips();
  }

}
