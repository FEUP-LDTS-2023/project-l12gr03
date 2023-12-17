package project.model.board;

import project.model.Position;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public abstract class TicTacToe {

    static final int MINI_NOT_SELECTED = -1;
    final int DEFAULT_SQUARE = 4;
    final ArrayList<Character> DEFAULT_MINI_CONTENT =  new ArrayList<>(Collections.nCopies(9, ' '));
    protected String totalTime;
    protected String formattedElapsedTime;
    protected Player currentPlayer;
    protected Player p1;
    protected Player p2;

    protected Position position;
    protected int selected;
    protected static int nextgame;
    public static int gameIsOver;
    protected static boolean countingTime;
    protected static boolean isTimePaused;

    ArrayList<Mini> bigSquares;

    public void toggleTimePaused() {isTimePaused = !isTimePaused;}
    public boolean getIsPaused(){return isTimePaused;}
    public int getGameIsOver() {return gameIsOver;}

    public int getSelected() {return selected;}

    public void setInialGame() {
        countingTime = true;
        gameIsOver = 0;
        isTimePaused = false;
    }

    public abstract List<Character> getContents();

    /**
    public ArrayList<Mini> getBigSquares(){
        return bigSquares;
    }**/


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

    public abstract Position getMinPosition();

    protected File file;
    protected List<String> initialBoard = new ArrayList<>();


    public String getLine(int i) {
        return initialBoard.get(i);
    }

    public int getNumberLines() {
        return this.initialBoard.size();
    }

    protected void writeTotalTimeToFile(String time) {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(System.getProperty("user.dir") + "/resources/total_time.txt"), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            writer.println(time);
        } catch (IOException e) {
        throw new RuntimeException("Erro ao escrever no arquivo", e);
    }
}

    public abstract int getInnerSelected();
    public abstract void setGameState();

    public String findMinTimeFromFile() {
        try (Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/resources/total_time.txt"), StandardCharsets.UTF_8)) {
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
        if (Objects.equals(time1, "null")) System.out.println("error in 1");
        if (Objects.equals(time2, "null")) System.out.println("error in 2");
        LocalTime localTime1 = LocalTime.parse(time1);
        LocalTime localTime2 = LocalTime.parse(time2);

        return localTime1.compareTo(localTime2);
    }

    public String getFormattedElapsedTime() {
        return formattedElapsedTime;
    }

    public void resetElapsedTime() {
        formattedElapsedTime = "00:00:00";
    }

    public void updateElapsedTime() {
        long startTime = System.currentTimeMillis();
        long pausedTime = 0;

        while (countingTime) {

            long currentTime = System.currentTimeMillis();

            if (isTimePaused) {pausedTime++;}

            long elapsedTime = ((currentTime - startTime) / 1000 - pausedTime); // Convert to seconds

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


    public abstract boolean select(Player player);

}
