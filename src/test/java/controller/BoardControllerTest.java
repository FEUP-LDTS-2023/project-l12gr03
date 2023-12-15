package controller;

import org.junit.jupiter.api.Test;
import project.Game;
import project.controller.BoardController;
import project.gui.GUI;
import project.model.board.Player;
import project.model.board.TicTacToe;
import project.states.MenuState;

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

        try {
            boardController.step(mockGame, GUI.ACTION.SELECT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockBoard, times(1)).getPlayer();
        verify(mockBoard, times(1)).select(any(Player.class));
        verify(mockBoard, times(1)).setMiniGameState();
        //TODO beware pit tests
        //TODO BIG CODE SMELL
    }
}
