import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineDisplay extends Display {
    /* The Display class acts as the UI. It is responsible for displaying information and receiving input from
    a user.
     */

    @Override
    public boolean isPlayAgain() {
        /* This method returns true if the user would like to play again, and false if not.
         */

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Would you like to play again? (y/n)");
            userInput = scanner.nextLine();
        } while(!userInput.matches("[yY]|[nN]|[qQ]"));

        return userInput.matches("[yY]");
    }

    @Override
    public void displayBoard(String[][] board) {
        System.out.println();

        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[0].length; ++col) {
                String token = board[row][col];
                System.out.print(" | ");
                System.out.print(( token == null) ? " " : token);
            }
            System.out.print(" |");
            System.out.println();
        }

        System.out.print("  ");

        for (int i = 0; i < board.length - 1; ++i) {
            System.out.print(" " + i + "  ");
        }
        System.out.println();
    }

    @Override
    public void printMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    public int[] getMove(String[][] board, String player) {
        System.out.println();
        System.out.printf("It is player %s's turn\n", player);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.printf("Player %s, please enter the column you wish to play as a digit...\n", player);
            userInput = scanner.nextLine();
        } while (!isValidInput(userInput, board));

        List<Integer> numbers = getDigit(userInput);

        return new int[] { numbers.get(0) };
    }

    private List<Integer> getDigit(String input) {
//      This method parses a string and returns an ArrayList containing all of the numbers found in the string.

        List<Integer> numbers = new ArrayList<>();
        Pattern digit = Pattern.compile("\\d+");
        Matcher matcher = digit.matcher(input);

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        return numbers;
    }

    private boolean isValidInput(String userInput, String[][] board) {
//      This method returns true if a string entered by a user contains valid input for the board and false if not.

        List<Integer> numbers = getDigit(userInput);

        if (numbers.size() < 1) {
            return false;
        }

        int col = numbers.get(0);
        int row = 0;

        if (col > board[0].length - 1 || col < 0) {
            System.out.println("Oops... that number isn't on the board!");
            return false;
        }

        if (board[row][col] != null) {
            System.out.println("Oops... can't play here, find an empty spot!");
            return false;
        }

        return true;
    }
}
