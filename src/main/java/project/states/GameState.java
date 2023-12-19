package project.states;

import project.controller.BoardController;
import project.controller.Controller;
import project.model.board.TicTacToe;
import project.viewer.BoardViewer;
import project.viewer.Viewer;

public class GameState extends State<TicTacToe>{

    public GameState(TicTacToe board, Viewer<TicTacToe> viewer, Controller<TicTacToe> controller)
    {
        super(board,viewer,controller);
    }

    @Override
    public Viewer<TicTacToe> getViewer() {
        return new BoardViewer(getModel());
    }

    @Override
    public Controller<TicTacToe> getController() {
        return new BoardController(getModel());
    }
}
