package project.Viewer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.Position;
import project.model.registation.PlayerRegistrator;
import project.viewer.RegistrationView;

import static org.mockito.Mockito.*;

public class RegistrationViewer {

    @Test
    void drawElementsTest() {
        PlayerRegistrator registrator = Mockito.mock(PlayerRegistrator.class);
        RegistrationView view = new RegistrationView(registrator);
        when(registrator.getMessage()).thenReturn("Message");
        GUI gui = Mockito.mock(GUI.class);

        view.drawElements(gui);

        verify(gui,times(1)).drawText(new Position(10,5),"Message","#FFFFFF");


    }

}
