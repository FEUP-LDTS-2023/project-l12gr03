package model.TTTtest;

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
import java.util.List;

import static org.mockito.Mockito.*;

public class BigTest {

    private Big big;
    private Player mockP1;
    private Player mockP2;
    private List<Mini> squares = new ArrayList<Mini>();
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

    @Property
    void goDownTest(@ForAll int x, @ForAll int y) throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mockmini = Mockito.mock(Mini.class);
        ArrayList<Mini> bigSquaresL = new ArrayList<>();
        when(mockmini.getInnerSelected()).thenReturn(-1);
        for (int row=0;row<3;row++)
        {
            for (int column=0; column<3; column++)
            {
                bigSquaresL.add(mockmini);
            }
        }
        Big big = new Big(p1,p2,x,y,bigSquaresL);
        for (int it = 0; it < 4; it++) {
            if (it % 3 == 0) Assertions.assertEquals(4, big.getSelected());
            if (it % 3 == 1) Assertions.assertEquals(7, big.getSelected());
            if (it % 3 == 2) Assertions.assertEquals(1, big.getSelected());
            big.goDown();
        }
    }

    @Property
    void goLeftTest(@ForAll int x, @ForAll int y) throws IOException{
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mockmini = Mockito.mock(Mini.class);
        when(mockmini.getInnerSelected()).thenReturn(-1);
        ArrayList<Mini> bigSquaresL = new ArrayList<>();
        for (int row=0;row<3;row++)
        {
            for (int column=0; column<3; column++)
            {
                bigSquaresL.add(mockmini);
            }
        }
        Big big = new Big(p1,p2,x,y,bigSquaresL);

        for (int it = 0; it < 9 ;it++)
        {
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
    }

    @Property
    void goRightTest(@ForAll int x, @ForAll int y) throws IOException{
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mockmini = Mockito.mock(Mini.class);
        when(mockmini.getInnerSelected()).thenReturn(-1);
        ArrayList<Mini> bigSquaresL = new ArrayList<>();
        for (int row=0;row<3;row++)
        {
            for (int column=0; column<3; column++)
            {
                bigSquaresL.add(mockmini);
            }
        }
        Big big = new Big(p1,p2,x,y,bigSquaresL);
        for (int it = 0; it < 9 ;it++)
        {
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
    }

    @Property
    void MixedMovementTest(@ForAll int x, @ForAll int y) throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mockmini = Mockito.mock(Mini.class);
        when(mockmini.getInnerSelected()).thenReturn(-1);
        ArrayList<Mini> bigSquaresL = new ArrayList<>();
        for (int row=0;row<3;row++)
        {
            for (int column=0; column<3; column++)
            {
                bigSquaresL.add(mockmini);
            }
        }
        Big big = new Big(p1,p2,x,y,bigSquaresL);
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

    @BeforeEach
    void setp() throws IOException {
        this.mockP1 = Mockito.mock(Player.class);
        this.mockP2 = Mockito.mock(Player.class);
        for (int col = 0; col < 9; col++){
            Mini mini = Mockito.mock(Mini.class);
            this.squares.add(mini);
        }
        this.big = new Big(mockP1, mockP2,0,0,this.squares);
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
    void getPlayStateTest() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mini = Mockito.mock(Mini.class);
        Big big = new Big(p1,p2,0,0);
        List<Integer> playStates = new ArrayList<>(big.getPlayState());
        for (int comp=0; comp < 4; comp++){

            Mockito.when(mini.getMiniGameState()).thenReturn(comp);
            for (Integer i : playStates) Assertions.assertEquals(comp,i);
        }
    }

    @Test
    void getContentsTest() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Big big = new Big(p1,p2,0,0);
        List<Character> symbols = new ArrayList<>(Arrays.asList(' ','X','O'));
        for (Character car : symbols){
            Mini mini = Mockito.mock(Mini.class);

            List<Character> mockedContents = new ArrayList<>();
            for (int i=0; i<9; i++) mockedContents.add(car);

            Mockito.when(mini.getContents()).thenReturn(mockedContents);
            for (Character content : big.getContents()){
                Assertions.assertEquals(car,content);
            }
        }
    }
*/
}
