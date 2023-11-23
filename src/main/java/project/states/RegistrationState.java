package project.states;

import project.controller.Controller;
import project.controller.RegistrationController;
import project.model.registation.PlayerRegistrator;
import project.viewer.RegistrationView;
import project.viewer.Viewer;

public class RegistrationState extends State<PlayerRegistrator>{

    public RegistrationState(PlayerRegistrator model) {super(model);}
    @Override
    protected Viewer<PlayerRegistrator> getViewer() {
        return new RegistrationView(getModel());
    }

    @Override
    protected Controller<PlayerRegistrator> getController() {
        return new RegistrationController(getModel());
    }

}
