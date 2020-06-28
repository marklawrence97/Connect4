import java.util.List;

public interface Display {

    void displayBoard(String[][] board);

    void printMessage(String message);

    int[] getMove(String[][] board, String player);

    List<Player> getPlayers();

    boolean isPlayAgain();
}
