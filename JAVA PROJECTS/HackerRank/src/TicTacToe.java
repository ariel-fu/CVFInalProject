
public class TicTacToe {
  public String tictactoe(int[][] moves) {
    int[][] lastMoves;
    String lastPlayer = "";
    if (moves.length % 2 == 1) {
      // A is the odd player because it is first
      lastMoves = new int[moves.length / 2 + 1][2];
      lastPlayer = "A";
    } else {
      // B is the even player because it goes second
      lastMoves = new int[moves.length / 2][2];
      lastPlayer = "B";
    }
    int index = 0;
    // add the last player's moves to an array
    for (int i = moves.length - 1; i >= 0; i -= 2) {
      lastMoves[index] = moves[i];
      index++;
    }
    // get the last move
    int[] lastMove = moves[moves.length - 1];

    boolean win = verticalWin(lastMoves, lastMove) || horizontalWin(lastMoves, lastMove)
        || diagonalWin(lastMoves, lastMove);

    if (!win) {
      if (moves.length == 9) {
        return "Draw";
      } else {
        return "Pending";
      }
    } else {
      return lastPlayer;
    }

  }

  private boolean verticalWin(int[][] moves, int[] lastMove) {
    // get the [1]th place from the lastMove and compare it
    int verticalPlace = lastMove[1];
    int count = 0;

    for (int i = 0; i < moves.length; i++) {
      int[] currentArray = moves[i];
      int currentValue = currentArray[1];
      if (verticalPlace == currentValue) {
        count++;
      }
    }
    return count == 3;
  }

  private boolean horizontalWin(int[][] moves, int[] lastMove) {
    // get the [0]th place and compare it
    int horizontalPlace = lastMove[0];
    int count = 0;
    for (int i = 0; i < moves.length; i++) {
      int[] currentArray = moves[i];
      int currentValue = currentArray[0];
      if (horizontalPlace == currentValue) {
        count++;
      }
    }
    return count == 3;
  }

  private boolean diagonalWin(int[][] moves, int[] lastMove) {
    int add2 = 0;
    int sameValues = 0;
    for (int i = 0; i < moves.length; i++) {
      int[] currMove = moves[i];
      if (currMove[0] == currMove[1]) {
        sameValues++;
      }
      if (currMove[0] + currMove[1] == 2) {
        add2++;
      }
    }

    return (sameValues == 3) || (add2 == 3);

  }
}
