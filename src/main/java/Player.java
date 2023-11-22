public class Player {
    private char symbol;
    private int score = 0;

    public Player(char symbol){this.symbol = symbol;}
    public Player(char symbol, int score){this.symbol=symbol; this.score=score;}

    public char getSymbol() {return symbol;}

    public int getScore() {return score;}

    public void addScore() {score++;}
}
