package project.controller;


import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.board.Board;
import project.Game;
import project.states.MenuState;

import java.io.IOException;

public class BoardController extends Controller<Board> {

    public BoardController(Board board) { super(board);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().getUp();
                getModel().goUp();
                break;
            case DOWN:
                getModel().getDown();
                getModel().goDown();
                break;
            case LEFT:
                getModel().getLeft();
                getModel().goLeft();
                break;
            case RIGHT:
                getModel().getRight();
                getModel().goRight();
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case SELECT:

        }
    }


}
