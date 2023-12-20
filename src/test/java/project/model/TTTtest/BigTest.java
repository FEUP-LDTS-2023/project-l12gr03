package project.model.TTTtest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.model.board.Big;
import project.model.board.Mini;
import project.model.board.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            if (it % 3 == 0) Assertions.assertEquals(4, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(1, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(7, big.getSelected());
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
            if (it % 3 == 0) Assertions.assertEquals(4, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(7, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(1, big.getSelected());
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
                    Assertions.assertEquals(4, big.getSelected());
                    break;
                case 1:
                    Assertions.assertEquals(3, big.getSelected());
                    break;
                case 2:
                    Assertions.assertEquals(2, big.getSelected());
                    break;
                case 3:
                    Assertions.assertEquals(1, big.getSelected());
                    break;
                case 4:
                    Assertions.assertEquals(0, big.getSelected());
                    break;
                case 5:
                    Assertions.assertEquals(8, big.getSelected());
                    break;
                case 6:
                    Assertions.assertEquals(7, big.getSelected());
                    break;
                case 7:
                    Assertions.assertEquals(6, big.getSelected());
                    break;
                case 8:
                    Assertions.assertEquals(5, big.getSelected());
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
                    Assertions.assertEquals(4, big.getSelected());
                    break;
                case 1:
                    Assertions.assertEquals(5, big.getSelected());
                    break;
                case 2:
                    Assertions.assertEquals(6, big.getSelected());
                    break;
                case 3:
                    Assertions.assertEquals(7, big.getSelected());
                    break;
                case 4:
                    Assertions.assertEquals(8, big.getSelected());
                    break;
                case 5:
                    Assertions.assertEquals(0, big.getSelected());
                    break;
                case 6:
                    Assertions.assertEquals(1, big.getSelected());
                    break;
                case 7:
                    Assertions.assertEquals(2, big.getSelected());
                    break;
                case 8:
                    Assertions.assertEquals(3, big.getSelected());
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
            if (it % 3 == 0) Assertions.assertEquals(5, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(8, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(2, big.getSelected());
            big.goDown();
        }

        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) Assertions.assertEquals(5, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(2, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(8, big.getSelected());
            big.goUp();
        }

        big.goLeft(); big.goLeft();

        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) Assertions.assertEquals(3, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(6, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(0, big.getSelected());
            big.goDown();
        }

        for (int it = 0; it < 3; it++) {
            if (it % 3 == 0) Assertions.assertEquals(3, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(0, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(6, big.getSelected());
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

    /*
    @Test
    void endGameTest() {
        Assertions.assertTrue(big.isCountingTime());
        doNothing().when(big).writeTotalTimeToFile(anyString());
        big.endGame();
        verify(big,times(1)).writeTotalTimeToFile(anyString());
        Assertions.assertFalse(big.isCountingTime());
    }*/
    //TODO endGame funciona sozinho mas não em separado

    @Test
    void setXBigGameStateTest() {

        for (Mini m : squares) when(m.getMiniGameState()).thenReturn(1);
        big.setBigGameState();

        Assertions.assertEquals(1,big.getGameIsOver());
        verify(big,times(1)).endGame();
    }

    /*@Test
    void setOBigGameStateTest() {

        for (Mini m : squares) when(m.getMiniGameState()).thenReturn(2);
        big.setBigGameState();

        Assertions.assertEquals(2,big.getGameIsOver());
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


        big.setBigGameState();

        Assertions.assertEquals(3,big.getGameIsOver());
        verify(big,times(1)).endGame();
    }*/

    //TODO perguntar setBigGamestate não funciona com mocks
    //when(big.checkWinner(anyList(),1).thenReturn(1) não funciona
}
