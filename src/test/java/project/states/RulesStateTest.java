package project.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.controller.Controller;
import project.controller.RegistrationController;
import project.controller.RuleController;
import project.gui.GUI;
import project.model.rules.Rule;
import project.states.RegistrationState;
import project.states.RulesState;
import project.viewer.RegistrationView;
import project.viewer.RuleViewer;
import project.viewer.Viewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RulesStateTest {

    private Rule mockRule;
    private RulesState rulesState;

    @BeforeEach
    void setUp() {
        this.mockRule = mock(Rule.class);

        RuleViewer viewer = mock(RuleViewer.class);
        RuleController controller = mock(RuleController.class);

        this.rulesState = new RulesState(mockRule,viewer,controller);
    }

    @Test
    void testGetViewer() {
        assertTrue(rulesState.getViewer() instanceof RuleViewer);
        assertEquals(mockRule, rulesState.getViewer().getModel());
    }

    @Test
    void testGetController() {
        assertTrue(rulesState.getController() instanceof RuleController);
        assertEquals(mockRule, rulesState.getController().getModel());
    }

    @Test
    void testInitialization() {
        assertEquals(mockRule, rulesState.getModel());
    }

    @Test
    void stepTest() throws IOException {
        RuleController controller = Mockito.mock(RuleController.class);
        RuleViewer viewer = Mockito.mock(RuleViewer.class);
        RulesState rulesStateSpy = Mockito.spy(new RulesState(mockRule,viewer,controller));

        Game mockGame = Mockito.mock(Game.class);
        GUI mockGui = Mockito.mock(GUI.class);
        when(mockGui.getNextAction()).thenReturn(GUI.ACTION.UP);

        rulesStateSpy.step(mockGame,mockGui,0);

        verify(mockGui,times(1)).getNextAction();
        verify(controller,times(1)).step(mockGame,GUI.ACTION.UP,0);
        verify(viewer,times(1)).draw(mockGui);
    }
}
