package project.states;

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
    private MenuViewer viewer;
    private MenuController controller;

    @BeforeEach
    void setUp() {
        this.mockMenu = mock(Menu.class);

        this.viewer = Mockito.mock(MenuViewer.class);
        this.controller = Mockito.mock(MenuController.class);
        this.menuState = new MenuState(mockMenu,viewer,controller);
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

    @Test
    void stepTest() throws IOException {
        MenuState menuSpy = Mockito.spy(new MenuState(mockMenu,viewer,controller));


        when(menuSpy.getViewer()).thenReturn(viewer);
        when(menuSpy.getController()).thenReturn(controller);


        Game mockGame = Mockito.mock(Game.class);
        GUI mockGui = Mockito.mock(GUI.class);
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.UP);


        menuSpy.step(mockGame,mockGui,0);

        verify(mockGui,times(1)).getNextAction();
        verify(controller,times(1)).step(mockGame,GUI.ACTION.UP,0);
        verify(viewer,times(1)).draw(mockGui);
    }
}
