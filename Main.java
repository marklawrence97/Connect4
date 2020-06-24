public class Main {
    public static void main(String[] args) {
        Display display = new CommandLineDisplay();
        int N;

        try {
            N = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You must enter N as a command line argument");
            return;
        } catch (NumberFormatException e) {
            System.out.println("N must be an integer");
            return;
        }

        if (N < 3 || N > 6) {
            System.out.println("N must be in the interval (2, 7) / [3, 6]");
            return;
        }

        do {
        ConnectNGame connectNGame = new ConnectNGame(N, display);
        connectNGame.playGame();
        } while (display.isPlayAgain());
    }
}
