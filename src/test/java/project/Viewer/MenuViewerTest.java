package project.Viewer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.Position;
import project.viewer.MenuViewer;

import static org.mockito.Mockito.*;

public class MenuViewerTest {
    @Test
    void drawElementsTest() {
        Menu menu = Mockito.mock(Menu.class);
        MenuViewer menuViewer = new MenuViewer(menu);
        GUI gui = Mockito.mock(GUI.class);

        when(menu.getNumberEntries()).thenReturn(2);
        when(menu.getEntry(0)).thenReturn("Entry 1");
        when(menu.getEntry(1)).thenReturn("Entry 2");
        when(menu.isSelected(0)).thenReturn(false);
        when(menu.isSelected(1)).thenReturn(true);
        menuViewer.drawElements(gui);
        verify(gui,times(1)).drawText(new Position(10,5),"Menu","#FFFFFF");
        verify(gui,times(1)).drawText(new Position(10,7),"Entry 1","#FFFFFF");
        verify(gui,times(1)).drawText(new Position(10,8),"Entry 2","#FFD700");



    }
}
