package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.controller.BoardController;
import project.gui.GUI;
import project.model.board.TicTacToe;
import project.states.GameState;
import project.viewer.BoardViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameStateTest {

    private TicTacToe mockBoard;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        this.mockBoard = mock(TicTacToe.class);
        this.gameState = new GameState(mockBoard);
    }

    @Test
    void testGetViewer() {
        assertTrue(gameState.getViewer() instanceof BoardViewer);
        assertEquals(mockBoard, gameState.getViewer().getModel());
    }

    @Test
    void testGetController() {
        assertTrue(gameState.getController() instanceof BoardController);
        assertEquals(mockBoard, gameState.getController().getModel());
    }

    @Test
    void testInitialization() {
        assertEquals(mockBoard, gameState.getModel());
    }

    @Test
    void testStep() throws IOException {
        Game game = Mockito.mock(Game.class);
        GUI gui = Mockito.mock(GUI.class);
        when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN);

        gameState = Mockito.mock(GameState.class);
        gameState.step(game,gui,100);
        //verify(gui,times(1)).getNextAction();
        //verify(gameState.)

    }
}
