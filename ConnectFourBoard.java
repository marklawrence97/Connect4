import java.util.*;

public class ConnectFourBoard extends Board {
    private int[] heightOfLowestEmptyCell;

    public ConnectFourBoard(int rows, int cols, List<Player> players) {
        this.board = new String[rows][cols];
        this.players = players;
        this.heightOfLowestEmptyCell = new int[rows];
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
    public void placeCounter(int[] move, String player) {
//          This method takes a move and a player as an input. A counter representing the player is placed on the
//        lowest available slot in the requested column.

        int col = move[0];
        int row = this.board.length - this.heightOfLowestEmptyCell[col] - 1;

        this.board[row][col] = player;
        this.heightOfLowestEmptyCell[col] += 1;
    }

    @Override
    public boolean isTerminal() {


        return false;
    }

    @Override
    public String getWinner() {
        int boardHeight = this.board.length;
        int boardWidth = this.board[0].length;

        for (int row = boardHeight - 1; row > 2; --row) {
            for (int col = 0; col < boardWidth; ++col) {
                boolean[][] visited = new boolean[boardWidth][boardHeight];
                int count = 1;
                String currentToken = this.board[row][col];
                int currentRow = row;

                if (!visited[row][col]) {
                    visited[row][col] = true;

                    while (this.board[currentRow - 1][col] == currentToken) {
                        currentRow -= 1;
                        count += 1;
                        visited[currentRow][col] = true;
                        if (count >= 4) {
                            return currentToken;
                        }
                    }
                }
            }
        }

        for (int row = boardHeight - 1; row >= 0; --row) {
            for (int col = 0; col < boardWidth - 3; ++col) {
                boolean[][] visited = new boolean[boardWidth][boardHeight];
                int count = 1;
                String currentToken = this.board[row][col];
                int currentCol = col;

                if (!visited[row][col]) {
                    visited[row][col] = true;

                    while (this.board[row][currentCol + 1] == currentToken) {
                        currentCol += 1;
                        count += 1;
                        visited[row][currentCol] = true;
                        if (count >= 4) {
                            return currentToken;
                        }
                    }
            }
        }

        for (int row = boardHeight - 1; row > 2; --row) {
            for (int col = 0; col < boardWidth; ++col) {
                boolean[][] visited = new boolean[boardWidth][boardHeight];
                if (!visited[row][col]) {
//                    Check down
                }
            }
        }

        for (int row = boardHeight - 1; row > 2; --row) {
            for (int col = 0; col < boardWidth; ++col) {
                boolean[][] visited = new boolean[boardWidth][boardHeight];
                if (!visited[row][col]) {
//                    Check down
                }
            }
        }

        return null;
    }

    @Override
    public int utility(String player) {
        return 0;
    }
}
