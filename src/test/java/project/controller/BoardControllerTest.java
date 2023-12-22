package project.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.controller.BoardController;
import project.gui.GUI;
import project.model.board.Player;
import project.model.board.TicTacToe;
import project.states.MenuState;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BoardControllerTest {

    @Test
    void StepQuit() {
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        try {
            boardController.step(mockGame, GUI.ACTION.QUIT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockGame, times(1)).setState(any(MenuState.class));

    }

    @Test
    void StepUp(){
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        try {
            boardController.step(mockGame, GUI.ACTION.UP, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockBoard, times(1)).goUp();
    }

    @Test
    void StepDown(){
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        try {
            boardController.step(mockGame, GUI.ACTION.DOWN, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockBoard, times(1)).goDown();
    }

    @Test
    void StepLeft(){
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        try {
            boardController.step(mockGame, GUI.ACTION.LEFT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockBoard, times(1)).goLeft();
    }

    @Test
    void StepRight(){
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        try {
            boardController.step(mockGame, GUI.ACTION.RIGHT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockBoard, times(1)).goRight();
    }

    @Test
    void StepSelect(){
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);
        when(mockBoard.getPlayer()).thenReturn(mock(Player.class));
        when(mockBoard.getIsPaused()).thenReturn(false);

        try {
            boardController.step(mockGame, GUI.ACTION.SELECT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockBoard, times(1)).getPlayer();
        verify(mockBoard, times(1)).select(any(Player.class));
    }

    @Test
    void press_N_Test() throws IOException {
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);
        when(mockBoard.getGameIsOver()).thenReturn(0);

        boardController.step(mockGame, GUI.ACTION.PRESS_N,0);
        verify(mockGame,times(0)).setState(any());

        when(mockBoard.getGameIsOver()).thenReturn(1);
        boardController.step(mockGame, GUI.ACTION.PRESS_N,0);
        verify(mockGame,times(1)).setState(any());
    }

    @Test
    void press_Y_Test_X_Won() throws IOException {
        TicTacToe mockBoardX = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoardX);
        Game mockGame = mock(Game.class);
        Player mockPlayer1 = Mockito.mock(Player.class);
        Player mockPlayer2 = Mockito.mock(Player.class);
        when(mockBoardX.getGameIsOver()).thenReturn(0);
        when(mockBoardX.getp1()).thenReturn(mockPlayer1);
        when(mockBoardX.getp2()).thenReturn(mockPlayer2);

        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockGame,times(0)).setState(any());

        when(mockBoardX.getGameIsOver()).thenReturn(1);
        when(mockPlayer1.getSymbol()).thenReturn('X');
        when(mockPlayer2.getSymbol()).thenReturn('O');
        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockPlayer1,times(1)).addScore();


        when(mockPlayer1.getSymbol()).thenReturn('O');
        when(mockPlayer2.getSymbol()).thenReturn('X');
        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockPlayer2,times(1)).addScore();
    }

    @Test
    void press_Y_Test_O_Won() throws IOException {
        TicTacToe mockBoardO = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoardO);
        Game mockGame = mock(Game.class);
        Player mockPlayer1 = Mockito.mock(Player.class);
        Player mockPlayer2 = Mockito.mock(Player.class);
        when(mockBoardO.getGameIsOver()).thenReturn(0);
        when(mockBoardO.getp1()).thenReturn(mockPlayer1);
        when(mockBoardO.getp2()).thenReturn(mockPlayer2);

        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockGame,times(0)).setState(any());

        when(mockBoardO.getGameIsOver()).thenReturn(2);
        when(mockPlayer1.getSymbol()).thenReturn('O');
        when(mockPlayer2.getSymbol()).thenReturn('X');
        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockPlayer1,times(1)).addScore();


        when(mockPlayer1.getSymbol()).thenReturn('X');
        when(mockPlayer2.getSymbol()).thenReturn('O');
        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockPlayer2,times(1)).addScore();
    }

    @Test
    void press_Y_Test_Tie() throws IOException {
        TicTacToe mockBoardTie = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoardTie);
        Game mockGame = mock(Game.class);
        Player mockPlayer1 = Mockito.mock(Player.class);
        Player mockPlayer2 = Mockito.mock(Player.class);
        when(mockBoardTie.getGameIsOver()).thenReturn(0);
        when(mockBoardTie.getp1()).thenReturn(mockPlayer1);
        when(mockBoardTie.getp2()).thenReturn(mockPlayer2);

        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockGame,times(0)).setState(any());

        when(mockBoardTie.getGameIsOver()).thenReturn(3);
        boardController.step(mockGame, GUI.ACTION.PRESS_Y,0);
        verify(mockPlayer1,times(1)).addScoreTie();
        verify(mockPlayer2,times(1)).addScoreTie();
    }

    @Test
    void press_P_Test() throws IOException {
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        boardController.step(mockGame, GUI.ACTION.PRESS_P,0);

        verify(mockBoard,times(1)).toggleTimePaused();
    }

    @Test
    void press_M_Test() throws IOException {
        TicTacToe mockBoard = mock(TicTacToe.class);
        BoardController boardController = new BoardController(mockBoard);
        Game mockGame = mock(Game.class);

        boardController.step(mockGame, GUI.ACTION.PRESS_M,0);
        verify(mockBoard,times(1)).stopMusic();
        verify(mockBoard,times(0)).startMusic();

        boardController.step(mockGame, GUI.ACTION.PRESS_M,0);
        verify(mockBoard,times(1)).stopMusic();
        verify(mockBoard,times(1)).startMusic();
    }
}
