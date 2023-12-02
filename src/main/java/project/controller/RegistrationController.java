package project.controller;

import project.Game;
import project.model.board.Big;
import project.model.board.Player;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.registation.PlayerRegistrator;
import project.states.GameState;
import project.states.MenuState;

import java.io.IOException;

public class RegistrationController extends Controller<PlayerRegistrator> {

    public RegistrationController(PlayerRegistrator registration){super(registration);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case PRESS_X:
                getModel().assignX();
                break;
            case PRESS_O:
                getModel().assignO();
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case SELECT:

                if(getModel().symbolChosen()) {
                    Player player1 = new Player(getModel().getPlayerSymbol(1),0);
                    Player player2 = new Player(getModel().getPlayerSymbol(2),0);
                    game.setState(new GameState(new Big(player1,player2, 0, 0)));
                }
                break;
            default:

        }
    }

}
