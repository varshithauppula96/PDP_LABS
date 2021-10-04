import org.junit.Test;

import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;


/**
 * Java Test class to test the functionality of a tic tac toe game.
 */
public class TicTacToeModelTest {
  private TicTacToe test1 = new TicTacToeModel();

  @Test
  public void testTurn() {
    test1.move(0, 0);
    assertEquals(Player.O, test1.getTurn());
  }

  @Test
  public void testHorizontalWin() {
    test1.move(0, 0); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(2, 1); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(0, 1);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertFalse(test1.isGameOver());
    test1.move(0, 2);
    assertTrue(test1.isGameOver());
    assertEquals(Player.X, test1.getWinner());
  }

  @Test
  public void testDiagonalWin() {
    test1.move(0, 1); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(0, 0); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(0, 2);
    assertFalse(test1.isGameOver());
    test1.move(1, 1);
    assertFalse(test1.isGameOver());
    test1.move(1, 2);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertTrue(test1.isGameOver());
    assertEquals(Player.O, test1.getWinner());
  }

  @Test
  public void testVerticalWin() {
    test1.move(0, 0); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(2, 1); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(1, 0);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertFalse(test1.isGameOver());
    test1.move(2, 0);
    assertTrue(test1.isGameOver());
    assertEquals(Player.X, test1.getWinner());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPositionOccupied() {
    test1.move(0, 0);
    assertEquals(Player.O, test1.getTurn());
    assertEquals(Player.X, test1.getMarkAt(0, 0));
    test1.move(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutofBoard() {
    test1.move(-1, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    test1.move(0, 1); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(0, 0); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(0, 2);
    assertFalse(test1.isGameOver());
    test1.move(1, 1);
    assertFalse(test1.isGameOver());
    test1.move(1, 2);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertTrue(test1.isGameOver());
    assertEquals(Player.O, test1.getWinner());
    test1.move(2, 1); // 2,2 is an empty position
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    test1.getMarkAt(-5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    test1.getMarkAt(0, -15);
  }

  @Test
  public void testDrawGame() {
    test1.move(0, 0);
    assertEquals(Player.O, test1.getTurn());
    test1.move(2, 0);
    assertEquals(Player.X, test1.getTurn());
    test1.move(1, 1);
    test1.move(2, 2);
    test1.move(2, 1);
    test1.move(0, 1);
    test1.move(1, 0);
    test1.move(1, 2);
    test1.move(0, 2);
    assertTrue(test1.isGameOver());
    assertNull(test1.getWinner());
    assertEquals(" X | O | X\n"
            + "-----------\n"
            + " X | X | O\n"
            + "-----------\n"
            + " O | X | O", test1.toString());
    System.out.println("\n Test 8");
    System.out.println(test1.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveAfterDrawGame() {
    test1.move(0, 0);
    assertEquals(Player.O, test1.getTurn());
    test1.move(2, 0);
    assertEquals(Player.X, test1.getTurn());
    test1.move(1, 1);
    test1.move(2, 2);
    test1.move(2, 1);
    test1.move(0, 1);
    test1.move(1, 0);
    test1.move(1, 2);
    test1.move(0, 2);
    assertTrue(test1.isGameOver());
    assertNull(test1.getWinner());
    test1.move(1, 2);
  }

  @Test
  public void testGetBoard() {
    test1.move(0, 1); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(0, 0); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(0, 2);
    assertFalse(test1.isGameOver());
    test1.move(1, 1);
    assertFalse(test1.isGameOver());
    test1.move(1, 2);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertTrue(test1.isGameOver());
    assertEquals(Player.O, test1.getWinner());
    Player[][] newboard = test1.getBoard();
    assertEquals(Player.X, newboard[0][1]);
    assertEquals(Player.O, newboard[0][0]);
    assertEquals(Player.X, newboard[0][2]);
  }


  @Test
  public void TestFullBoardAndWinnerDiagonal() {
    test1.move(0, 0); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(1, 2); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(0, 2);
    assertFalse(test1.isGameOver());
    test1.move(0, 1);
    assertFalse(test1.isGameOver());
    test1.move(2, 1);
    assertFalse(test1.isGameOver());
    test1.move(1, 0);
    assertFalse(test1.isGameOver());
    test1.move(1, 1);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertFalse(test1.isGameOver());
    test1.move(2, 0);
    assertTrue(test1.isGameOver());
    assertEquals("X", test1.getWinner().toString());
  }

  @Test
  public void testToString() {
    test1.move(0, 0); //X starts game
    assertFalse(test1.isGameOver());
    test1.move(2, 1); // Y turn
    assertFalse(test1.isGameOver());
    test1.move(1, 0);
    assertFalse(test1.isGameOver());
    test1.move(2, 2);
    assertFalse(test1.isGameOver());
    test1.move(2, 0);
    assertTrue(test1.isGameOver());
    assertEquals(Player.X, test1.getWinner());
    assertEquals(" X |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + " X | O | O", test1.toString());
  }


}