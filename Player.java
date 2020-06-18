public abstract class Player {
    public String name;
    public int precedence;

    public String getName() {
        return this.name;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    abstract int[] getMove(Display display, Board board);
}
