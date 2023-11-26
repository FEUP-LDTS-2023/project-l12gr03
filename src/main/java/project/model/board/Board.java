package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board extends Position {

    private String totalTime;
    private String formattedElapsedTime;
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

        new Thread(this::updateElapsedTime).start();
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

    public void updateElapsedTime() {
        long startTime = System.currentTimeMillis();

        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds

            long hours = elapsedTime / 3600;
            long minutes = (elapsedTime % 3600) / 60;
            long seconds = elapsedTime % 60;

            formattedElapsedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            try {
                // Wait for 1 second before updating again
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFormattedElapsedTime() {
        return formattedElapsedTime;
    }

    public void endGame() {
        totalTime = formattedElapsedTime;
        writeTotalTimeToFile(totalTime);
    }

    private void writeTotalTimeToFile(String time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(System.getProperty("user.dir") + "/total_time.txt", true))) {
            writer.println(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findMinTimeFromFile() {
        try (Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/best_time.txt"))) {
            String minTime = null;

            while (scanner.hasNext()) {
                String currentTime = scanner.next();

                if (minTime == null || compareTimes(currentTime, minTime) < 0) {
                    minTime = currentTime;
                }
            }

            return minTime;
        } catch (FileNotFoundException e) {
            return "00:00:00";
        }
    }

    private int compareTimes(String time1, String time2) {
        LocalTime localTime1 = LocalTime.parse(time1);
        LocalTime localTime2 = LocalTime.parse(time2);

        return localTime1.compareTo(localTime2);
    }


    public abstract static class TickTockToe extends Board{

        public TickTockToe(Player player1, Player player2, int x, int y) throws IOException {
            super(player1, player2, x, y);
        }

        public void move(Position position) {}

        public void select(Position position) {}
    }

}
