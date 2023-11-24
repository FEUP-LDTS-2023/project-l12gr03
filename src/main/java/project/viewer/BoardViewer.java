package project.viewer;

import project.gui.GUI;
import project.model.Position;
import project.model.board.Board;

public class BoardViewer extends Viewer<Board>{
    public BoardViewer(Board board) {
        super(board);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(10,7),getModel().dummy.toString(),"#FFD700");

    }

}
