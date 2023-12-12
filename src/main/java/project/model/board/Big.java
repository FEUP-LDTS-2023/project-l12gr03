package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.Charset;

import java.util.*;


public class Big extends TicTacToe {


    ArrayList<Mini>  bigSquaresL= new ArrayList<>();
    boolean isPlayingMini = false;

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
        } else {
        selected = (((selected-3) % 9) + 9) % 9;}
    }
    @Override
    public void goDown(){
        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goDown();
        }else{
        selected = (selected+3) % 9;}
    }
    @Override
    public void goLeft(){
        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goLeft();
        }else{
        selected = (((selected-1) % 9) + 9) % 9;}
    }
    @Override
    public void goRight(){
        if (bigSquaresL.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquaresL.get(selected).goRight();
        }else{
        selected = (selected+1) % 9;}
    }

    @Override
    public void endGame() {}

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
    public void setMiniGameState() {
        for (Mini mini : bigSquaresL) {
            mini.setMiniGameState();
        }
    }

    public void CoinToss() {
        Random random = new Random();
        int resultado = random.nextInt(2); // Gera 0 ou 1

        if (resultado == 0) {currentPlayer = p1;}
        else {currentPlayer = p2;}
    }

}
