package project.controller;


import project.Music;
import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.board.Big;
import project.model.board.Player;
import project.model.board.TicTacToe;
import project.Game;
import project.states.GameState;
import project.states.MenuState;
import project.model.board.Mini;
import project.viewer.BoardViewer;
import project.viewer.MenuViewer;
import project.viewer.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                Menu menu = new Menu();
                MenuController controller = new MenuController(menu);
                MenuViewer viewer = new MenuViewer(menu);
                game.setState(new MenuState(menu,viewer,controller));

                break;
            case SELECT:
                if (!getModel().getIsPaused()){getModel().select(getModel().getPlayer());}
                break;
            case PRESS_N:
                if (getModel().getGameIsOver() != 0){
                    Menu menu1 = new Menu();
                    MenuController controller1 = new MenuController(menu1);
                    MenuViewer viewer1 = new MenuViewer(menu1);
                    game.setState(new MenuState(menu1,viewer1,controller1));                }
                break;
            case PRESS_Y:
                if (getModel().getGameIsOver() != 0){
                    Player p1 = getModel().getp1(); Player p2 = getModel().getp2();
                    switch (getModel().getGameIsOver()){
                        case 1:
                            if (p1.getSymbol() == 'X' ) { p1.addScore();}
                            else {p2.addScore();}
                            break;
                        case 2:
                            if (p1.getSymbol() == 'O' ) {p1.addScore();}
                            else {p2.addScore();}
                            break;
                        case 3:
                            p1.addScoreTie();
                            p2.addScoreTie();
                            break;
                    }

                    ArrayList<Mini> bigSquaresL = new ArrayList<>();

                    for (int row=0;row<3;row++)
                    {
                        for (int column=0; column<3; column++)
                        {
                            //Since objects are passed by reference the next line CAN NOT be moved to outside the cycle
                            ArrayList<Character> squares = new ArrayList<>(Collections.nCopies(9, ' '));
                            bigSquaresL.add(new Mini(p1,p2,10+18*column,8+8*row, squares));
                        }
                    }

                    Big newGame = new Big(p1,p2, 0, 0,bigSquaresL);
                    Viewer<TicTacToe> viewer1 = new BoardViewer(newGame);
                    Controller<TicTacToe> controller1 = new BoardController(newGame);
                    game.setState(new GameState(newGame,viewer1,controller1));
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
