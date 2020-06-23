import java.util.List;
import java.util.Random;

public class RandomComputerPlayer extends Player {

    public RandomComputerPlayer(String name, int precedence) {
        this.name = name;
        this.precedence = precedence;
    }

    @Override
    int[] getMove(Display display, Board board) {
        List<int[]> actions = board.getActions();

        display.printMessage("Computer thinking...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random rand = new Random();
        return actions.get(rand.nextInt(actions.size()));
    }
}
