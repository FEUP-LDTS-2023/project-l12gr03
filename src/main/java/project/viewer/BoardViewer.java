package project.viewer;

import project.gui.GUI;
import project.model.Position;
import project.model.board.Mini;
import project.model.board.TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class BoardViewer extends Viewer<TicTacToe> {
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
        int row = 10;
        int col = 8;
        int index = 0;
        for (int y = 0; y < 3; y ++) {
            for (int x = 0; x < 3; x ++) {
                switch (states.get(index)) {
                    case 0:  // jogo está a acontecer
                        for (int i = 0; i < 6; i += 2) {
                            gui.drawText(new Position(row + x * 18, col + y * 8 + i), "   |   |    ", "#FFFFFF");
                        }
                        for (int i = 1; i < 4; i += 2) {
                            gui.drawText(new Position(row + x * 18, col + y * 8 + i), "---+---+--- ", "#FFFFFF");
                        }
                        if (index == currentMini && isSelectingMini){
                            switch(getModel().getSelected()) {
                                case 0:
                                    gui.drawText(new Position(row + x * 18, col + y * 8), "   |", "#FFFF00");
                                    gui.drawText(new Position(row + x * 18, col + 1 + y * 8), "---+", "#FFFF00");
                                    break;
                                case 1:
                                    gui.drawText(new Position(row + 3 + x * 18, col + y * 8), "|   |", "#FFFF00");
                                    gui.drawText(new Position(row + 3 + x * 18, col + 1 + y * 8), "+---+", "#FFFF00");
                                    break;
                                case 2:
                                    gui.drawText(new Position(row + 7 + x * 18, col + y * 8), "|   ", "#FFFF00");
                                    gui.drawText(new Position(row + 7 + x * 18, col + 1 + y * 8), "+---", "#FFFF00");
                                    break;
                                case 3:
                                    gui.drawText(new Position(row + x * 18, col + 1 + y * 8), "---+", "#FFFF00");
                                    gui.drawText(new Position(row + x * 18, col + 2 + y * 8), "   |", "#FFFF00");
                                    gui.drawText(new Position(row + x * 18, col + 3 + y * 8), "---+", "#FFFF00");
                                    break;
                                case 4:
                                    gui.drawText(new Position(row + 3 + x * 18, col + 1 + y * 8), "+---+", "#FFFF00");
                                    gui.drawText(new Position(row + 3 + x * 18, col + 2 + y * 8), "|   |", "#FFFF00");
                                    gui.drawText(new Position(row + 3 + x * 18, col + 3 + y * 8), "+---+", "#FFFF00");
                                    break;
                                case 5:
                                    gui.drawText(new Position(row + 7 + x * 18, col + 1 + y * 8), "+---", "#FFFF00");
                                    gui.drawText(new Position(row + 7 + x * 18, col + 2 + y * 8), "|   ", "#FFFF00");
                                    gui.drawText(new Position(row + 7 + x * 18, col + 3 + y * 8), "+---", "#FFFF00");
                                    break;
                                case 6:
                                    gui.drawText(new Position(row + x * 18, col + 4 + y * 8), "   |", "#FFFF00");
                                    gui.drawText(new Position(row + x * 18, col + 3 + y * 8), "---+", "#FFFF00");
                                    break;
                                case 7:
                                    gui.drawText(new Position(row + 3 + x * 18, col + 4 + y * 8), "|   |", "#FFFF00");
                                    gui.drawText(new Position(row + 3 + x * 18, col + 3 + y * 8), "+---+", "#FFFF00");
                                    break;
                                case 8:
                                    gui.drawText(new Position(row + 7 + x * 18, col + 4 + y * 8), "|   ", "#FFFF00");
                                    gui.drawText(new Position(row + 7 + x * 18, col + 3 + y * 8), "+---", "#FFFF00");
                                    break;
                                default:
                                    gui.drawText(new Position(22, 22), String.valueOf(getModel().getSelected()), "#0000FF");
                                    break;
                            }
                        }
                        break;
                    case 1: // jogo acabou, o jogador 1
                        gui.drawText(new Position(row + x * 18, col - 1 + y * 8), "  \\     /  ", "#29C3F4");
                        gui.drawText(new Position(row + x * 18, col + y * 8), "   \\   /   ", "#29C3F4");
                        gui.drawText(new Position(row + x * 18, col + 1 + y * 8), "    \\ /    ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 2 + y * 8), "      X     ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 3 + y * 8), "     / \\   ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 4 + y * 8), "    /   \\  ", "#29C3F4");
                        gui.drawText(new Position(row - 1 + x * 18, col + 5 + y * 8), "   /     \\ ", "#29C3F4");
                        break;
                    case 2: // jogo acabou, o jogador 2 ganhou
                        gui.drawText(new Position(row - 1 + x * 18, col - 1 + y * 8), "   *******  ", "#FF0000");
                        gui.drawText(new Position(row - 1+ x * 18, col + y * 8), "  *       * ", "#FF0000");
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

    }

    /*private void higlightMini(GUI gui, Position topCorner, int selected){
        switch(selected){
            case 0:
                gui.drawText(new Position(topCorner.getX()+3, topCorner.getY()));
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
        }
    }*/
}
