package project.viewer;

import project.gui.GUI;
import project.model.Position;
import project.model.registation.PlayerRegistrator;

public class RegistrationView extends Viewer<PlayerRegistrator> {

    public RegistrationView(PlayerRegistrator registration) {super(registration);}

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(
                new Position(10,5),
                getModel().getMessage(),"#FFFFFF");
    }

}
