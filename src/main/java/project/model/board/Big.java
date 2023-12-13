package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.Charset;

import java.util.*;


public class Big extends TicTacToe {


    ArrayList<Mini>  bigSquaresL= new ArrayList<>();
    //boolean isPlayingMini = false;
    boolean isLocked = false;

    public Big(Player player1, Player player2, int x, int y) throws IOException {
        super(x, y);
        this.p1 = player1;
        this.p2 = player2;
        ScanBoard();
        CoinToss();
        for (int row=0;row<3;row++)
        {
            for (int column=0; column<3; column++)
            {
                bigSquaresL.add(new Mini(player1,player2,10+18*column,8+8*row));
            }
        }
        selected = 4;
        setInialGame();
        new Thread(this::updateElapsedTime).start();

    }

    @Override
    public List<Integer> getPlayState(){
        List<Integer> states = new ArrayList<>();

        for (int i = 0; i < bigSquaresL.size(); i++){
            states.add(bigSquaresL.get(i).getMiniGameState());
        }
        return states;
    }

    @Override
    public void goUp(){

        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goUp();
        }else if(!isLocked){
        selected = (((selected-3) % 9) + 9) % 9;}
    }
    @Override
    public void goDown(){
        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goDown();
        }else if(!isLocked){
        selected = (selected+3) % 9;}
    }
    @Override
    public void goLeft(){
        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goLeft();
        }else if(!isLocked){
        selected = (((selected-1) % 9) + 9) % 9;}
    }
    @Override
    public void goRight(){
        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goRight();
        }else if(!isLocked){
            selected = (selected+1) % 9;}
    }

    @Override
    public void endGame() {
        writeTotalTimeToFile(getFormattedElapsedTime());
        countingTime = false;
    }

    public void setBigGameState(){
        if (checkWinner(getPlayState(), 1)) {
            gameIsOver = 1; // X ganhou!!
            endGame();
        } else if (checkWinner(getPlayState(), 2)) {
            gameIsOver = 2; // O ganhou.. que azar!
            endGame();
        } else if (isBigGameTie()){
            gameIsOver = 3; // ora bolas empatou
            endGame();
        }
    }

    @Override
    public List<Character> getContents()
    {
        List<Character> res = new ArrayList<Character>();
        for (Mini mini : bigSquaresL)
        {
            res.addAll(mini.getContents());
        }
        return res;
    }

    @Override
    public boolean select(Player player){
        if(bigSquaresL.get(selected).select(currentPlayer)){
            selected = nextgame;
            switchPlayer();
            setGameState();
            isLocked = (bigSquaresL.get(selected).getMiniGameState() == 0);
            return true;
        }
        return false;

    }


    public void ScanBoard() throws IOException {
        this.file = new File(System.getProperty("user.dir") + "/resources/initialBoard2.txt");
        Scanner myReader = new Scanner(file, Charset.defaultCharset());
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.initialBoard.add(data);
        }
    }

    @Override
    public Position getMinPosition()
    {
        return bigSquaresL.get(selected).getMinPosition();
    }

    @Override
    public int getInnerSelected() {
        return bigSquaresL.get(selected).getInnerSelected();
    }

    @Override
    public void setGameState() {
        for (Mini mini : bigSquaresL) {
            mini.setGameState();
        }

        setBigGameState();
    }

    public void CoinToss() {
        Random random = new Random();
        int resultado = random.nextInt(2); // Gera 0 ou 1

        if (resultado == 0) {currentPlayer = p1;}
        else {currentPlayer = p2;}
    }


    protected boolean isBigGameTie() {
        for (int element : getPlayState()){
            if (element == 0){ // ainda tem algum jogo a acontecer
                return false;
            }
        }
        return true;
    }

    public static boolean checkWinner(List<Integer> states, int playerState) {
        return (checkRows(states, playerState) || checkColumns(states, playerState) || checkDiagonals(states, playerState));
    }

    public static boolean checkRows(List<Integer> states, int playerState) {
        for (int i = 0; i < 7; i += 3) {
            if (((states.get(i) == playerState) || (states.get(i) == 3)) && ((states.get(i + 1) == playerState) || (states.get(i) == 3)) && ((states.get(i + 2) == playerState) || (states.get(i + 2) == 3)))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumns(List<Integer> states, int playerState) {
        for (int i = 0; i < 3; i++) {
            if ((states.get(i) == playerState || states.get(i) == 3 ) && (states.get(i + 3) == playerState ||states.get(i + 3) == 3 ) && (states.get(i + 6) == playerState || states.get(i + 6) == 3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonals(List<Integer> states, int playerState) {
        return (((states.get(0) == playerState || states.get(0) == 3) && (states.get(4) == playerState || states.get(4) == 3) && (states.get(8) == playerState|| states.get(8) == 3)) ||
                ((states.get(2) == playerState || states.get(2) == 3) && (states.get(4) == playerState || states.get(4) == 3)  && (states.get(6) == playerState || states.get(6) == 3)));
    }

}
