package project.controller;

import project.model.rules.Rule;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.registation.PlayerRegistrator;
import project.Game;
import project.states.RegistrationState;
import project.states.RulesState;
import project.viewer.RegistrationView;
import project.viewer.RuleViewer;
import project.viewer.Viewer;
import java.io.IOException;


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
                if (getModel().isSelectedExit()) {game.setState(null);}

                if (getModel().isSelectedStart()) {
                    PlayerRegistrator registrator = new PlayerRegistrator();
                    Viewer<PlayerRegistrator> viewer = new RegistrationView(registrator);
                    Controller<PlayerRegistrator> controller = new RegistrationController(registrator);

                    game.setState(new RegistrationState(registrator,viewer,controller));
                }

                if (getModel().isSelectedRules()) {
                    Rule rule = new Rule();
                    Viewer<Rule> viewer = new RuleViewer(rule);
                    Controller<Rule> controller = new RuleController(rule);
                    game.setState(new RulesState(rule,viewer,controller));
                }
                break;
            default:

        }
    }
}
