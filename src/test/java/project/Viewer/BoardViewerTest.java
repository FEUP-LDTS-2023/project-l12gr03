package project.Viewer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.Position;
import project.model.board.Player;
import project.model.board.TicTacToe;
import project.viewer.BoardViewer;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class BoardViewerTest {

    @Test
    void drawBasicGridTest() {
        GUI gui = Mockito.mock(GUI.class);
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(0,0));
        viewer.drawBasicGrid(gui);
    }
    @Test
    void drawElementsTestBasic(){
        GUI gui = Mockito.mock(GUI.class);
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(0,0));
        doNothing().when(viewer).drawBasicGrid(gui);
        when(ticTacToe.getPlayState()).thenReturn(Arrays.asList(3,3,3,3,3,3,3,3,3));


        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        when(ticTacToe.getp1()).thenReturn(player1);
        when(ticTacToe.getp2()).thenReturn(player2);
        when(player1.getSymbol()).thenReturn('X');
        when(player2.getSymbol()).thenReturn('O');
        when(player1.getScore()).thenReturn((float)1);
        when(player2.getScore()).thenReturn((float)2);



        viewer.drawElements(gui);

        verify(viewer,times(1)).drawBasicGrid(gui);
        verify(gui,times(1)).drawText(new Position(80, 8),Float.toString(3),"#FFFFFF");

        verify(gui,times(1)).drawText(new Position(77, 11),Float.toString(1),"#FFFFFF");
        verify(gui,times(1)).drawText(new Position(77, 10),Float.toString(2),"#FFFFFF");


        when(player1.getSymbol()).thenReturn('O');
        when(player2.getSymbol()).thenReturn('X');
        when(player1.getScore()).thenReturn((float)1);
        when(player2.getScore()).thenReturn((float)2);

        viewer.drawElements(gui);

        verify(viewer,times(2)).drawBasicGrid(gui);
        verify(gui,times(2)).drawText(new Position(80, 8),Float.toString(3),"#FFFFFF");

        verify(gui,times(1)).drawText(new Position(77, 10),Float.toString(1),"#FFFFFF");
        verify(gui,times(1)).drawText(new Position(77, 11),Float.toString(2),"#FFFFFF");
    }

    @Test
    void drawElementsTestBigGrid() {

    }
}
