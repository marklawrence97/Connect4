import java.util.*;

public class ConnectNBoard extends Board {
    private final int[] heightOfLowestEmptyCell;
    private final int N;

    public ConnectNBoard(String[][] board, List<Player> players, int N) {
        this.board = board;
        this.players = players;
        this.N = N;
        this.heightOfLowestEmptyCell = new int[board.length];
        for (int col = 0; col < board[0].length; ++col) {
            for (int row = this.board.length - 1; row >= 0; --row) {
                if (board[row][col] == null) {
                    this.heightOfLowestEmptyCell[col] = this.board.length - row - 1;
                    break;
                }
            }
        }
    }

    @Override
    public List<int[]> getActions() {
//      This method returns all the possible actions available to a player based on the current state of the board.

        List<int[]> possibleActions = new ArrayList<>();
        int height = this.board.length;

        for (int col = 0; col < this.board[0].length; ++col) {
            if (this.heightOfLowestEmptyCell[col] < height) {
                possibleActions.add(new int[] {col});
            }
        }

        return possibleActions;
    }

    @Override
    public void placeCounter(int[] move) {
//          This method takes a move and a player as an input. A counter representing the player is placed on the
//        lowest available slot in the requested column.

        int col = move[0];
        int row = this.board.length - this.heightOfLowestEmptyCell[col] - 1;

        this.board[row][col] = this.getTurn().getName();
        this.heightOfLowestEmptyCell[col] += 1;
    }

    @Override
    public boolean isTerminal() {
        /* This method returns True if the game is finished, and False if the game is not finished. */

        if (getWinner() != null) {
            return true;
        }

        for (String[] row: this.board) {
            for (int col = 0; col < row.length; ++col) {
                if (row[col] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String getWinner() {
        int boardHeight = this.board.length;
        int boardWidth = this.board[0].length;
        int N = this.N;
        boolean[][] visited = new boolean[boardHeight][boardWidth];

//      Check Vertical
        for (int row = 0; row < boardHeight - N + 1; ++row) {
            for (int col = 0; col < boardWidth; ++col) {

                int count = 1;
                String currentToken = this.board[row][col];
                int currentRow = row;

                if (!visited[row][col] && currentToken != null) {
                    visited[row][col] = true;

                    while (Objects.equals(this.board[currentRow + 1][col], currentToken)) {
                        currentRow += 1;
                        count += 1;
                        visited[currentRow][col] = true;
                        if (count >= N) {
                            return currentToken;
                        }
                    }
                }
                visited[row][col] = true;
            }
        }

//      Check Horizontal
        visited = new boolean[boardHeight][boardWidth];
        for (int row = 0; row < boardHeight; ++row) {
            for (int col = 0; col < boardWidth - N + 1; ++col) {

                int count = 1;
                String currentToken = this.board[row][col];
                int currentCol = col;

                if (!visited[row][col] && currentToken != null) {
                    visited[row][col] = true;

                    while (Objects.equals(this.board[row][currentCol + 1], currentToken)) {
                        currentCol += 1;
                        count += 1;
                        visited[row][currentCol] = true;
                        if (count >= N) {
                            return currentToken;
                        }
                    }
                }

                visited[row][col] = true;
            }
        }

//      Check right diagonal
        visited = new boolean[boardHeight][boardWidth];
        for (int row = 0; row < boardHeight - N + 1; ++row) {
            for (int col = 0; col < boardWidth - N + 1; ++col) {
                int count = 1;
                String currentToken = this.board[row][col];
                int currentCol = col;
                int currentRow = row;

                if (!visited[row][col] && currentToken != null) {
                    while (Objects.equals(this.board[currentRow + 1][currentCol + 1], currentToken)) {
                        currentCol += 1;
                        currentRow += 1;
                        count += 1;
                        visited[currentRow][currentCol] = true;
                        if (count >= N) {
                            return currentToken;
                        }
                    }
                }
            }
        }

//      Check left diagonal
        visited = new boolean[boardHeight][boardWidth];
        for (int row = 0; row < boardHeight - N + 1; ++row) {
            for (int col = 3; col < boardWidth; ++col) {
                int count = 1;
                String currentToken = this.board[row][col];
                int currentCol = col;
                int currentRow = row;

                if (!visited[row][col] && currentToken != null) {
                    while (Objects.equals(this.board[currentRow + 1][currentCol - 1], currentToken)) {
                        currentCol -= 1;
                        currentRow += 1;
                        count += 1;
                        visited[currentRow][currentCol] = true;
                        if (count >= N) {
                            return currentToken;
                        }
                    }
                }
            }
        }

        return null;
    }
}
