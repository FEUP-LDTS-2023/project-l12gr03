package project.controller;

import project.Game;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.rules.Rule;
import project.states.MenuState;

import java.io.IOException;

public class RuleController extends Controller<Rule> {

    public RuleController(Rule rule){super (rule);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
        }
    }
}

