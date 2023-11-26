package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.controller.Controller;
import project.controller.RuleController;
import project.model.rules.Rule;
import project.states.RulesState;
import project.viewer.RuleViewer;
import project.viewer.Viewer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RulesStateTest {

    private Rule mockRule;
    private RulesState rulesState;

    @BeforeEach
    void setUp() {
        this.mockRule = mock(Rule.class);
        this.rulesState = new RulesState(mockRule);
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
}
