package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.controller.MenuController;
import project.model.Menu.Menu;
import project.states.MenuState;
import project.viewer.MenuViewer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuStateTest {

    private Menu mockMenu;
    private MenuState menuState;

    @BeforeEach
    void setUp() {
        this.mockMenu = mock(Menu.class);
        this.menuState = new MenuState(mockMenu);
    }

    @Test
    void testGetViewer() {
        assertTrue(menuState.getViewer() instanceof MenuViewer);
        assertEquals(mockMenu, menuState.getViewer().getModel());
    }

    @Test
    void testGetController() {
        assertTrue(menuState.getController() instanceof MenuController);
        assertEquals(mockMenu, menuState.getController().getModel());
    }

    @Test
    void testInitialization() {
        assertEquals(mockMenu, menuState.getModel());
    }
}
