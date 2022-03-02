package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the TicTacToe MVC
 * assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;


  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    boolean check;
    if (m == null) {
      throw new IllegalArgumentException("Not a valid model");
    }
    check = true;
    while (!m.isGameOver()) {
      int r = 0;
      int c = 0;
      try {
        if (check) {
          out.append(m.toString() + "\n");
          out.append("Enter a move for " + m.getTurn().toString() + ":\n");
          check = false;
        }

        String input = scan.next();
        if (input.equalsIgnoreCase("q")) {
          out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
          break;
        }
        try {
          r = Integer.parseInt(input) - 1;
          input = scan.next();
          if (input.equalsIgnoreCase("q")) {
            out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
            break;
          }
          c = Integer.parseInt(input) - 1;
          m.move(r, c);
          check = true;
        } catch (NumberFormatException e) {
          out.append("Not a valid number: " + input + "\n");
        } catch (IllegalArgumentException i) {
          out.append("Not a valid move: " + (r + 1) + ", " + (c + 1) + "\n");
        }
      } catch (IOException e) {
        throw new IllegalStateException("Append failed");
      }
    }

    if (m.isGameOver()) {
      try {
        out.append(m.toString() + "\n");
        if (m.getWinner() != null) {
          out.append("Game is over! " + m.getWinner().toString() + " wins.");
        } else {
          out.append("Game is over! Tie game.");
        }
      } catch (IOException e) {
        throw new IllegalStateException("Append failed", e);
      }
    }
  }
}

