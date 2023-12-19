package project.states;

import project.controller.Controller;
import project.controller.RegistrationController;
import project.model.registation.PlayerRegistrator;
import project.viewer.RegistrationView;
import project.viewer.Viewer;

public class RegistrationState extends State<PlayerRegistrator>{

    public RegistrationState(PlayerRegistrator model,
                             Viewer<PlayerRegistrator> viewer,
                             Controller<PlayerRegistrator> controller) {
        super(model,viewer,controller);
    }
    @Override
    public Viewer<PlayerRegistrator> getViewer() {
        return new RegistrationView(getModel());
    }

    @Override
    public Controller<PlayerRegistrator> getController() {
        return new RegistrationController(getModel());
    }

}
