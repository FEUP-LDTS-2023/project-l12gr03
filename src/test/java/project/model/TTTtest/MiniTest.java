package project.model.TTTtest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import project.model.Position;
import project.model.board.Mini;
import project.model.board.Player;
import project.model.board.TicTacToe;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class MiniTest {

    final ArrayList<Character> DEFAULT_MINI_CONTENT =  new ArrayList<>(Collections.nCopies(9, ' '));
    Mini mini;
    @BeforeEach
    void setUp() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        mini = Mockito.spy(new Mini(p1,p2,0,0,DEFAULT_MINI_CONTENT));
    }
    @Property
    void getPositionTest(@ForAll int x, @ForAll int y) throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mini = new Mini(p1,p2,x,y,DEFAULT_MINI_CONTENT);

        Assertions.assertEquals(new Position(x,y),mini.getPosition());
        Assertions.assertEquals(new Position(x,y),mini.getMinPosition());
    }
    @Test
    void IsGameTieTest() {
        boolean result;
        List<Character> squares = List.of('X', 'O', 'X', 'X', 'O', 'X', 'X', 'O', ' ');
        result = Mini.isGameTie(squares);
        Assertions.assertFalse(result, "Expected game not to be a tie with empty squares available");

        squares = List.of('X', 'O', 'X', 'O','X', 'O','X', 'O','X');
        result = Mini.isGameTie(squares);
        Assertions.assertTrue(result, "Expected game to be a tie with no empty squares available");

        squares = List.of(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ');
        result = Mini.isGameTie(squares);
        Assertions.assertFalse(result, "Expected game not to be a tie with all squares empty");
    }
    @Test
    void checkRowsTests(){
        for (Character car : Arrays.asList('X','O')) {
            boolean res = Mini.checkRows(DEFAULT_MINI_CONTENT, car);
            Assertions.assertFalse(res);

            Character other;
            if (car == 'X') other='O'; else other='X';
            Assertions.assertFalse(Mini.checkRows(Arrays.asList(
                    car, ' ', car,
                    car, car, other,
                    car, other, car) , car));

            Assertions.assertTrue(Mini.checkRows(Arrays.asList(
                    car, car, car,
                    car, car, ' ',
                    ' ', other, other), car));

            Assertions.assertTrue(Mini.checkRows(Arrays.asList(
                    car, car, ' ',
                    car, car, car,
                    ' ', other, other), car));

            Assertions.assertTrue(Mini.checkRows(Arrays.asList(
                    car, car, ' ',
                    ' ', other, other,
                    car, car, car), car));
        }
    }
    @Test
    void checkColumnsTests(){
        for (Character car : Arrays.asList('X','O')) {
            boolean res = Mini.checkColumns(DEFAULT_MINI_CONTENT, car);
            Assertions.assertFalse(res);

            Character other;
            if (car == 'X') other='O'; else other='X';
            Assertions.assertFalse(Mini.checkColumns(Arrays.asList(
                    car, car, ' ',
                    car, car, car,
                    ' ', other, car) , car));

            Assertions.assertTrue(Mini.checkColumns(Arrays.asList(
                    car, other, other,
                    car, car, ' ',
                    car, other, ' '), car));

            Assertions.assertTrue(Mini.checkColumns(Arrays.asList(
                    car, car, other,
                    other, car, ' ',
                    car, car, ' '), car));

            Assertions.assertTrue(Mini.checkColumns(Arrays.asList(
                    ' ', other, car,
                    other, car, car,
                    ' ', other, car), car));
        }
    }
    @Test
    void checkDiagonalsTests(){
        for (Character car : Arrays.asList('X','O')) {
            boolean res = Mini.checkDiagonals(DEFAULT_MINI_CONTENT, car);
            Assertions.assertFalse(res);

            Character other;
            if (car == 'X') other='O'; else other='X';
            Assertions.assertFalse(Mini.checkDiagonals(Arrays.asList(
                    car, car, car,
                    car, ' ', car,
                    car, car, car) , car));

            Assertions.assertTrue(Mini.checkDiagonals(Arrays.asList(
                    car, other, other,
                    car, car, ' ',
                    car, other, car), car));

            Assertions.assertTrue(Mini.checkDiagonals(Arrays.asList(
                    other, other, car,
                    car, car, ' ',
                    car, other, other), car));
        }
    }

    @Test
    void checkXWon() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        mini = new Mini(p1,p2,0,0,Arrays.asList('X','X','X','X','X','X','X','X','X'));
        mini.setGameState();
        Assertions.assertEquals(1,mini.getMiniGameState());
        Assertions.assertEquals(1,mini.getPlayState().get(0));

    }
    @Test
    void checkOWon() throws IOException {
        /*
        Mini mockMini = mock(Mini.class);
        when(mockMini.checkRows(anyList(),anyChar())).thenReturn(true);
        when(mockMini.checkWinner(anyList(),'X')).thenReturn(false);
        when(mockMini.checkWinner(anyList(),'O')).thenReturn(true);
        mockMini.setMiniGameState();
        Assertions.assertEquals(2,mockMini.getMiniGameState());*/

        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        mini = new Mini(p1,p2,0,0,Arrays.asList('O','O','O','O','O','O','O','O','O'));
        mini.setGameState();
        Assertions.assertEquals(2,mini.getMiniGameState());
        Assertions.assertEquals(2,mini.getPlayState().get(0));

    }
    @Test
    void checkTie() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        mini = new Mini(p1,p2,0,0,Arrays.asList('O','O','X','X','X','O','O','X','X'));
        mini.setGameState();
        Assertions.assertEquals(3,mini.getMiniGameState());
        Assertions.assertEquals(3,mini.getPlayState().get(0));

    }
    @Test
    void setStateDoNothing() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        mini = new Mini(p1,p2,0,0,Arrays.asList('O',' ','X','X','X','O','O','X','X'));
        mini.setGameState();
        Assertions.assertEquals(0,mini.getMiniGameState());
    }

    @Test
    void GoUp(){
        Player player = Mockito.mock(Player.class);
        mini.select(player);
        for (int it = 0; it < 4; it++){
            if (it % 3 == 0) Assertions.assertEquals(4, mini.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(1, mini.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(7, mini.getSelected());
            mini.goUp();
        }
    }
    @Test
    void GoDown(){
        Player player = Mockito.mock(Player.class);
        mini.select(player);
        for (int it = 0; it < 4; it++){
            if (it % 3 == 0) Assertions.assertEquals(4, mini.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(7, mini.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(1, mini.getSelected());
            mini.goDown();
        }
    }
    @Test
    void GoLeft(){
        Player player = Mockito.mock(Player.class);
        mini.select(player);
        for (int it = 0; it < 9 ;it++)
        {
            switch (it%9){
                case 0:
                    Assertions.assertEquals(4, mini.getSelected());
                    break;
                case 1:
                    Assertions.assertEquals(3, mini.getSelected());
                    break;
                case 2:
                    Assertions.assertEquals(2, mini.getSelected());
                    break;
                case 3:
                    Assertions.assertEquals(1, mini.getSelected());
                    break;
                case 4:
                    Assertions.assertEquals(0, mini.getSelected());
                    break;
                case 5:
                    Assertions.assertEquals(8, mini.getSelected());
                    break;
                case 6:
                    Assertions.assertEquals(7, mini.getSelected());
                    break;
                case 7:
                    Assertions.assertEquals(6, mini.getSelected());
                    break;
                case 8:
                    Assertions.assertEquals(5, mini.getSelected());
                    break;
            }
            mini.goLeft();
        }
    }

    @Test
    void getInnerSelected(){
        Assertions.assertEquals(mini.getInnerSelected(),mini.getSelected());
    }
    @Test
    void GoRight(){
        Player player = Mockito.mock(Player.class);
        mini.select(player);
        for (int it = 0; it < 9 ;it++)
        {
            switch (it%9){
                case 0:
                    Assertions.assertEquals(4, mini.getSelected());
                    break;
                case 1:
                    Assertions.assertEquals(5, mini.getSelected());
                    break;
                case 2:
                    Assertions.assertEquals(6, mini.getSelected());
                    break;
                case 3:
                    Assertions.assertEquals(7, mini.getSelected());
                    break;
                case 4:
                    Assertions.assertEquals(8, mini.getSelected());
                    break;
                case 5:
                    Assertions.assertEquals(0, mini.getSelected());
                    break;
                case 6:
                    Assertions.assertEquals(1, mini.getSelected());
                    break;
                case 7:
                    Assertions.assertEquals(2, mini.getSelected());
                    break;
                case 8:
                    Assertions.assertEquals(3, mini.getSelected());
                    break;
            }
            mini.goRight();
        }
    }

    @Test
    void endGame(){mini.endGame();}

    

    @Test
    void selectTest(){
        Player player = Mockito.mock(Player.class);

        when(mini.isOver()).thenReturn(true);
        //when(mini.select(player)).thenCallRealMethod();

        boolean flag = mini.select(player);
        Assertions.assertFalse(flag);

        when(mini.isOver()).thenReturn(false);
        flag = mini.select(player);
        Assertions.assertEquals(4,mini.getSelected());
        Assertions.assertFalse(flag);


        when(mini.drawsymbol(player)).thenReturn(true);
        when(mini.getSelected()).thenReturn(4);
        flag = mini.select(player);
        Assertions.assertTrue(flag);

        when(mini.drawsymbol(player)).thenReturn(false);
        flag = mini.select(player);
        Assertions.assertFalse(flag);
    }


    @Test
    void drawSymbolsTest() throws IOException {
        when(mini.getSelected()).thenReturn(4);
        Player player = Mockito.mock(Player.class);
        when(player.getSymbol()).thenReturn('O');

        boolean flag = mini.drawsymbol(player);
        Assertions.assertTrue(flag);
        when(mini.getSelected()).thenCallRealMethod();
        Assertions.assertEquals(-1,mini.getSelected());
        List<Character> ls = mini.getContents();
        Assertions.assertEquals('O',ls.get(4));

        mini = Mockito.spy(new Mini(player,player,0,0,Arrays.asList(' ',' ',' ',' ','X',' ',' ',' ',' ')));
        when(mini.getSelected()).thenReturn(4);

        flag = mini.drawsymbol(player);
        Assertions.assertFalse(flag);
        Assertions.assertEquals(4,mini.getSelected());

    }

    @Test
    void getContentsTest(){
        Assertions.assertEquals(Arrays.asList(' ',' ',' ',' ',' ',' ',' ',' ',' '),mini.getContents());
    }
}