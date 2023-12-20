package project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Game;
import project.controller.RegistrationController;
import project.gui.GUI;
import project.model.registation.PlayerRegistrator;
import project.states.GameState;
import project.states.MenuState;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        registrationController = new RegistrationController(new PlayerRegistrator());
    }

    @Test
    void testStepPressX() {

        assertDoesNotThrow(() -> registrationController.step(mock(Game.class), GUI.ACTION.PRESS_X, 100));

        assertTrue(registrationController.getModel().symbolChosen());
        assertEquals('X', registrationController.getModel().getPlayerSymbol(1));
        assertEquals("Player 1, pick crosses or circles[X/O]: X", registrationController.getModel().getMessage());

    }

    @Test
    void testStepPressO() {

        assertDoesNotThrow(() -> registrationController.step(mock(Game.class), GUI.ACTION.PRESS_O, 100));

        assertTrue(registrationController.getModel().symbolChosen());
        assertEquals('O', registrationController.getModel().getPlayerSymbol(1));
        assertEquals("Player 1, pick crosses or circles[X/O]: O", registrationController.getModel().getMessage());

    }

    @Test
    void testStepQuit() {

        Game mockGame = mock(Game.class);

        assertDoesNotThrow(() -> registrationController.step(mockGame, GUI.ACTION.QUIT, 100));
        verify(mockGame, times(1)).setState(any(MenuState.class));

    }

    @Test
    void testStepSelectWithSymbolsChosen() {
        Game mockGame = mock(Game.class);

        registrationController.getModel().assignX();
        registrationController.getModel().assignO();

        assertDoesNotThrow(() -> registrationController.step(mockGame, GUI.ACTION.SELECT, 100));


        verify(mockGame, times(1)).setState(any(GameState.class));
    }
}
