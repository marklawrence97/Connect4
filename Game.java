import java.util.ArrayList;
import java.util.List;

public class Game {

    public static void main(String[] args) {
        Display display = new Display();
        Player player1 = new HumanPlayer("R", 1);
        Player player2 = new ComputerPlayer("Y", 2);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        String[][] newBoard = new String[8][8];
        Board board = new ConnectFourBoard(newBoard, players);
        display.displayBoard(board.getBoard());
        do {
            Player player = board.getTurn();
            board.placeCounter(player.getMove(display, board));
            display.displayBoard(board.getBoard());
        } while (!board.isTerminal());
    }
}
