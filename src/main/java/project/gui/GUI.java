package project.gui;

import java.io.IOException;
import project.model.Position;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, PRESS_X, PRESS_O}
}