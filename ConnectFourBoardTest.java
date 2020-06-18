import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectFourBoardTest {
    Board board;
    Player player3 = new HumanPlayer("O", 3);
    Player player1 = new HumanPlayer("M", 1);
    Player player2 = new HumanPlayer("L", 2);
    List<Player> players = new ArrayList<>();


    @Before
    public void setUpBoard() {
        players.add(player1);
        players.add(player2);
        players.add(player3);
        board = new ConnectFourBoard(7, 5, players);
    }

    @Test
    @DisplayName("Test get actions when nothing has been played")
    public void TestFirstActions() {
        List<int[]> actions = board.getActions();
        assertEquals(5, actions.size());
        for (int i = 0; i < 5; ++i) {
            assertEquals(i, actions.get(i)[0]);
        }
    }

    @Test
    @DisplayName("Test correct actions when column full")
    public void TestCorrectActionsFullColumn() {
        for (int i = 0; i < 7; ++i) {
            board.placeCounter(new int[] { 3 }, player1.getName());
            assertEquals(player1.getName(), board.getBoard()[6 - i][3]);
        }
        List<int[]> actions = board.getActions();
        assertEquals(4, actions.size());
    }

    @Test
    @DisplayName("Test place first three counters in same column")
    public void TestFirstMove() {
        board.placeCounter(new int[] { 3 }, player1.getName());
        assertEquals(player1.getName(), board.getBoard()[6][3]);
        board.placeCounter(new int[] { 3 }, player2.getName());
        assertEquals(player2.getName(), board.getBoard()[5][3]);
        board.placeCounter(new int[] { 3 }, player1.getName());
        assertEquals(player1.getName(), board.getBoard()[4][3]);
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetTurnEmptyBoard() {
        assertEquals("M", board.getTurn());
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetSecondMove() {
        board.placeCounter(new int[] { 3 }, player1.getName());
        assertEquals(player2.getName(), board.getTurn());
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetThirdMove() {
        board.placeCounter(new int[] { 3 }, player1.getName());
        board.placeCounter(new int[] { 3 }, player2.getName());
        assertEquals(player3.getName(), board.getTurn());
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetFourthMove() {
        board.placeCounter(new int[] { 3 }, player1.getName());
        board.placeCounter(new int[] { 3 }, player2.getName());
        board.placeCounter(new int[] { 3 }, player3.getName());
        assertEquals(player1.getName(), board.getTurn());
    }
}