public class PlayerRegistrator {

    private String message = "Player 1, pick crosses or circles[X/O]: ";

    private String playersSymbols = "  ";

    private boolean isSymbolChosen = false;
    enum SymbolOptions {BallCross, CrossBall}

    public PlayerRegistrator() {}

    public String getMessage() {
        return (message + playersSymbols.charAt(0));
    }

    public void assignX() {
        playersSymbols = "XO";
        isSymbolChosen = true;
    }

    public void assignO() {
        playersSymbols = "OX";
        isSymbolChosen = true;
    }

    public boolean symbolChosen() {return isSymbolChosen;}

    public char getPlayerSymbol(int playerNumber) {return playersSymbols.charAt(playerNumber-1);}

}
