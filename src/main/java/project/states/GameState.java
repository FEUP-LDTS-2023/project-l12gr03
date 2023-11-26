package project.states;

import project.controller.BoardController;
import project.controller.Controller;
import project.model.board.Board;
import project.viewer.BoardViewer;
import project.viewer.Viewer;

public class GameState extends State<Board>{

    public GameState(Board board) {
        super(board);
    }

    @Override
    public Viewer<Board> getViewer() {
        return new BoardViewer(getModel());
    }

    @Override
    public Controller<Board> getController() {
        return new BoardController(getModel());
    }
}
