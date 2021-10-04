package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class to implement the model for the tic tac toe game.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private Player[][] ticTacToeSize;
  private Player turn;

  /**
   * Initialise the tic tae board size as a 3x3 matrix and set the current player to X.
   */
  public TicTacToeModel() {
    this.ticTacToeSize = new Player[3][3];
    this.turn = Player.X;
  }

  /**
   * Method to display the tic tac toe board game.
   *
   * @return string of the game
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard())
            .map(row -> " " + Arrays.stream(row)
                    .map(p -> p == null ? " " : p.toString())
                    .collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toSt  ring());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }

  /**
   * Make a move by the player at a given position r and c.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException    if the game is over
   */
  @Override
  public void move(int r, int c) throws IllegalArgumentException {
    if (r < 0) {
      throw new IllegalArgumentException("rows ended");
    }
    if (c < 0) {
      throw new IllegalArgumentException("columns ended");
    }
    if (isGameOver()) {
      throw new IllegalStateException("The game is over");
    }
    if (ticTacToeSize[r][c] != null) {
      throw new IllegalArgumentException("Cell not empty");
    }


    ticTacToeSize[r][c] = this.turn;
    if (turn == Player.O) {
      this.turn = Player.X;
    } else {
      this.turn = Player.O;
    }
  }


  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return players turn
   */
  @Override
  public Player getTurn() {
    return this.turn;
  }

  /**
   * Checks if elements at the diagonal positions in the tic tac toe game are all the same and
   * returns true if they are.
   *
   * @return boolean check
   */
  private boolean isDiagonal() {
    if (ticTacToeSize[0][0] == ticTacToeSize[1][1] && ticTacToeSize[1][1] == ticTacToeSize[2][2]
            && ticTacToeSize[0][0] != null) {
      return true;
    } else if (ticTacToeSize[0][2] == ticTacToeSize[1][1]
            && ticTacToeSize[1][1] == ticTacToeSize[2][0]
            && ticTacToeSize[2][0] != null) {
      return true;
    }
    return false;
  }

  /**
   * Checks if all the elements in any row of the tic tac toe are same.
   *
   * @return boolean check
   */
  private boolean isHorizontal() {
    for (int i = 0; i < this.ticTacToeSize.length; i++) {
      if (ticTacToeSize[i][0] == ticTacToeSize[i][1]
              && ticTacToeSize[i][0] == ticTacToeSize[i][2] && ticTacToeSize[i][0] != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if all the elements in any column of the tic tac toe are same.
   *
   * @return boolean check
   */
  private boolean isVertical() {
    for (int i = 0; i < this.ticTacToeSize.length; i++) {
      if (ticTacToeSize[0][i] == ticTacToeSize[1][i]
              && ticTacToeSize[0][i] == ticTacToeSize[2][i] && ticTacToeSize[0][i] != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if there is any space left to play in the tic tac toe board.
   *
   * @return boolean check
   */
  private boolean isTicTacToeSizeFull() {
    for (int i = 0; i < this.ticTacToeSize.length; i++) {
      for (int j = 0; j < this.ticTacToeSize.length; j++) {
        if (this.ticTacToeSize[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checks if the game is over either because of player winning or if there is no space left to
   * play.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    if (this.isTicTacToeSizeFull()) {
      return true;
    }
    return (this.isDiagonal() || this.isHorizontal() || this.isVertical());

  }

  /**
   * Check if any of the three criteria to win a game are satisfied, if they are then return the
   * player who won.
   *
   * @return winner.
   */
  @Override
  public Player getWinner() {
    if (this.isDiagonal() || this.isHorizontal() || this.isVertical()) {
      if (turn == Player.O) {
        return Player.X;
      } else {
        return Player.O;
      }
    }
    return null;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current tic tac toe board
   */
  @Override
  public Player[][] getBoard() {
    Player[][] newTicTacToe = new Player[3][3];

    for (int i = 0; i < this.ticTacToeSize.length; i++) {
      newTicTacToe[i] = this.ticTacToeSize[i].clone();
    }
    return newTicTacToe;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r < 0) {
      throw new IllegalArgumentException("Outside of ticTacToeSize");
    }

    if (c < 0) {
      throw new IllegalArgumentException("Outside of ticTacToeSize");
    }
    return this.ticTacToeSize[r][c];
  }

}

