import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeTest {
  TicTacToe test;

  @BeforeEach
  void setUp() throws Exception {
    test = new TicTacToe();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testTictactoeDraw() {
    int[][] moves = { { 0, 0 }, { 1, 1 } };
    assertEquals("Pending", test.tictactoe(moves));
  }

  @Test
  void testTicTacToeAWinHorizontal() {
    int[][] moves = { { 0, 0 }, { 1, 1 }, { 0, 1 }, { 2, 2 }, { 0, 2 } };
    assertEquals("A", test.tictactoe(moves));
  }

  @Test
  void testTicTacToeAWinVertical() {
    int[][] moves = { { 0, 0 }, { 1, 1 }, { 1, 0 }, { 0, 1 }, { 2, 0 } };
    assertEquals("A", test.tictactoe(moves));
  }

  @Test
  void testTicTacToeAWinDiagonal() {
    int[][] moves = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 0 }, { 2, 2 } };
    assertEquals("A", test.tictactoe(moves));

    int[][] secondDiagonal = { { 0, 2 }, { 0, 1 }, { 1, 1 }, { 0, 0 }, { 2, 0 } };
    assertEquals("A", test.tictactoe(secondDiagonal));
  }

  @Test
  void testWrongAnswer() {
    int[][] moves = { { 0, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 2, 0 } };
    assertEquals("B", test.tictactoe(moves));
  }
}
