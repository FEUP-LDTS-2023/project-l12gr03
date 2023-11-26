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

        gui.drawText(new Position(71, 10), String.valueOf(getModel().getp1().getScore()),"#FFFFFF");
        gui.drawText(new Position(71, 11), String.valueOf(getModel().getp2().getScore()),"#FFFFFF");


        switch(getModel().getSelected()) {
            case 0:
                gui.drawText(new Position(10, 14), "------------+", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(22, 7+i), "|", "#FFFF00");
                }
                break;

            case 1:
                gui.drawText(new Position(22, 14), "+-------------+", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(22, 7+i), "|", "#FFFF00");
                    gui.drawText(new Position(36, 7+i), "|", "#FFFF00");
                }
                break;

            case 2:
                gui.drawText(new Position(36, 14), "+------------", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(36, 7+i), "|", "#FFFF00");
                }
                break;

            case 3:
                gui.drawText(new Position(10, 14), "------------+", "#FFFF00");
                gui.drawText(new Position(10, 22), "------------+", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(22, 15+i), "|", "#FFFF00");
                }
                break;

            case 4:
                gui.drawText(new Position(22, 14), "+-------------+", "#FFFF00");
                gui.drawText(new Position(22, 22), "+-------------+", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(22, 15+i), "|", "#FFFF00");
                    gui.drawText(new Position(36, 15+i), "|", "#FFFF00");
                }
                break;

            case 5:
                gui.drawText(new Position(36, 14), "+------------", "#FFFF00");
                gui.drawText(new Position(36, 22), "+------------", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(36, 15+i), "|", "#FFFF00");
                }
                break;

            case 6:
                gui.drawText(new Position(10, 22), "------------+", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(22, 23+i), "|", "#FFFF00");
                }
                break;

            case 7:
                gui.drawText(new Position(22, 22), "+-------------+", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(22, 23+i), "|", "#FFFF00");
                    gui.drawText(new Position(36, 23+i), "|", "#FFFF00");
                }
                break;

            case 8:
                gui.drawText(new Position(36, 22), "+------------", "#FFFF00");
                for (int i = 0; i<7; i++) {
                    gui.drawText(new Position(36, 23+i), "|", "#FFFF00");
                }
                break;
            default:
                gui.drawText(new Position(22,22),String.valueOf(getModel().getSelected()),"#0000FF");

        }

        gui.drawText(new Position(62, 28), "Playtime: " + getModel().findMinTimeFromFile(), "#FFFFFF");
        gui.drawText(new Position(62, 27), "Record time: " + getModel().getFormattedElapsedTime(), "#FFFFFF");


    }

}
