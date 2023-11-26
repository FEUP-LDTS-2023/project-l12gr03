package project.model.board;

import project.model.Position;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board extends Position {

    private Position position;
    private Player currentPlayer;
    private Player p1;
    private Player p2;

    public Board(Player player1, Player player2, int x, int y) throws IOException {
        super(x, y);
        this.p1 = player1;
        this.p2 = player2;
        ScanBoard();
        CoinToss();
    }

    public void CoinToss() {
        Random random = new Random();
        int resultado = random.nextInt(2); // Gera 0 ou 1

        if (resultado == 0) {currentPlayer = p1;}
        else {currentPlayer = p2;}
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == p1) ? p2 : p1;
    }

    public Player getPlayer(){
        return currentPlayer;
    }

    public Player getp1(){
        return p1;
    }

    public Player getp2(){
        return p2;
    }

    public void setPosition(Position pos){
        this.position = pos;
    }
    public Position getPosition() {
        return position;
    }

    private File file;
    private List<String> initialBoard = new ArrayList<>();
    public void ScanBoard() throws IOException {
        this.file = new File(System.getProperty("user.dir") + "/resources/initialBoard.txt");
        Scanner myReader = new Scanner(file,  Charset.defaultCharset().name());
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.initialBoard.add(data);
        }
    }

    public String getLine(int i) {
        return initialBoard.get(i);
    }

    public int getNumberLines() {
        return this.initialBoard.size();
    }


    public abstract static class TickTockToe extends Board{

        public TickTockToe(Player player1, Player player2, int x, int y) throws IOException {
            super(player1, player2, x, y);
        }

        public void move(Position position) {}

        public void select(Position position) {}
    }

}
