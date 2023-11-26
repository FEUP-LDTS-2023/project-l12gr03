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
        gui.drawText(new Position(10, 5),  "Player " + getModel().getPlayer().getSymbol() + " it is your turn!", "#FFFFFF");
        for (int i = 0; i < getModel().getNumberLines(); i++){
            gui.drawText(new Position(10, 7 + i),
                    getModel().getLine(i), "#FFFFFF");}

        gui.drawText(new Position(73, 10), String.valueOf(getModel().getp1().getScore()),"#FFFFFF");
        gui.drawText(new Position(73, 11), String.valueOf(getModel().getp2().getScore()),"#FFFFFF");
    }

}
