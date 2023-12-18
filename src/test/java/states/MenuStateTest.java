package states;

import com.googlecode.lanterna.terminal.swing.TerminalScrollController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import project.Game;
import project.controller.MenuController;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.states.GameState;
import project.states.MenuState;
import project.states.RulesState;
import project.states.State;
import project.viewer.MenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuStateTest {

    private Menu mockMenu;
    private MenuState menuState;

    @BeforeEach
    void setUp() {
        this.mockMenu = mock(Menu.class);

        MenuViewer viewer = new MenuViewer(mockMenu);
        MenuController controller = new MenuController(mockMenu);
        this.menuState = new MenuState(mockMenu,viewer,controller);
    }

    @Spy
    MenuState menuSpy;

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

    @Test
    void stepTest() throws IOException {
        MenuViewer mockMenuViewer = Mockito.mock(MenuViewer.class);
        MenuController mockMenuController = Mockito.mock(MenuController.class);



        when(menuSpy.getViewer()).thenReturn(mockMenuViewer);
        when(menuSpy.getController()).thenReturn(mockMenuController);


        menuSpy = Mockito.spy(new MenuState(mockMenu,mockMenuViewer,mockMenuController));
        Game mockGame = Mockito.mock(Game.class);
        GUI mockGui = Mockito.mock(GUI.class);
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.UP);


        menuSpy.step(mockGame,mockGui,0);

        verify(mockGui,times(1)).getNextAction();
        verify(mockMenuController,times(1)).step(mockGame,GUI.ACTION.UP,0);
        verify(mockMenuViewer,times(1)).draw(mockGui);
    }
}
