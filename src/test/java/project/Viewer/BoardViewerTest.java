package project.Viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.Position;
import project.model.board.Player;
import project.model.board.TicTacToe;
import project.viewer.BoardViewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class BoardViewerTest {

    private BoardViewer viewer;
    private GUI gui;
    private TicTacToe ticTacToe;

    private final String DEFAULT_COLOUR = "#FFFFFF";

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(GUI.class);
        this.ticTacToe = Mockito.mock(TicTacToe.class);
        this.viewer = Mockito.spy(new BoardViewer(ticTacToe));
    }

    @Test
    void drawBasicGridTest() {
        when(ticTacToe.getMinPosition()).thenReturn(new Position(0,0));
        Player player = Mockito.mock(Player.class);
        when(ticTacToe.getPlayer()).thenReturn(player);
        when(player.getScore()).thenReturn((float)1);
        when(player.getSymbol()).thenReturn('X');
        when(ticTacToe.getNumberLines()).thenReturn(2);
        when(ticTacToe.getLine(0)).thenReturn("Line1");
        when(ticTacToe.getLine(1)).thenReturn("Line2");
        viewer.drawBasicGrid(gui);
        verify(gui,times(1)).drawText(new Position(10,5),"Player X it is your turn!",DEFAULT_COLOUR);
        verify(gui,times(1)).drawText(new Position(10,7),"Line1",DEFAULT_COLOUR);
        verify(gui,times(1)).drawText(new Position(10,8),"Line2",DEFAULT_COLOUR);
    }
    @Test
    void drawElementsTestBasic(){
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
    void handleTimeTest(){
        when(ticTacToe.findMinTimeFromFile()).thenReturn("MinTime");
        when(ticTacToe.getFormattedElapsedTime()).thenReturn("FormattedTime");
        when(ticTacToe.getIsPaused()).thenReturn(false);

        viewer.handleTime(gui);

        verify(gui,times(1)).drawText(new Position(68,28),"Record time: MinTime",DEFAULT_COLOUR);
        verify(gui,times(1)).drawText(new Position(68,27),"Playtime: FormattedTime",DEFAULT_COLOUR);
        verify(gui,times(0)).drawText(new Position(10,31),"The game is paused! Press P to resume the game.",DEFAULT_COLOUR);

        when(ticTacToe.getIsPaused()).thenReturn(true);

        GUI gui2 = Mockito.mock(GUI.class);
        viewer.handleTime(gui2);

        verify(gui2,times(1)).drawText(new Position(68,28),"Record time: MinTime",DEFAULT_COLOUR);
        verify(gui2,times(1)).drawText(new Position(68,27),"Playtime: FormattedTime",DEFAULT_COLOUR);
        verify(gui2,times(1)).drawText(new Position(10,31),"The game is paused! Press P to resume the game.",DEFAULT_COLOUR);
    }
    @Test
    void drawScores() {
        Player p1 = Mockito.mock(Player.class);
        when(p1.getScore()).thenReturn((float)1);
        when(p1.getSymbol()).thenReturn('X');
        when(ticTacToe.getp1()).thenReturn(p1);

        Player p2 = Mockito.mock(Player.class);
        when(p2.getScore()).thenReturn((float)2);
        when(p2.getSymbol()).thenReturn('O');
        when(ticTacToe.getp2()).thenReturn(p2);

        viewer.drawScores(gui);

        verify(gui,times(1)).drawText(eq(new Position(80, 8)),  eq(Float.toString(3)),  eq(DEFAULT_COLOUR));
        verify(gui,times(1)).drawText(eq(new Position(77, 11)), eq(Float.toString(1)) , eq(DEFAULT_COLOUR));
        verify(gui,times(1)).drawText(eq(new Position(77, 10)), eq(Float.toString(2)),  eq(DEFAULT_COLOUR));

        when(p1.getSymbol()).thenReturn('0');
        when(p2.getSymbol()).thenReturn('X');
        GUI gui2 = Mockito.mock(GUI.class);

        viewer.drawScores(gui2);

        verify(gui2,times(1)).drawText(eq(new Position(80, 8)),  eq(Float.toString(3)),  eq(DEFAULT_COLOUR));
        verify(gui2,times(1)).drawText(eq(new Position(77, 10)), eq(Float.toString(1)) , eq(DEFAULT_COLOUR));
        verify(gui2,times(1)).drawText(eq(new Position(77, 11)), eq(Float.toString(2)),  eq(DEFAULT_COLOUR));
    }
    @Test
    void handleEndGame() {

        when(ticTacToe.getGameIsOver()).thenReturn(0);
        viewer.handleEndGame(gui);
        verify(gui,times(0)).drawText(eq(new Position(10, 31)), eq("Congrats Player X, you WON!"), eq(DEFAULT_COLOUR));
        verify(gui,times(0)).drawText(eq(new Position(10, 32)), eq("Do you want to keep playing? [Y/N]"), eq(DEFAULT_COLOUR));
        verify(gui,times(0)).drawText(eq(new Position(10, 31)), eq("Congrats Player 0, you WON!"), eq(DEFAULT_COLOUR));
        verify(gui,times(0)).drawText(eq(new Position(10, 31)), eq("Oh no, it is a TIE GAME! "), eq(DEFAULT_COLOUR));


        when(ticTacToe.getGameIsOver()).thenReturn(1);
        GUI gui1 = Mockito.mock(GUI.class);
        viewer.handleEndGame(gui1);
        verify(gui1,times(1)).drawText(eq(new Position(10, 31)), eq("Congrats Player X, you WON!"), eq(DEFAULT_COLOUR));
        verify(gui1,times(1)).drawText(eq(new Position(10, 32)), eq("Do you want to keep playing? [Y/N]"), eq(DEFAULT_COLOUR));

        when(ticTacToe.getGameIsOver()).thenReturn(2);
        GUI gui2 = Mockito.mock(GUI.class);
        viewer.handleEndGame(gui2);
        verify(gui2,times(1)).drawText(eq(new Position(10, 31)), eq("Congrats Player 0, you WON!"), eq(DEFAULT_COLOUR));
        verify(gui2,times(1)).drawText(eq(new Position(10, 32)), eq("Do you want to keep playing? [Y/N]"), eq(DEFAULT_COLOUR));

        when(ticTacToe.getGameIsOver()).thenReturn(3);
        GUI gui3 = Mockito.mock(GUI.class);
        viewer.handleEndGame(gui3);
        verify(gui3,times(1)).drawText(eq(new Position(10, 31)), eq("Oh no, it is a TIE GAME! "), eq(DEFAULT_COLOUR));
        verify(gui3,times(1)).drawText(eq(new Position(10, 32)), eq("Do you want to keep playing? [Y/N]"), eq(DEFAULT_COLOUR));
    }
    @Test
    void drawElementsTestHighlightBig0() {
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
    @Test
    void drawMinisXTest() {
        when(ticTacToe.getPlayState()).thenReturn(Arrays.asList(1,1,1,1,1,1,1,1,1));
        ArrayList<Character> squares = new ArrayList<>(Collections.nCopies(81, ' '));
        when(ticTacToe.getContents()).thenReturn(squares);

        viewer.drawMinis(gui);

        int row = 10;
        int col = 8;
        for (int y = 0; y < 3; y ++) {
            for (int x = 0; x < 3; x ++) {
                verify(gui, times(1)).drawText(eq(new Position(row + x * 18, col - 1 + y * 8)),     eq("  \\     /  "), eq("#29C3F4"));
                verify(gui, times(1)).drawText(eq(new Position(row + x * 18, col + y * 8)),         eq("   \\   /   "), eq("#29C3F4"));
                verify(gui, times(1)).drawText(eq(new Position(row + x * 18, col + 1 + y * 8)),     eq("    \\ /    "), eq("#29C3F4"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 2 + y * 8)), eq("      X     "), eq("#29C3F4"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 3 + y * 8)), eq("     / \\   "), eq("#29C3F4"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 4 + y * 8)), eq("    /   \\  "), eq("#29C3F4"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 5 + y * 8)), eq("   /     \\ "), eq("#29C3F4"));
            }
        }
    }
    @Test
    void drawMinisOTest() {
        when(ticTacToe.getPlayState()).thenReturn(Arrays.asList(2,2,2,2,2,2,2,2,2));
        ArrayList<Character> squares = new ArrayList<>(Collections.nCopies(81, ' '));
        when(ticTacToe.getContents()).thenReturn(squares);

        viewer.drawMinis(gui);

        int row = 10;
        int col = 8;
        for (int y = 0; y < 3; y ++) {
            for (int x = 0; x < 3; x ++) {
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col - 1 + y * 8)), eq("   *******  "), eq("#FF0000"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col     + y * 8)), eq("  *       * "), eq("#FF0000"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 1 + y * 8)), eq("  *       * "), eq("#FF0000"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 2 + y * 8)), eq("  *   0   * "), eq("#FF0000"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 3 + y * 8)), eq("  *       * "), eq("#FF0000"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 4 + y * 8)), eq("  *       * "), eq("#FF0000"));
                verify(gui, times(1)).drawText(eq(new Position(row - 1 + x * 18, col + 5 + y * 8)), eq("   *******  "), eq("#FF0000"));
            }
        }
    }
    @Test
    void drawMinisMiniGridTest() {
        when(ticTacToe.getPlayState()).thenReturn(Arrays.asList(0,0,0,0,0,0,0,0,0));
        ArrayList<Character> squares = new ArrayList<>(Collections.nCopies(81, ' '));
        when(ticTacToe.getContents()).thenReturn(squares);

        viewer.drawMinis(gui);

        int row = 10;
        int col = 8;
        for (int y = 0; y < 3; y ++) {
            for (int x = 0; x < 3; x++) {
                for (int i = 0; i < 6; i += 2) {
                    String text = "   |   |    ";
                    Position positionDraw = new Position(row + x * 18, col + y * 8 + i);
                    String o_COLOUR = "#FF0000";
                    String x_COLOUR = "#29C3F4";
                    verify(gui,times(1)).drawWith2Exceptions( eq(positionDraw), eq(text), eq(DEFAULT_COLOUR), eq('X'), eq(x_COLOUR), eq('O'), eq(o_COLOUR));
                    verify(gui,times(1)).drawText(eq(new Position(row + x * 18, col + y * 8 + 1)), eq("---+---+--- "), eq("#FFFFFF"));
                    verify(gui,times(1)).drawText(eq(new Position(row + x * 18, col + y * 8 + 3)), eq("---+---+--- "), eq("#FFFFFF"));

                }
            }
        }
    }
    @Test
    void highlightMiniTest0() {
        viewer.highlightMini(gui, new Position(1, 1),0);

        verify(gui,times(1)).drawText(new Position(1,2), "---+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,1), "|", "#FFFF00");
    }
    @Test
    void highlightMiniTest1() {
        viewer.highlightMini(gui, new Position(1, 1),1);

        verify(gui,times(1)).drawText(new Position(8, 1), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,1), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,2), "+---+", "#FFFF00");
    }
    @Test
    void highlightMiniTest2() {
        viewer.highlightMini(gui, new Position(1, 1),2);

        verify(gui,times(1)).drawText(new Position( 8,1), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position( 8, 2), "+---", "#FFFF00");
    }
    @Test
    void highlightMiniTest3() {
        viewer.highlightMini(gui, new Position(1, 1),3);

        verify(gui,times(1)).drawText(new Position(1, 4), "---+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4, 3), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(1, 2), "---+", "#FFFF00");
    }
    @Test
    void highlightMiniTest4() {
        viewer.highlightMini(gui, new Position(1, 1),4);

        verify(gui,times(1)).drawText(new Position(4,3), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,2), "+---+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(8,3), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,4), "+---+", "#FFFF00");
    }
    @Test
    void highlightMiniTest5() {
        viewer.highlightMini(gui, new Position(1, 1),5);

        verify(gui,times(1)).drawText(new Position(8,3) , "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(8,2), "+---", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(8,4), "+---", "#FFFF00");
    }
    @Test
    void highlightMiniTest6() {
        viewer.highlightMini(gui, new Position(1, 1),6);

        verify(gui,times(1)).drawText(new Position(1,4), "---+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,5), "|", "#FFFF00");
    }
    @Test
    void highlightMiniTest7() {
        viewer.highlightMini(gui, new Position(1, 1),7);

        verify(gui,times(1)).drawText(new Position(4,5), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(4,4), "+---+", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(8,5), "|", "#FFFF00");
    }
    @Test
    void highlightMiniTest8() {
        viewer.highlightMini(gui, new Position(1, 1),8);

        verify(gui,times(1)).drawText(new Position(8,5), "|", "#FFFF00");
        verify(gui,times(1)).drawText(new Position(8,4), "+---", "#FFFF00");
    }
}

