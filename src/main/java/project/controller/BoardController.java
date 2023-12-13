package project.controller;


import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.board.Big;
import project.model.board.TicTacToe;
import project.Game;
import project.states.MenuState;
import project.model.board.Mini;

import java.io.IOException;

public class BoardController extends Controller<TicTacToe> {

    public BoardController(TicTacToe board) { super(board);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:

                getModel().goUp();
                break;
            case DOWN:

                getModel().goDown();
                break;
            case LEFT:

                getModel().goLeft();
                break;
            case RIGHT:

                getModel().goRight();
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case SELECT:
                getModel().select(getModel().getPlayer());
                getModel().setGameState();
                break;

            default:
                break;

        }
    }


}
