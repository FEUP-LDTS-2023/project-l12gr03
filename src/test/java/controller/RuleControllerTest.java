package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Game;
import project.controller.RuleController;
import project.gui.GUI;
import project.model.rules.Rule;
import project.states.MenuState;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RuleControllerTest {

    private RuleController ruleController;

    @BeforeEach
    void setUp() {
      ruleController = new RuleController(mock(Rule.class));
    }

    @Test
    void testStepQuit(){

        Game mockGame = mock(Game.class);
        assertDoesNotThrow(() -> ruleController.step(mockGame, GUI.ACTION.QUIT, 100));
        verify(mockGame, times(1)).setState(any(MenuState.class));

    }

}
