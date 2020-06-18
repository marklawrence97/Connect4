import java.util.*;

public abstract class Board {
    String[][] board;
    List<Player> players;

    String[][] getBoard() {
        return board;
    }

    public abstract List<int[]> getActions();

    public abstract void placeCounter(int[] move, String player);

    public abstract boolean isTerminal();

    public abstract String getWinner();

    public abstract int utility(String player);

    public String getTurn() {
/*      This generic function counts the number of player tokens on a 2D board and returns the String value of the
 *      player whose turn is it next.
 */
        Map<String, Integer> playerTokenCounter = new HashMap<>();
        for (Player player: this.players) {
            playerTokenCounter.put(player.getName(), 0);
        }

        for (int i = 0; i < this.board.length; ++i) {
            for (int j = 0; j < this.board[0].length; ++j) {
                String token = this.board[i][j];
                if (token == null) {
                    continue;
                }

                int count = playerTokenCounter.get(token);
                playerTokenCounter.put(token, count + 1);
            }
        }

        Collections.sort(players, Comparator.comparingInt(Player::getPrecedence));

        for (int i = 0; i < players.size() - 1; ++i) {
            String player = players.get(i).getName();
            String lowerPrecedencePlayer = players.get(i + 1).getName();
            if (playerTokenCounter.get(player) > playerTokenCounter.get(lowerPrecedencePlayer)) {
                return lowerPrecedencePlayer;
            }
        }

        return players.get(0).getName();
    }
}
