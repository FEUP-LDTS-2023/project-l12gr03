package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.controller.BoardController;
import project.controller.RegistrationController;
import project.gui.GUI;
import project.model.board.TicTacToe;
import project.states.GameState;
import project.states.RegistrationState;
import project.viewer.BoardViewer;
import project.viewer.RegistrationView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameStateTest {

    private TicTacToe mockBoard;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        this.mockBoard = mock(TicTacToe.class);
        BoardViewer viewer = mock(BoardViewer.class);
        BoardController controller = mock(BoardController.class);
        this.gameState = new GameState(mockBoard,viewer,controller);
    }


    @Test
    void testGetViewer() {
        //assertTrue(gameState.getViewer() instanceof BoardViewer);
        //verify(gameState.getViewer()).equals(assertInstanceOf(BoardViewer));
        assertEquals(mockBoard, gameState.getViewer().getModel());
    }

    @Test
    void testGetController() {
        //assertTrue(gameState.getController() instanceof BoardController);
        assertEquals(mockBoard, gameState.getController().getModel());
    }

    @Test
    void testInitialization() {
        //assertEquals(mockBoard, gameState.getModel());
    }

    @Test
    void testStep() throws IOException {
        BoardController controller = Mockito.mock(BoardController.class);
        BoardViewer viewer = Mockito.mock(BoardViewer.class);
        GameState gameStateSpy = Mockito.spy(new GameState(mockBoard,viewer,controller));

        Game mockGame = Mockito.mock(Game.class);
        GUI mockGui = Mockito.mock(GUI.class);
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.UP);

        gameStateSpy.step(mockGame,mockGui,0);

        verify(mockGui,times(1)).getNextAction();
        verify(controller,times(1)).step(mockGame,GUI.ACTION.UP,0);
        verify(viewer,times(1)).draw(mockGui);
    }
}
