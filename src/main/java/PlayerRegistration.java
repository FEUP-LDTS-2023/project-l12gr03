public class PlayerRegistration {

    private String message="Player 1, pick crosses or circles[X/O]: ";
    private char symbol = ' ';

    private boolean isAssigned;
    public PlayerRegistration(){}

    public String getMessage() {return (message + symbol);}

    public void assignX(){symbol='X'; isAssigned=true;}

    public void assignO(){symbol='O'; isAssigned=true;}
}
