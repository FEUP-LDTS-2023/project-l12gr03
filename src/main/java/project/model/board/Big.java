package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class Big extends TicTacToe {


    ArrayList<Mini> bigSquares;

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
                bigSquares = new ArrayList<>(Collections.nCopies(9, new Mini(p1, p2, getPosition().getX()+column, getPosition().getY()+row)));
            }
        }


        new Thread(this::updateElapsedTime).start();

    }

    public void goUp(){this.selected = (((selected-3) % 9) + 9) % 9;}
    public void goDown(){selected = (selected+3) % 9;}
    public void goLeft(){selected = (((selected-1) % 9) + 9) % 9;}
    public void goRight(){selected = (selected+1) % 9;}

    @Override
    public void endGame() {}


    @Override
    public void select(Position position){}



    public void ScanBoard() throws IOException {
        this.file = new File(System.getProperty("user.dir") + "/resources/initialBoard.txt");
        Scanner myReader = new Scanner(file,  Charset.defaultCharset().name());
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.initialBoard.add(data);
        }
    }

    public void CoinToss() {
        Random random = new Random();
        int resultado = random.nextInt(2); // Gera 0 ou 1

        if (resultado == 0) {currentPlayer = p1;}
        else {currentPlayer = p2;}
    }

}
