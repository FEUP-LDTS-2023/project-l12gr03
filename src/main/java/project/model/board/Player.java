package project.model.board;

public class Player {
    private char symbol;
    private float score;

    //public Player(char symbol){this.symbol = symbol;}
    public Player(char symbol, int score){this.symbol=symbol; this.score=score;}

    public char getSymbol() {return symbol;}

    public float getScore() {return score;}

    public void addScore() {score++;}
    public void addScoreTie() {score += 0.5f;}
}
