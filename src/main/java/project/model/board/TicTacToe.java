package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class TicTacToe {
    protected String totalTime;
    protected String formattedElapsedTime;
    protected Player currentPlayer;
    protected Player p1;
    protected Player p2;
    private Position position;
    protected int selected = 4;

    //ArrayList<Mini> bigSquares;

    public int getSelected() {
        return selected;
    }

    /**
    public ArrayList<Mini> getBigSquares(){
        return bigSquares;
    }**/

    public TicTacToe(int x, int y){this.position=new Position(x,y);}
    public abstract List<Integer> getPlayState();

    public abstract void goUp();
    public abstract void goDown();
    public abstract void goLeft();
    public abstract void goRight();



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

    protected File file;
    protected List<String> initialBoard = new ArrayList<>();


    public String getLine(int i) {
        return initialBoard.get(i);
    }

    public int getNumberLines() {
        return this.initialBoard.size();
    }

    /**private void writeTotalTimeToFile(String time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(System.getProperty("user.dir") + "/total_time.txt", true))) {
            writer.println(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }**/// Unused

    public String findMinTimeFromFile() {
        try (Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/total_time.txt"), StandardCharsets.UTF_8)) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int compareTimes(String time1, String time2) {
        LocalTime localTime1 = LocalTime.parse(time1);
        LocalTime localTime2 = LocalTime.parse(time2);

        return localTime1.compareTo(localTime2);
    }

    public String getFormattedElapsedTime() {
        return formattedElapsedTime;
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
                Thread.currentThread().interrupt(); // Restore interrupted status
                logError("Thread interrupted while sleeping", e);
            }
        }
    }


    private void logError(String message, Throwable throwable) {
        System.err.println(message);
        throwable.printStackTrace();
    }


    public abstract void endGame();


    public abstract void select(Position position);

}
