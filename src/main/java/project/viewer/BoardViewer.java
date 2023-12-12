package project.viewer;

import project.gui.GUI;
import project.model.Position;
import project.model.board.TicTacToe;

import java.util.List;

public class BoardViewer extends Viewer<TicTacToe> {

    String X_COLOUR = "#29C3F4";
    String O_COLOUR = "#FF0000";
    String DEFAULT_COLOUR = "#FFFFFF";


    public BoardViewer(TicTacToe board) {
        super(board);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(10, 5), "Player " + getModel().getPlayer().getSymbol() + " it is your turn!", "#FFFFFF");
        for (int i = 0; i < getModel().getNumberLines(); i++) {
            gui.drawText(new Position(10, 7 + i),
                    getModel().getLine(i), "#FFFFFF");
        }

        gui.drawText(new Position(77, 10), String.valueOf(getModel().getp1().getScore()), "#FFFFFF");
        gui.drawText(new Position(77, 11), String.valueOf(getModel().getp2().getScore()), "#FFFFFF");


        switch (getModel().getSelected()) {
            case 0:
                gui.drawText(new Position(10, 14), "--------------+", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(24, 7 + i), "|", "#FFFF00");
                }
                break;

            case 1:
                gui.drawText(new Position(24, 14), "+-----------------+", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(24, 7 + i), "|", "#FFFF00");
                    gui.drawText(new Position(42, 7 + i), "|", "#FFFF00");
                }
                break;

            case 2:
                gui.drawText(new Position(42, 14), "+---------------", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(42, 7 + i), "|", "#FFFF00");
                }
                break;

            case 3:
                gui.drawText(new Position(10, 14), "--------------+", "#FFFF00");
                gui.drawText(new Position(10, 22), "--------------+", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(24, 15 + i), "|", "#FFFF00");
                }
                break;

            case 4:
                gui.drawText(new Position(24, 14), "+-----------------+", "#FFFF00");
                gui.drawText(new Position(24, 22), "+-----------------+", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(24, 15 + i), "|", "#FFFF00");
                    gui.drawText(new Position(42, 15 + i), "|", "#FFFF00");
                }
                break;

            case 5:
                gui.drawText(new Position(42, 14), "+---------------", "#FFFF00");
                gui.drawText(new Position(42, 22), "+---------------", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(42, 15 + i), "|", "#FFFF00");
                }
                break;

            case 6:
                gui.drawText(new Position(10, 22), "--------------+", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(24, 23 + i), "|", "#FFFF00");
                }
                break;

            case 7:
                gui.drawText(new Position(24, 22), "+-----------------+", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(24, 23 + i), "|", "#FFFF00");
                    gui.drawText(new Position(42, 23 + i), "|", "#FFFF00");
                }
                break;

            case 8:
                gui.drawText(new Position(42, 22), "+---------------", "#FFFF00");
                for (int i = 0; i < 7; i++) {
                    gui.drawText(new Position(42, 23 + i), "|", "#FFFF00");
                }
                break;
            default:
                gui.drawText(new Position(22, 22), String.valueOf(getModel().getSelected()), "#0000FF");


        }

        gui.drawText(new Position(68, 28), "Record time: " + getModel().findMinTimeFromFile(), "#FFFFFF");
        gui.drawText(new Position(68, 27), "Playtime: " + getModel().getFormattedElapsedTime(), "#FFFFFF");


        List<Integer> states = getModel().getPlayState();

        List<Character> contents = getModel().getContents();

        int row = 10;
        int col = 8;
        int index = 0;
        for (int y = 0; y < 3; y ++) {
            for (int x = 0; x < 3; x ++) {
                switch (states.get(index)) {
                    case 0:  // jogo está a acontecer
                        for (int i = 0; i < 6; i += 2) {
                            int ContentsIndex = 27*y + 9*x + 3 * i/2;
                            String text = " " + contents.get(ContentsIndex) + " | " + contents.get(ContentsIndex + 1) + " | " + contents.get(ContentsIndex + 2) + "  ";
                            //gui.drawText(new Position(row + x * 18, col + y * 8 + i), text, "#FFFFFF");
                            Position positionDraw = new Position(row + x * 18, col + y * 8 + i);
                            gui.drawWith2Exceptions(positionDraw, text, DEFAULT_COLOUR, 'X',X_COLOUR,'O',O_COLOUR);
                        }
                        for (int i = 1; i < 4; i += 2) {
                            gui.drawText(new Position(row + x * 18, col + y * 8 + i), "---+---+--- ", "#FFFFFF");
                        }
                        break;
                    case 1: // jogo acabou, o jogador 1
                        gui.drawText(new Position(row + x * 18, col - 1 + y * 8),     "  \\     /  ", "#29C3F4");
                        gui.drawText(new Position(row + x * 18, col + y * 8),         "   \\   /   ", "#29C3F4");
                        gui.drawText(new Position(row + x * 18, col + 1 + y * 8),     "    \\ /    ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 2 + y * 8), "      X     ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 3 + y * 8), "     / \\   ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 4 + y * 8), "    /   \\  ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 5 + y * 8), "   /     \\ ", "#29C3F4");
                        break;
                    case 2: // jogo acabou, o jogador 2 ganhou
                        gui.drawText(new Position(row - 1 + x * 18, col - 1 + y * 8), "   *******  ", "#FF0000");
                        gui.drawText(new Position(row - 1+ x * 18, col + y * 8),      "  *       * ", "#FF0000");
                        gui.drawText(new Position(row - 1 + x * 18, col + 1 + y * 8), "  *       * ", "#FF0000");
                        gui.drawText(new Position(row - 1 + x * 18, col + 2 + y * 8), "  *   0   * ", "#FF0000");
                        gui.drawText(new Position(row - 1 + x * 18, col + 3 + y * 8), "  *       * ", "#FF0000");
                        gui.drawText(new Position(row - 1 + x * 18, col + 4 + y * 8), "  *       * ", "#FF0000");
                        gui.drawText(new Position(row - 1 + x * 18, col + 5 + y * 8), "   *******  ", "#FF0000");
                        break;
                    case 3: // jogo acabou, empate
                        break;
                }
                index++;
            }
        }

        highlightMini(gui,getModel().getMinPosition(),getModel().getInnerSelected());
    }

    private void highlightMini(GUI gui, Position upperCorner, int MiniSelected){
        switch (MiniSelected){
            case 0:
                gui.drawText(new Position(upperCorner.getX()+3,upperCorner.getY()), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX(),upperCorner.getY() + 1), "---+", "#FFFF00");
                break;
            case 1:
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY()), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY()), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 1), "+---+", "#FFFF00");
                break;
            case 2:
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY()), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 1), "+---", "#FFFF00");
                break;
            case 3:
                gui.drawText(new Position(upperCorner.getX(),upperCorner.getY() + 3), "---+", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 2), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX(),upperCorner.getY() + 1), "---+", "#FFFF00");
                break;
            case 4:
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 2), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 1), "+---+", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 2 ), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 3), "+---+", "#FFFF00");
                break;
            case 5:
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 2) , "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 1), "+---", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 3), "+---", "#FFFF00");
                break;
            case 6:
                gui.drawText(new Position(upperCorner.getX(),upperCorner.getY() + 3), "---+", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 4), "|", "#FFFF00");
                break;
            case 7:
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 4), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 3,upperCorner.getY() + 3), "+---+", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 4), "|", "#FFFF00");
                break;
            case 8:
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 4), "|", "#FFFF00");
                gui.drawText(new Position(upperCorner.getX() + 7,upperCorner.getY() + 3), "+---", "#FFFF00");

            // fall through

            default:
                break;
        }
    }
}
