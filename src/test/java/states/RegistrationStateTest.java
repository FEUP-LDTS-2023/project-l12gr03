package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.controller.RegistrationController;
import project.model.registation.PlayerRegistrator;
import project.states.RegistrationState;
import project.viewer.RegistrationView;

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
}
