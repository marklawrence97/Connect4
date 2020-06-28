import java.util.*;

public abstract class Board {
    protected String[][] board;
    protected List<Player> players;

    public String[][] getBoardValue() {
        String[][] boardValue = new String[this.board.length][this.board[0].length];
        for (int row = 0; row < this.board.length; ++row) {
            System.arraycopy(this.board[row], 0, boardValue[row], 0, this.board[0].length);
        }
        return boardValue;
    }

    public abstract List<int[]> getActions();

    public abstract void placeCounter(int[] move);

    public abstract boolean isTerminal();

    public abstract String getWinner();

    public Player getTurn() {
/*      This generic function counts the number of player tokens on a 2D board and returns the String value of the
 *      player whose turn is it next based on the order of players to act and the number of tokens that are on the board.
 */
        Map<String, Integer> playerTokenCounter = new HashMap<>();
        for (Player player: this.players) {
            playerTokenCounter.put(player.getName(), 0);
        }

        for (String[] rows : this.board) {
            for (int col  = 0; col < this.board[0].length; ++col) {
                String token = rows[col];
                if (token == null) {
                    continue;
                }

                int count = playerTokenCounter.get(token);
                playerTokenCounter.put(token, count + 1);
            }
        }

        players.sort(Comparator.comparingInt(Player::getPrecedence));

        for (int i = 0; i < players.size() - 1; ++i) {
            String player = players.get(i).getName();
            String lowerPrecedencePlayer = players.get(i + 1).getName();
            if (playerTokenCounter.get(player) > playerTokenCounter.get(lowerPrecedencePlayer)) {
                return players.get(i + 1);
            }
        }

        return players.get(0);
    }
}
