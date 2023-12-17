package Viewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.Position;
import project.model.rules.Rule;
import project.viewer.RuleViewer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RuleViewerTest {

    @Test
    void drawElementsTest(){
        GUI mockGUI = Mockito.mock(GUI.class);
        Rule mockRule = Mockito.mock(Rule.class);
        when(mockRule.getNumberLines()).thenReturn(3);
        when(mockRule.getLine(0)).thenReturn("Line 0");
        when(mockRule.getLine(1)).thenReturn("Line 1");
        when(mockRule.getLine(2)).thenReturn("Line 2");
        RuleViewer ruleViewer = new RuleViewer(mockRule);
        ruleViewer.drawElements(mockGUI);
        verify(mockGUI,times(3)).drawText(any(Position.class),any(String.class),"#FFFFFF");
        Assertions.assertEquals("Line 0",mockRule.getLine(0));
    }
}
