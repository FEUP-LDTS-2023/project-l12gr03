package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.Charset;

import java.util.*;


public class Big extends TicTacToe {


    ArrayList<Mini> bigSquares = new ArrayList<>();
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
                bigSquares.add(new Mini(player1,player2,10+18*column,8+8*row));
            }
        }
        selected = 4;


        new Thread(this::updateElapsedTime).start();

    }

    public List<Integer> getPlayState(){
        List<Integer> states = new ArrayList<>();

        for (int i = 0; i < bigSquares.size(); i++){
            states.add(bigSquares.get(i).getMiniGameState());
        }
        return states;
    }

    public boolean getBool(){
        return isPlayingMini;
    }
    @Override
    public void goUp(){
        if (bigSquares.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquares.get(selected).goUp();
        } else {
        selected = (((selected-3) % 9) + 9) % 9;}
    }
    @Override
    public void goDown(){
        if (bigSquares.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquares.get(selected).goDown();
        }else{
        selected = (selected+3) % 9;}
    }
    @Override
    public void goLeft(){
        if (bigSquares.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquares.get(selected).goLeft();
        }else{
        selected = (((selected-1) % 9) + 9) % 9;}
    }
    @Override
    public void goRight(){
        if (bigSquares.get(selected).getInnerSelected() != MINI_NOT_SELECTED){
            bigSquares.get(selected).goRight();
        }else{
        selected = (selected+1) % 9;}
    }

    @Override
    public void endGame() {}


    @Override
    public boolean select(Player player){
        if(bigSquares.get(selected).select(currentPlayer)){ switchPlayer(); return true;}
        return false;

    }


    public void ScanBoard() throws IOException {
        this.file = new File(System.getProperty("user.dir") + "/resources/initialBoard2.txt");
        Scanner myReader = new Scanner(file,  Charset.defaultCharset().name());
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.initialBoard.add(data);
        }
    }

    public Position getMinPosition()
    {
        return bigSquares.get(selected).getMinPosition();
    }

    public int getInnerSelected() {
        return bigSquares.get(selected).getInnerSelected();
    }

    public void CoinToss() {
        Random random = new Random();
        int resultado = random.nextInt(2); // Gera 0 ou 1

        if (resultado == 0) {currentPlayer = p1;}
        else {currentPlayer = p2;}
    }

}
