import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

import java.io.StringReader;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {
  private TicTacToe model;

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  // TODO: Implement your own tests cases for the controller
  @Before
  public void setUp() {
    model = new TicTacToeModel();
  }

  /**
   * XYZ.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringReader input = new StringReader("1 1 1 2 2 2 2 1 3 3 3 1 3 2 2 3 3 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(null);
  }

  @Test
  public void testInvalidRow() {
    StringReader input = new StringReader("p q 5 p");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "Not a valid number: p\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testInvalidColumnInput() {
    StringReader input = new StringReader("1 o q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "Not a valid number: o\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }


  @Test
  public void testRowOutOfBound() {
    StringReader input = new StringReader("5 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "Not a valid move: 5, 1\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testColumnOutOfBound() {
    StringReader input = new StringReader("1 4 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "Not a valid move: 1, 4\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testGameQuit() {
    StringReader input = new StringReader("q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }



  @Test
  public void testGameQuitRow() {
    StringReader input = new StringReader("2 2 2 1 q 2");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " O | X |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " O | X |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testGameQuitCol() {
    StringReader input = new StringReader("2 2 2 q 1 2");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testValidMove() {
    StringReader input = new StringReader("2 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testAlreadyExist() {
    StringReader input = new StringReader("2 1 2 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            "Not a valid move: 2, 1\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }


  @Test
  public void testValidInvalidValid() {
    StringReader input = new StringReader("2 1 1 4 1 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            "Not a valid move: 1, 4\n"
            +
            "   | O |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "Game quit! Ending game state:\n"
            +
            "   | O |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testXWinning() {
    StringReader input = new StringReader("1 1 1 2 2 1 1 3 3 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            " X | O |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            " X | O |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            " X | O | O\n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            " X | O | O\n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "-----------\n"
            +
            " X |   |  \n"
            +
            "Game is over! X wins.", gameLog.toString());
  }

  @Test
  public void testOWinning() {
    StringReader input = new StringReader("3 2 1 1 1 2 2 1 1 3 3 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "Enter a move for O:\n"
            +
            " O |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "Enter a move for X:\n"
            +
            " O | X |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "Enter a move for O:\n"
            +
            " O | X |  \n"
            +
            "-----------\n"
            +
            " O |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "Enter a move for X:\n"
            +
            " O | X | X\n"
            +
            "-----------\n"
            +
            " O |   |  \n"
            +
            "-----------\n"
            +
            "   | X |  \n"
            +
            "Enter a move for O:\n"
            +
            " O | X | X\n"
            +
            "-----------\n"
            +
            " O |   |  \n"
            +
            "-----------\n"
            +
            " O | X |  \n"
            +
            "Game is over! O wins.", gameLog.toString());
  }

  @Test
  public void testTie() {
    StringReader input = new StringReader("1 1 1 3 1 2 2 2 2 3 2 1 3 3 3 2 3 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(model);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            +
            " X |   | O\n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            " X | X | O\n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            " X | X | O\n"
            +
            "-----------\n"
            +
            "   | O |  \n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            " X | X | O\n"
            +
            "-----------\n"
            +
            "   | O | X\n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for O:\n"
            +
            " X | X | O\n"
            +
            "-----------\n"
            +
            " O | O | X\n"
            +
            "-----------\n"
            +
            "   |   |  \n"
            +
            "Enter a move for X:\n"
            +
            " X | X | O\n"
            +
            "-----------\n"
            +
            " O | O | X\n"
            +
            "-----------\n"
            +
            "   |   | X\n"
            +
            "Enter a move for O:\n"
            +
            " X | X | O\n"

            +
            "-----------\n"
            +
            " O | O | X\n"
            +
            "-----------\n"
            +
            "   | O | X\n"
            +
            "Enter a move for X:\n"
            +
            " X | X | O\n"
            +
            "-----------\n"
            +
            " O | O | X\n"
            +
            "-----------\n"
            +
            " X | O | X\n"
            +
            "Game is over! Tie game.", gameLog.toString());
  }
}
