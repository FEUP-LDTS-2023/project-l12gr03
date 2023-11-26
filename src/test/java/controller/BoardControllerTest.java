package controller;

import org.junit.jupiter.api.Test;
import project.Game;
import project.controller.BoardController;
import project.gui.GUI;
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
}
