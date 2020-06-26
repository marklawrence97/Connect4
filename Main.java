public class Main {
    public static void main(String[] args) {
        Display display = new CommandLineDisplay();
        int N;

        try {
            N = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            display.printMessage("You must enter N as a command line argument");
            return;
        } catch (NumberFormatException e) {
            display.printMessage("N must be an integer");
            return;
        }

        if (N < 3 || N > 6) {
            display.printMessage("N must be in the interval (2, 7) / [3, 6]");
            return;
        }

        display.printMessage(String.format("Welcome to connect %d", N));
        do {
        ConnectNGame connectNGame = new ConnectNGame(N, display);
        connectNGame.playGame();
        } while (display.isPlayAgain());
    }
}
