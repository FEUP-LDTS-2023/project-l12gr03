package project.model.board;

import project.model.Position;

import java.io.*;
import java.lang.annotation.Target;
import java.nio.charset.Charset;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.*;


public class Big extends TicTacToe {

    List<Mini>  bigSquaresL = new ArrayList<>();
    boolean isLocked = false;

    public Big(Player player1, Player player2, int x, int y,List<Mini> squares) throws IOException {
        this.position = new Position(x,y);
        this.p1 = player1;
        this.p2 = player2;
        this.bigSquaresL = squares;

        ScanBoard();
        CoinToss();

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
        backgroundMusic.stop();
    }

    public void setBigGameState(){
        List<Integer> l = getPlayState();
        if (checkWinner(l, 1)) {
            gameIsOver = 1; // X ganhou!!
            endGame();
        } else if (checkWinner(l, 2)) {
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
        if(bigSquaresL.get(getSelected()).select(getPlayer())){
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
        return bigSquaresL.get(getSelected()).getMinPosition();
    }

    @Override
    public int getInnerSelected() {
        return bigSquaresL.get(getSelected()).getInnerSelected();
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
            if (checkRowsCase0(states, playerState) || checkRowsCase1(states, playerState) || checkRowsCase2(states, playerState)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRowsCase0(List<Integer> states, int playerState) {
        for (int i = 0; i < 7; i += 3) {
            if (states.get(i) == playerState && (states.get(i + 1) == playerState || states.get(i) == 3) && (states.get(i + 2) == playerState || states.get(i + 2) == 3) ) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRowsCase1(List<Integer> states, int playerState) {
        for (int i = 0; i < 7; i += 3) {
            if ((states.get(i) == playerState || states.get(i) == 3) && (states.get(i + 1) == playerState)  && (states.get(i + 2) == playerState || states.get(i + 2) == 3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRowsCase2(List<Integer> states, int playerState) {
        for (int i = 0; i < 7; i += 3) {
            if ((states.get(i) == playerState || states.get(i) == 3) && (states.get(i + 1) == playerState || states.get(i) == 3) && states.get(i + 2) == playerState) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumns(List<Integer> states, int playerState) {
        for (int i = 0; i < 3; i++) {
            if (checkColumnsCase0(states,playerState) || checkColumnsCase1(states, playerState) || checkColumnsCase2(states, playerState)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumnsCase0(List<Integer> states, int playerState) {
        for (int i = 0; i < 3; i++) {
            if ((states.get(i) == playerState) && (states.get(i + 3) == playerState || states.get(i + 3) == 3 ) && (states.get(i + 6) == playerState || states.get(i + 6) == 3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumnsCase1(List<Integer> states, int playerState) {
        for (int i = 0; i < 3; i++) {
            if ((states.get(i) == playerState || states.get(i) == 3 ) && (states.get(i + 3) == playerState) && (states.get(i + 6) == playerState || states.get(i + 6) == 3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumnsCase2(List<Integer> states, int playerState) {
        for (int i = 0; i < 3; i++) {
            if  ((states.get(i) == playerState || states.get(i) == 3 ) && (states.get(i + 3) == playerState ||states.get(i + 3) == 3 ) && (states.get(i + 6) == playerState)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonals(List<Integer> states, int playerState) {
        return (checkDiagonalsCase0(states, playerState) || checkDiagonalsCase1(states, playerState) || checkDiagonalsCase2(states,playerState));
    }


    public boolean isCountingTime(){return countingTime;}
    public static boolean checkDiagonalsCase0(List<Integer> states, int playerState) {
        return (((states.get(0) == playerState) && (states.get(4) == playerState || states.get(4) == 3) && (states.get(8) == playerState|| states.get(8) == 3)) ||
                ((states.get(2) == playerState) && (states.get(4) == playerState || states.get(4) == 3)  && (states.get(6) == playerState || states.get(6) == 3)));
    }

    public static boolean checkDiagonalsCase1(List<Integer> states, int playerState) {
        return (((states.get(0) == playerState || states.get(0) == 3) && (states.get(4) == playerState) && (states.get(8) == playerState|| states.get(8) == 3)) ||
                ((states.get(2) == playerState || states.get(2) == 3) && (states.get(4) == playerState)  && (states.get(6) == playerState || states.get(6) == 3)));
    }

    public static boolean checkDiagonalsCase2(List<Integer> states, int playerState) {
        return (((states.get(0) == playerState || states.get(0) == 3) && (states.get(4) == playerState || states.get(4) == 3) && (states.get(8) == playerState)) ||
                ((states.get(2) == playerState || states.get(2) == 3) && (states.get(4) == playerState || states.get(4) == 3)  && (states.get(6) == playerState)));
    }


}
