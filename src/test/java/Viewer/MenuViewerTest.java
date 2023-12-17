package Viewer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.Position;
import project.viewer.MenuViewer;

import static org.mockito.Mockito.*;

public class MenuViewerTest {

   @Test
    void drawElementsTest(){
        Menu menu = Mockito.mock(Menu.class);
        when(menu.getNumberEntries()).thenReturn(3);
        when(menu.getEntry(0)).thenReturn("A:");
        when(menu.getEntry(1)).thenReturn("B:");
        when(menu.getEntry(2)).thenReturn("C:");
        when(menu.isSelected(2)).thenReturn(true);
        MenuViewer menuViewer = new MenuViewer(menu);
        GUI gui = Mockito.mock(GUI.class);
        menuViewer.drawElements(gui);
        verify(gui,times(4)).drawText(any(Position.class), any(String.class),"#FFFFFF");
    }
}
