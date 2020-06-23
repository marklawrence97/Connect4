import java.util.*;

public class EasyComputerPlayer extends Player {

    public EasyComputerPlayer(String name, int precedence) {
        this.name = name;
        this.precedence = precedence;
    }

    @Override
    int[] getMove(Display display, Board board) {
//      This Computer looks ahead to see if move will win the game for the player

        List<int[]> actions = board.getActions();
        Map<int[], Integer> moveScores = new HashMap<>();
        for (int[] move: actions) {
            moveScores.put(move, 0);
            Board connectFourBoard = new ConnectNBoard(board.getBoardValue(), board.getPlayers(), 4);
            connectFourBoard.placeCounter(move);
            if (connectFourBoard.utility(this.name) == 1) {
                return move;
            }
        }
        Random rand = new Random();
        return actions.get(rand.nextInt(actions.size()));
    }
}
