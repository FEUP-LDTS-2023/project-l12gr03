package project.controller;

import project.model.rules.Rule;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.registation.PlayerRegistrator;
import project.Game;
import project.states.RegistrationState;
import project.states.RulesState;

import java.io.IOException;

import static java.lang.System.exit;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) {game.setState(null); exit(0);}
                if (getModel().isSelectedStart()) game.setState(new RegistrationState(new PlayerRegistrator()));
                if (getModel().isSelectedRules()) game.setState(new RulesState(new Rule()));
                break;
            default:

        }
    }
}
