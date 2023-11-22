public class Player {
    private char symbol;
    private int score = 0;

    public Player(char symbol){this.symbol = symbol;}

    public char getSymbol() {return symbol;}

    public int getScore() {return score;}

    public void won() {score++;}
}
