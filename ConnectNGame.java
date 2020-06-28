import java.util.List;

public class ConnectNGame implements Game {
    Display display;
    Board board;

    public ConnectNGame(int N, Display display) {
        if (N < 3 || N > 6) {
            display.printMessage("N must be in the range 2 < N < 7!");
            return;
        }

        List<Player> players = display.getPlayers();
        this.board = new ConnectNBoard(new String[7][6], players, N);
        this.display = display;
    }

    public void playGame() {
        this.display.displayBoard(this.board.getBoardValue());
        do {
            Player player = this.board.getTurn();
            this.board.placeCounter(player.getMove(this.display, this.board));
            this.display.displayBoard(this.board.getBoardValue());
        } while ((!this.board.isTerminal()));
        display.printMessage(String.format("The winner of the game was %s", board.getWinner()));
    }
}
