import java.util.ArrayList;
import java.util.List;

public class ConnectNGame {
    Display display;
    Board board;

    public ConnectNGame(int N, Display display) {
        if (N < 3 || N > 6) {
            display.printMessage("N must be in the range 2 < N < 7!");
            return;
        }

//      TODO logic to get number of players from display

        Player human = new HumanPlayer("R", 1);
        Player computer1 = new RandomComputerPlayer("Y", 2);
        Player computer2 = new RandomComputerPlayer("B", 3);

        List<Player> players = new ArrayList<>();
        players.add(human);
        players.add(computer1);
        players.add(computer2);
        Board board = new ConnectNBoard(new String[7][6], players, N);
        this.board = board;
        this.display = display;
    }

    public void playGame() {
        while (!this.board.isTerminal()) {
            Player player = this.board.getTurn();
            this.board.placeCounter(player.getMove(this.display, this.board));
            this.display.displayBoard(this.board.getBoardValue());
        }
        display.printMessage(String.format("The winner of the game was %s", board.getWinner()));
    }
}
