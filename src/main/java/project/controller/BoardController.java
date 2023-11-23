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
                getModel().UpperEntry();
                break;
            case DOWN:
                getModel().LowerEntry();
                break;
            case LEFT:
                getModel().LeftEntry();
                break;
            case RIGHT:
                getModel().RightEntry();
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case SELECT:

        }
    }


}
