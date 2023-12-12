package controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.Game;
import project.controller.MenuController;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.states.RegistrationState;
import project.states.RulesState;

import static java.lang.System.exit;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuControllerTest {

    @Test
    void testStepUp() {
        Menu mockMenu = mock(Menu.class);
        MenuController menuController = new MenuController(mockMenu);

        Game mockGame = mock(Game.class);

        try {
            menuController.step(mockGame, GUI.ACTION.UP, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockMenu, times(1)).previousEntry();

    }

    @Test
    void testStepDown() {
        Menu mockMenu = mock(Menu.class);
        MenuController menuController = new MenuController(mockMenu);

        Game mockGame = mock(Game.class);

        try {
            menuController.step(mockGame, GUI.ACTION.DOWN, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockMenu, times(1)).nextEntry();

    }

    @Test
    void testStepSelectExit() {
        Menu mockMenu = mock(Menu.class);
        when(mockMenu.isSelectedExit()).thenReturn(true);
        when(mockMenu.isSelectedRules()).thenReturn(false);
        when(mockMenu.isSelectedStart()).thenReturn(false);

        MenuController menuController = new MenuController(mockMenu);
        Game mockGame = mock(Game.class);

        try {
            menuController.step(mockGame, GUI.ACTION.SELECT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
        verify(mockGame, times(1)).setState(null);

    }

    @Test
    void testStepSelectStart() {
        Menu mockMenu = mock(Menu.class);
        when(mockMenu.isSelectedStart()).thenReturn(true);

        MenuController menuController = new MenuController(mockMenu);
        Game mockGame = mock(Game.class);

        try {
            menuController.step(mockGame, GUI.ACTION.SELECT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockGame, times(1)).setState(any(RegistrationState.class));

    }

    @Test
    void testStepSelectRules() {
        Menu mockMenu = mock(Menu.class);
        when(mockMenu.isSelectedRules()).thenReturn(true);

        MenuController menuController = new MenuController(mockMenu);
        Game mockGame = mock(Game.class);

        try {
            menuController.step(mockGame, GUI.ACTION.SELECT, 100);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }

        verify(mockGame, times(1)).setState(any(RulesState.class));

    }
}
