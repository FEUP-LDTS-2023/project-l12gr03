package project.Viewer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.gui.LanternaGUI;
import project.model.Position;
import project.model.rules.Rule;
import project.viewer.RuleViewer;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class RuleViewerTest {

    @Test
    void drawTest() throws IOException {
        Rule rule = Mockito.mock(Rule.class);
        RuleViewer ruleViewer = Mockito.spy(new RuleViewer(rule));
        GUI gui = Mockito.mock(LanternaGUI.class);
        doNothing().when(ruleViewer).drawElements(gui);

        ruleViewer.draw(gui);
        verify(gui,times(1)).clear();
        verify(ruleViewer,times(1)).drawElements(gui);
        verify(gui,times(1)).refresh();

    }

    @Test
    void drawElementsTest(){
        Rule rule = Mockito.mock(Rule.class);
        RuleViewer ruleViewer = new RuleViewer(rule);
        when(rule.getNumberLines()).thenReturn(3);
        when(rule.getLine(0)).thenReturn("1");
        when(rule.getLine(1)).thenReturn("2");
        when(rule.getLine(2)).thenReturn("3");

        GUI gui = Mockito.mock(LanternaGUI.class);
        ruleViewer.drawElements(gui);
        verify(gui,times(1)).drawText(new Position(10,7),"1","#FFFFFF");
        verify(gui,times(1)).drawText(new Position(10,8),"2","#FFFFFF");
        verify(gui,times(1)).drawText(new Position(10,9),"3","#FFFFFF");
    }
}
