package project.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.controller.RegistrationController;
import project.gui.GUI;
import project.model.registation.PlayerRegistrator;
import project.viewer.RegistrationView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationStateTest {

    private PlayerRegistrator mockPlayerRegistrator;
    private RegistrationState registrationState;

    @BeforeEach
    void setUp() {
        this.mockPlayerRegistrator = mock(PlayerRegistrator.class);

        RegistrationView view = mock(RegistrationView.class);
        RegistrationController controller = mock(RegistrationController.class);
        this.registrationState = new RegistrationState(mockPlayerRegistrator,view,controller);
    }

    @Test
    void testGetViewer() {
        assertTrue(registrationState.getViewer() instanceof RegistrationView);
        assertEquals(mockPlayerRegistrator, registrationState.getViewer().getModel());
    }

    @Test
    void testGetController() {
        assertTrue(registrationState.getController() instanceof RegistrationController);
        assertEquals(mockPlayerRegistrator, registrationState.getController().getModel());
    }

    @Test
    void testInitialization() {
        assertEquals(mockPlayerRegistrator, registrationState.getModel());
    }

    @Test
    void stepTest() throws IOException {
        RegistrationController controller = Mockito.mock(RegistrationController.class);
        RegistrationView viewer = Mockito.mock(RegistrationView.class);
        RegistrationState registrationStateSpy = Mockito.spy(new RegistrationState(mockPlayerRegistrator,viewer,controller));

        Game mockGame = Mockito.mock(Game.class);
        GUI mockGui = Mockito.mock(GUI.class);
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.UP);

        registrationStateSpy.step(mockGame,mockGui,0);

        verify(mockGui,times(1)).getNextAction();
        verify(controller,times(1)).step(mockGame,GUI.ACTION.UP,0);
        verify(viewer,times(1)).draw(mockGui);
    }
}
