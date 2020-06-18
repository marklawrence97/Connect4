public class HumanPlayer extends Player {

    public HumanPlayer(String name, int precedence) {
        this.name = name;
        this.precedence = precedence;
    }

    public int[] getMove(Display display, Board board) {
        return display.getMove(board.getBoard(), this.name);
    }
}
