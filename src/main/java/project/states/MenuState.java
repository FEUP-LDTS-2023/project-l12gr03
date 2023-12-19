package project.states;

import project.controller.Controller;
import project.controller.MenuController;
import project.model.Menu.Menu;
import project.viewer.MenuViewer;
import project.viewer.Viewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model, Viewer<Menu> viewer, Controller<Menu> controller) {
        super(model,viewer,controller);
    }

    @Override
    public Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    public Controller<Menu> getController() {
        return new MenuController(getModel());
    }

}