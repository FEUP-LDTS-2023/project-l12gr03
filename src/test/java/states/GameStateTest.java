package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.controller.BoardController;
import project.model.board.Board;
import project.states.GameState;
import project.viewer.BoardViewer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameStateTest {

    private Board mockBoard;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        this.mockBoard = mock(Board.class);
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
}
