package project.controller;


import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.board.Big;
import project.model.board.TicTacToe;
import project.Game;
import project.states.GameState;
import project.states.MenuState;
import project.model.board.Mini;

import java.io.IOException;

public class BoardController extends Controller<TicTacToe> {

    public BoardController(TicTacToe board) { super(board);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:

                if (!getModel().getIsPaused()){getModel().goUp();}
                break;
            case DOWN:

                if (!getModel().getIsPaused()){getModel().goDown();}
                break;
            case LEFT:

                if (!getModel().getIsPaused()){getModel().goLeft();}
                break;
            case RIGHT:

                if (!getModel().getIsPaused()){getModel().goRight();}
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case SELECT:
                if (!getModel().getIsPaused()){getModel().select(getModel().getPlayer());}
                break;
            case PRESS_N:
                if (getModel().getGameIsOver() != 0){game.setState(new MenuState(new Menu()));}
                break;
            case PRESS_Y:
                if (getModel().getGameIsOver() != 0){
                    switch (getModel().getGameIsOver()){
                        case 1:
                            if (getModel().getp1().getSymbol() == 'X' ) { getModel().getp1().addScore();}
                            else {getModel().getp2().addScore();}
                            break;
                        case 2:
                            if (getModel().getp1().getSymbol() == 'O' ) { getModel().getp1().addScore();}
                            else {getModel().getp2().addScore();}
                            break;
                        case 3:
                            getModel().getp1().addScoreTie();
                            getModel().getp2().addScoreTie();
                            break;
                    }
                    game.setState(new GameState(new Big(getModel().getp1(),getModel().getp2(), 0, 0)));
                    getModel().resetElapsedTime();
                }
                break;
            case PRESS_P:
                getModel().toggleTimePaused();
                break;
            default:
                break;

        }
    }


}
