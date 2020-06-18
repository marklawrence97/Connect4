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
        String[][] newBoard = new String[7][5];
        board = new ConnectFourBoard(newBoard, players);
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
    @DisplayName("Test correct actions length when column full")
    public void TestCorrectActionsFullColumn() {
        for (int i = 0; i < 7; ++i) {
            board.placeCounter(new int[] { 3 });
        }
        List<int[]> actions = board.getActions();
        assertEquals(4, actions.size());
    }

    @Test
    @DisplayName("Test place first three counters in same column")
    public void TestFirstFourMoves() {
        board.placeCounter(new int[] { 3 });
        assertEquals(player1.getName(), board.getBoard()[6][3]);
        board.placeCounter(new int[] { 3 });
        assertEquals(player2.getName(), board.getBoard()[5][3]);
        board.placeCounter(new int[] { 3 });
        assertEquals(player3.getName(), board.getBoard()[4][3]);
        board.placeCounter(new int[] { 3 });
        assertEquals(player1.getName(), board.getBoard()[3][3]);
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetTurnEmptyBoard() {
        assertEquals(player1.getName(), board.getTurn().getName());
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetSecondMove() {
        board.placeCounter(new int[] { 3 });
        assertEquals(player2.getName(), board.getTurn().getName());
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetThirdMove() {
        board.placeCounter(new int[] { 3 });
        board.placeCounter(new int[] { 3 });
        assertEquals(player3.getName(), board.getTurn().getName());
    }

    @Test
    @DisplayName("Test get turn on empty board")
    public void TestGetFourthMove() {
        board.placeCounter(new int[] { 3 });
        board.placeCounter(new int[] { 3 });
        board.placeCounter(new int[] { 3 });
        assertEquals(player1.getName(), board.getTurn().getName());
    }

    @Test
    @DisplayName("Test Vertical Win Final Column")
    public void TestVerticalWinFinalColumn() {
        String[][] newBoard = new String[][] {
            {null, null, null, null, null},
            {null, null, null, null, player1.getName()},
            {null, null, null, null, player1.getName()},
            {null, null, null, null, player1.getName()},
            {null, null, null, null, player1.getName()}
        };

        Board verticalWin = new ConnectFourBoard(newBoard, players);
        assertEquals(player1.getName(), verticalWin.getWinner());
    }

    @Test
    @DisplayName("Test No Win Final Column")
    public void TestNoWinWinFinalColumn() {
        String[][] newBoard = new String[][] {
                {null, null, null, null, null},
                {null, null, null, null, player1.getName()},
                {null, null, null, null, player2.getName()},
                {null, null, null, null, player1.getName()},
                {null, null, null, null, player1.getName()}
        };

        Board verticalNoWin = new ConnectFourBoard(newBoard, players);
        assertEquals(null, verticalNoWin.getWinner());
    }

    @Test
    @DisplayName("Test Vertical Win First Column")
    public void TestVerticalWinFirstColumn() {
        String[][] newBoard = new String[][] {
                {null, null, null, null, null},
                {player2.getName(), null, null, null, player1.getName()},
                {player2.getName(), null, null, null, player2.getName()},
                {player2.getName(), null, null, null, player1.getName()},
                {player2.getName(), null, null, null, player1.getName()}
        };

        Board verticalWin = new ConnectFourBoard(newBoard, players);
        assertEquals(player2.getName(), verticalWin.getWinner());
    }

    @Test
    @DisplayName("Test Horizontal Win First Row")
    public void TestHorizontalWinFirstRow() {
        String[][] newBoard = new String[][] {
                {null, null, null, null, null},
                {player2.getName(), null, null, null, player1.getName()},
                {player2.getName(), null, null, null, player2.getName()},
                {player2.getName(), null, null, null, player1.getName()},
                {player1.getName(), player1.getName(), player1.getName(), player1.getName(), player1.getName()}
        };
        Board horizontalWin = new ConnectFourBoard(newBoard, players);
        assertEquals(player1.getName(), horizontalWin.getWinner());
    }

    @Test
    @DisplayName("Test Right Diagonal Win")
    public void TestRightDiagonal() {
        String[][] newBoard = new String[][]{
                {null, null, null, null, null},
                {player3.getName(), null, null, null, player1.getName()},
                {player2.getName(), player3.getName(), null, null, player2.getName()},
                {player2.getName(), null, player3.getName(), null, player1.getName()},
                {player1.getName(), player1.getName(), player1.getName(), player3.getName(), player1.getName()}
        };

        Board rightDiagonalWin = new ConnectFourBoard(newBoard, players);
        assertEquals(player3.getName(), rightDiagonalWin.getWinner());
    }

    @Test
    @DisplayName("Test Left Diagonal Win")
    public void TestLeftDiagonal() {
        String[][] newBoard = new String[][]{
                {null, null, null, null, null},
                {player3.getName(), null, null, null, player1.getName()},
                {player2.getName(), player3.getName(), null, player1.getName(), player2.getName()},
                {player2.getName(), null, player1.getName(), null, player1.getName()},
                {player1.getName(), player1.getName(), player1.getName(), player3.getName(), player1.getName()}
        };

        Board leftDiagonalWin = new ConnectFourBoard(newBoard, players);
        assertEquals(player1.getName(), leftDiagonalWin.getWinner());
    }

    @Test
    @DisplayName("Test is terminal when game is not over")
    public void TestIsTerminalNotOver() {
        String[][] newBoard = new String[][]{
                {null, null, null, null, null},
                {player3.getName(), null, null, null, player1.getName()},
                {player2.getName(), player3.getName(), null, player1.getName(), player2.getName()},
                {player2.getName(), null, player1.getName(), null, player1.getName()},
                {player1.getName(), player2.getName(), player1.getName(), player3.getName(), player1.getName()}
        };
        Board gameNotOver = new ConnectFourBoard(newBoard, players);
        assertFalse(gameNotOver.isTerminal());
    }

    @Test
    @DisplayName("Test is terminal when game is over with a winner")
    public void TestIsTerminalOver() {
        String[][] newBoard = new String[][]{
                {null, null, null, null, null},
                {player2.getName(), null, null, null, player1.getName()},
                {player2.getName(), player3.getName(), null, player1.getName(), player2.getName()},
                {player2.getName(), null, player1.getName(), null, player1.getName()},
                {player2.getName(), player2.getName(), player1.getName(), player3.getName(), player1.getName()}
        };

        Board gameOverWithWin = new ConnectFourBoard(newBoard, players);
        assertTrue(gameOverWithWin.isTerminal());
    }

    @Test
    @DisplayName("Test is terminal when game is over no winner")
    public void TestIsTerminalOverNoWinner() {
        String[][] newBoard = new String[][] {
                {player2.getName(), player3.getName(), player1.getName()},
                {player2.getName(), player3.getName(), player1.getName()},
                {player2.getName(), player3.getName(), player1.getName()}
        };
        Board smallBoard = new ConnectFourBoard(newBoard, players);

        assertTrue(smallBoard.isTerminal());
    }

}