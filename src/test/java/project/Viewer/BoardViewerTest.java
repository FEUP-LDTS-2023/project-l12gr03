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
        Player player = Mockito.mock(Player.class);
        when(ticTacToe.getPlayer()).thenReturn(player);
        when(player.getScore()).thenReturn((float)1);
        when(player.getSymbol()).thenReturn('X');
        when(ticTacToe.getNumberLines()).thenReturn(2);
        when(ticTacToe.getLine(0)).thenReturn("Line1");
        when(ticTacToe.getLine(1)).thenReturn("Line2");
        viewer.drawBasicGrid(gui);
        verify(gui,times(1)).drawText(new Position(10,5),"Player X it is your turn!","#FFFFFF");
        verify(gui,times(1)).drawText(new Position(10,7),"Line1","#FFFFFF");
        verify(gui,times(1)).drawText(new Position(10,8),"Line2","#FFFFFF");


    }
    @Test
    void drawElementsTestBasic(){
        GUI gui = Mockito.mock(GUI.class);
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getPlayState()).thenReturn(Arrays.asList(3,3,3,3,3,3,3,3,3));

        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        when(ticTacToe.getp1()).thenReturn(player1);
        when(ticTacToe.getp2()).thenReturn(player2);


        viewer.drawElements(gui);

        verify(viewer,times(1)).drawBasicGrid(gui);
        verify(viewer,times(1)).drawMinis(gui);
        verify(viewer,times(1)).highlightMini(gui,new Position(2,2),0);
        verify(viewer,times(1)).drawScores(gui);


    }

    @Test
    void drawElementsTestHighlightBig0() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


            GUI gui = Mockito.mock(GUI.class);
            doNothing().when(viewer).drawBasicGrid(gui);
            doNothing().when(viewer).drawMinis(gui);
            doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
            doNothing().when(viewer).drawScores(gui);

            when(ticTacToe.getSelected()).thenReturn(0);

            viewer.drawElements(gui);

            verify(gui,times(1)).drawText(new Position(10, 14), "--------------+", "#FFFF00");
            for (int e = 0; e < 7 ; e++)
                verify(gui,times(1)).drawText(new Position(24, 7 + e), "|", "#FFFF00");
    }
    @Test
    void drawElementsTestHighlightBig1() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(1);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(24, 14), "+-----------------+", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(24, 7 + i), "|", "#FFFF00");
            verify(gui, times(1)).drawText(new Position(42, 7 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig2() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(2);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(42, 14), "+---------------", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(42, 7 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig3() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(3);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(10, 14), "--------------+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(10, 22), "--------------+", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(24, 15 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig4() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(4);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(24, 14), "+-----------------+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(24, 22), "+-----------------+", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(24, 15 + i), "|", "#FFFF00");
            verify(gui, times(1)).drawText(new Position(42, 15 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig5() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(5);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(42, 14), "+---------------", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(42, 22), "+---------------", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(42, 15 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig6() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(6);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(10, 22), "--------------+", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(24, 23 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig7() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(7);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(24, 22), "+-----------------+", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(24, 23 + i), "|", "#FFFF00");
            verify(gui, times(1)).drawText(new Position(42, 23 + i), "|", "#FFFF00");
        }
    }
    @Test
    void drawElementsTestHighlightBig8() {
        TicTacToe ticTacToe = Mockito.mock(TicTacToe.class);
        BoardViewer viewer = Mockito.spy(new BoardViewer(ticTacToe));
        when(ticTacToe.getMinPosition()).thenReturn(new Position(2,2));
        when(ticTacToe.getInnerSelected()).thenReturn(0);


        GUI gui = Mockito.mock(GUI.class);
        doNothing().when(viewer).drawBasicGrid(gui);
        doNothing().when(viewer).drawMinis(gui);
        doNothing().when(viewer).highlightMini(eq(gui),any(Position.class),any(Integer.class));
        doNothing().when(viewer).drawScores(gui);

        when(ticTacToe.getSelected()).thenReturn(8);

        viewer.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(42, 22), "+---------------", "#FFFF00");
        for (int i = 0; i < 7 ; i++) {
            verify(gui, times(1)).drawText(new Position(42, 23 + i), "|", "#FFFF00");
        }
    }
}

