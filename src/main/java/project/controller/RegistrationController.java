package project.controller;

import project.Game;
import project.model.board.Big;
import project.model.board.Mini;
import project.model.board.Player;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.registation.PlayerRegistrator;
import project.states.GameState;
import project.states.MenuState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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


                    ArrayList<Mini> bigSquaresL = new ArrayList<>();

                    for (int row=0;row<3;row++)
                    {
                        for (int column=0; column<3; column++)
                        {
                            //Since objects are passed by reference the next line CAN NOT be moved to outside the cycle
                            ArrayList<Character> squares = new ArrayList<>(Collections.nCopies(9, ' '));
                            bigSquaresL.add(new Mini(player1,player2,10+18*column,8+8*row, squares));
                        }
                    }
                    game.setState(new GameState(new Big(player1,player2, 0, 0,bigSquaresL)));
                }
                break;
            default:

        }
    }

}
