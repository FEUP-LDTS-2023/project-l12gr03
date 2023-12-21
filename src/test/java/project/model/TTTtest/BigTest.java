package project.model.TTTtest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.model.Position;
import project.model.board.Big;
import project.model.board.Mini;
import project.model.board.Player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BigTest {

    private Big big;
    private Player mockP1;
    private Player mockP2;
    private List<Mini> squares = new ArrayList<Mini>();

    @BeforeEach
    void setp() throws IOException {
        this.mockP1 = Mockito.mock(Player.class);
        this.mockP2 = Mockito.mock(Player.class);
        for (int col = 0; col < 9; col++){
            Mini mini = Mockito.mock(Mini.class);
            this.squares.add(mini);
        }
        this.big = Mockito.spy(new Big(mockP1, mockP2,0,0,this.squares));
    }
    @Test
    void goUpTest() throws IOException {
        for (int it = 0; it < 4; it++){
            when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(-1);
            if (it % 3 == 0) assertEquals(4, big.getSelected());
            if (it % 3 == 1) assertEquals(1, big.getSelected());
            if (it % 3 == 2) assertEquals(7, big.getSelected());
            big.goUp();
        }

        when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(1);
        big.goUp();
        verify(squares.get(big.getSelected()),times(1)).goUp();
    }

    @Test
    void goDownTest() throws IOException {
        for (int it = 0; it < 4; it++){
            when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(-1);
            if (it % 3 == 0) assertEquals(4, big.getSelected());
            if (it % 3 == 1) assertEquals(7, big.getSelected());
            if (it % 3 == 2) assertEquals(1, big.getSelected());
            big.goDown();
        }

        when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(1);
        big.goDown();
        verify(squares.get(big.getSelected()),times(1)).goDown();
    }

    @Test
    void goLeftTest() throws IOException{
        for (int it = 0; it < 9 ;it++) {
            when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(-1);
            switch (it%9){
                case 0:
                    assertEquals(4, big.getSelected());
                    break;
                case 1:
                    assertEquals(3, big.getSelected());
                    break;
                case 2:
                    assertEquals(2, big.getSelected());
                    break;
                case 3:
                    assertEquals(1, big.getSelected());
                    break;
                case 4:
                    assertEquals(0, big.getSelected());
                    break;
                case 5:
                    assertEquals(8, big.getSelected());
                    break;
                case 6:
                    assertEquals(7, big.getSelected());
                    break;
                case 7:
                    assertEquals(6, big.getSelected());
                    break;
                case 8:
                    assertEquals(5, big.getSelected());
                    break;
            }
            big.goLeft();
        }

        when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(1);
        big.goLeft();
        verify(squares.get(big.getSelected()),times(1)).goLeft();
    }

    @Test
    void goRightTest() throws IOException{
        for (int it = 0; it < 9 ;it++)
        {
            when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(-1);
            switch (it%9){
                case 0:
                    assertEquals(4, big.getSelected());
                    break;
                case 1:
                    assertEquals(5, big.getSelected());
                    break;
                case 2:
                    assertEquals(6, big.getSelected());
                    break;
                case 3:
                    assertEquals(7, big.getSelected());
                    break;
                case 4:
                    assertEquals(8, big.getSelected());
                    break;
                case 5:
                    assertEquals(0, big.getSelected());
                    break;
                case 6:
                    assertEquals(1, big.getSelected());
                    break;
                case 7:
                    assertEquals(2, big.getSelected());
                    break;
                case 8:
                    assertEquals(3, big.getSelected());
                    break;
            }
            big.goRight();
        }

        when(squares.get(big.getSelected()).getInnerSelected()).thenReturn(1);
        big.goRight();
        verify(squares.get(big.getSelected()),times(1)).goRight();
    }

    @Test
    void MixedMovementTest() throws IOException {
        for (Mini mini : squares) when(mini.getInnerSelected()).thenReturn(-1);
        big.goRight();
        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) assertEquals(5, big.getSelected());
            if (it % 3 == 1) assertEquals(8, big.getSelected());
            if (it % 3 == 2) assertEquals(2, big.getSelected());
            big.goDown();
        }

        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) assertEquals(5, big.getSelected());
            if (it % 3 == 1) assertEquals(2, big.getSelected());
            if (it % 3 == 2) assertEquals(8, big.getSelected());
            big.goUp();
        }

        big.goLeft(); big.goLeft();

        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) assertEquals(3, big.getSelected());
            if (it % 3 == 1) assertEquals(6, big.getSelected());
            if (it % 3 == 2) assertEquals(0, big.getSelected());
            big.goDown();
        }

        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) assertEquals(3, big.getSelected());
            if (it % 3 == 1) assertEquals(0, big.getSelected());
            if (it % 3 == 2) assertEquals(6, big.getSelected());
            big.goUp();
        }
    }

    @Test
    void getPlayStateTest() {
        big.getPlayState();
        for(int i=0; i<9;i++){
            verify(squares.get(i),times(1)).getMiniGameState();
        }
    }

    //TODO endGame funciona sozinho mas não em separado

    @Test
    void setXBigGameStateTest() {

        for (Mini m : squares) when(m.getMiniGameState()).thenReturn(1);
        doNothing().when(big).endGame();
        big.setBigGameState();

        assertEquals(1,big.getGameIsOver());
        verify(big,times(1)).endGame();
    }

    @Test
    void setOBigGameStateTest() {

        for (Mini m : squares) when(m.getMiniGameState()).thenReturn(2);
        doNothing().when(big).endGame();
        big.setBigGameState();

        assertEquals(2,big.getGameIsOver());
        verify(big,times(1)).endGame();
    }

    @Test
    void setTieBigGameStateTest() {

        when(squares.get(0).getMiniGameState()).thenReturn(1);
        when(squares.get(1).getMiniGameState()).thenReturn(1);
        when(squares.get(2).getMiniGameState()).thenReturn(2);
        when(squares.get(3).getMiniGameState()).thenReturn(2);
        when(squares.get(4).getMiniGameState()).thenReturn(2);
        when(squares.get(5).getMiniGameState()).thenReturn(1);
        when(squares.get(6).getMiniGameState()).thenReturn(1);
        when(squares.get(7).getMiniGameState()).thenReturn(1);
        when(squares.get(8).getMiniGameState()).thenReturn(2);

        doNothing().when(big).endGame();
        big.setBigGameState();

        assertEquals(3,big.getGameIsOver());
        verify(big,times(1)).endGame();
    }

    @Test
    void getConstentsTest() {
        for (Mini mockMini : squares){when(mockMini.getContents()).thenReturn(Arrays.asList(' ',' ',' ',
                                                                                            ' ',' ',' ',
                                                                                            ' ',' ',' '));}
        List<Character> res = big.getContents();
        for (Mini mockMini : squares){
            verify(mockMini,times(1)).getContents();
        }
        assertEquals(81,res.size());
    }

    @Test
    void selectTest() {
        when(big.getPlayer()).thenReturn(mockP1);
        when(big.getSelected()).thenReturn(4);
        when(squares.get(4).select(mockP1)).thenReturn(false);
        big.select(mockP1);
        Assertions.assertFalse(big.select(mockP1));

        when(squares.get(4).select(mockP1)).thenReturn(true);
        boolean flag = squares.get(4).select(mockP1);
        big.select(mockP1);
        Assertions.assertTrue(flag);
        //verify(big,times(1)).switchPlayer();
        //verify(big,times(1)).setGameState();
    }

    @Test
    void endGameTest(){
        when(big.getFormattedElapsedTime()).thenReturn("W");
        doNothing().when(big).writeTotalTimeToFile(anyString());
        big.endGame();

        verify(big,times(1)).getFormattedElapsedTime();
        verify(big,times(1)).writeTotalTimeToFile("W");
        Assertions.assertFalse(big.isCountingTime());
    }

    @Test
    void getMinPositionTest() {
        when(big.getSelected()).thenReturn(0);
        when(squares.get(0).getMinPosition()).thenReturn(new Position(0,1));
        for (int i = 1 ; i<9; i++) when(squares.get(i).getMinPosition()).thenReturn(new Position(2,3));

        Position pos = big.getMinPosition();
        assertEquals(0,pos.getX());
        assertEquals(1,pos.getY());
    }

    @Test
    void getInnerSelectedTest() {
        when(big.getSelected()).thenReturn(0);
        when(squares.get(0).getInnerSelected()).thenReturn(7);
        int res = big.getInnerSelected();
        assertEquals(7,res);

        when(squares.get(0).getInnerSelected()).thenReturn(1);
        res = big.getInnerSelected();
        assertEquals(1,res);

    }

    @Test
    void toggleTimePaused(){
        boolean flag = big.getIsPaused();
        big.toggleTimePaused();
        Assertions.assertNotEquals(flag, big.getIsPaused());
    }

    @Test
    void getPlayersTest() {
        assertEquals(mockP1,big.getp1());
        assertEquals(mockP2,big.getp2());
    }

    @Test
    void setPositonTest() {
        big.setPosition(new Position(0,0));
        Assertions.assertNotEquals(1,big.getPosition().getX());
        Assertions.assertNotEquals(1,big.getPosition().getY());

        big.setPosition(new Position(1,1));
        assertEquals(1,big.getPosition().getX());
        assertEquals(1,big.getPosition().getY());
    }

    @Test
    void getNumberLinesTest() {
        assertEquals(23,big.getNumberLines());
    }

    @Test
    void writeTotalTimeToFileTest() throws IOException {
        Big big = Mockito.spy(new Big(mockP1, mockP2, 0, 0, this.squares));
        String time = "01:23:45";

        doReturn("00:59:59").when(big).findMinTimeFromFile();

        big.writeTotalTimeToFile(time);

        verify(big, times(1)).findMinTimeFromFile();
        verify(big, times(1)).compareTimes(time, "00:59:59");

        doCallRealMethod().when(big).findMinTimeFromFile();
    }

    @Test
    void findMinTimeFromFileTest() throws IOException {
        Big big = Mockito.spy(new Big(mockP1, mockP2, 0, 0, this.squares));

        Path tempFile = Files.createTempFile("tempTotalTime", ".txt");
        Files.write(tempFile, Arrays.asList("01:23:45", "03:59:59", "02:30:00"), StandardCharsets.UTF_8);

        doReturn(tempFile).when(big).getTotalTimeFilePath();

        String minTime = big.findMinTimeFromFile();

        verify(big, times(1)).getTotalTimeFilePath();

        assertEquals("01:23:45", minTime);
    }

    @Test
    void checkRows() {
        List<Integer> states1 = Arrays.asList(1,1,1
                                            ,0,0,0,
                                             0,0,0);

        Assertions.assertTrue(Big.checkRowsCase0(states1,1));
        Assertions.assertFalse(Big.checkRowsCase0(states1,2));

        //Assertions.assertFalse(Big.checkRowsCase1(states1,1));
        //Assertions.assertFalse(Big.checkRowsCase1(states1,2));
        //Assertions.assertFalse(Big.checkRowsCase2(states1,1));
        //Assertions.assertFalse(Big.checkRowsCase2(states1,2));


        List<Integer> states2 = Arrays.asList(0,1,1
                                             ,2,2,2,
                                              3,1,0);

        //Assertions.assertFalse(Big.checkRowsCase1(states2,1));
        //Assertions.assertTrue(Big.checkRowsCase1(states2,2));

        //Assertions.assertFalse(Big.checkRowsCase0(states2,1));
        //Assertions.assertFalse(Big.checkRowsCase0(states2,2));
        //Assertions.assertFalse(Big.checkRowsCase2(states2,1));
        //Assertions.assertFalse(Big.checkRowsCase2(states2,2));

        List<Integer> states3 = Arrays.asList(0,1,1
                                             ,0,0,0,
                                              1,1,1);

        Assertions.assertTrue(Big.checkRowsCase2(states3,1));
        Assertions.assertFalse(Big.checkRowsCase2(states3,2));

        //Assertions.assertFalse(Big.checkRowsCase1(states3,1));
        //Assertions.assertFalse(Big.checkRowsCase1(states3,2));
        //Assertions.assertFalse(Big.checkRowsCase0(states3,1));
        //Assertions.assertFalse(Big.checkRowsCase0(states3,2));

    }

    @Test
    void checkColumns() {
        List<Integer> states1 = Arrays.asList(1,0,0
                                             ,1,0,0,
                                              1,0,0);

        Assertions.assertTrue(Big.checkColumnsCase0(states1,1));
        Assertions.assertFalse(Big.checkColumnsCase0(states1,2));

        //Assertions.assertFalse(Big.checkColumnsCase1(states1,1));
        //Assertions.assertFalse(Big.checkColumnsCase1(states1,2));
        //Assertions.assertFalse(Big.checkColumnsCase2(states1,1));
        //Assertions.assertFalse(Big.checkColumnsCase2(states1,2));


        List<Integer> states2 = Arrays.asList(0,2,1
                                             ,1,2,3,
                                              1,2,0);

        Assertions.assertFalse(Big.checkColumnsCase1(states2,1));
        Assertions.assertTrue(Big.checkColumnsCase1(states2,2));

        //Assertions.assertFalse(Big.checkColumnsCase0(states2,1));
        //Assertions.assertFalse(Big.checkColumnsCase0(states2,2));
        //Assertions.assertFalse(Big.checkColumnsCase2(states2,1));
        //Assertions.assertFalse(Big.checkColumnsCase2(states2,2));

        List<Integer> states3 = Arrays.asList(0,0,1
                                             ,0,1,1,
                                              1,1,1);

        Assertions.assertTrue(Big.checkColumnsCase2(states3,1));
        Assertions.assertFalse(Big.checkColumnsCase2(states3,2));

        //Assertions.assertFalse(Big.checkColumnsCase1(states3,1));
        //Assertions.assertFalse(Big.checkColumnsCase1(states3,2));
        //Assertions.assertFalse(Big.checkColumnsCase0(states3,1));
        //Assertions.assertFalse(Big.checkColumnsCase0(states3,2));

        List<Integer> states4 = Arrays.asList(1,1,1
                                             ,0,1,2,
                                              1,2,1);

        Assertions.assertFalse(Big.checkColumnsCase0(states4,1));
        Assertions.assertFalse(Big.checkColumnsCase1(states4,1));
        Assertions.assertFalse(Big.checkColumnsCase2(states4,1));

        Assertions.assertFalse(Big.checkColumnsCase0(states4,2));
        Assertions.assertFalse(Big.checkColumnsCase1(states4,2));
        Assertions.assertFalse(Big.checkColumnsCase2(states4,2));    }


    //TODO perguntar setBigGamestate não funciona com mocks
    //when(big.checkWinner(anyList(),1).thenReturn(1) não funciona
}
