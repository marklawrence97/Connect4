import java.util.ArrayList;
import java.util.List;

public class Game {

    public static void main(String[] args) {
        Display display = new CommandLineDisplay();
        Player player1 = new HumanPlayer("R", 1);
        Player player2 = new EasyComputerPlayer("Y", 2);
        Player player3 = new RandomComputerPlayer("B", 3);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        String[][] newBoard = new String[8][8];
        Board board = new ConnectNBoard(newBoard, players, 4);
        display.displayBoard(board.getBoardValue());
        do {
            Player player = board.getTurn();
            board.placeCounter(player.getMove(display, board));
            display.displayBoard(board.getBoardValue());
        } while (!board.isTerminal());
        display.printMessage(String.format("The winner of the game was %s", board.getWinner()));
    }
}
