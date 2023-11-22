public class PlayerRegistration {

    private String message = "Player 1, pick crosses or circles[X/O]: ";
    private char symbol = ' ';

    private boolean isSymbolChosen = false;

    public PlayerRegistration() {
    }

    public String getMessage() {
        return (message + symbol);
    }

    public void assignX() {
        symbol = 'X';
        isSymbolChosen = true;
    }

    public void assignO() {
        symbol = 'O';
        isSymbolChosen = true;
    }

    public boolean symbolChosen() {return isSymbolChosen;}
    public void registrate() {}

}
