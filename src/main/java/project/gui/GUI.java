package project.gui;

import java.io.IOException;
import java.io.StringReader;

import project.model.Position;


public interface GUI {

    ACTION getNextAction() throws IOException;

    void drawText(Position position, String text, String color);
    void drawCharacter(Position position, Character character, String color);

    void drawWith2Exceptions(Position position, String text, String defaultColour,
                             Character exception1, String exepColour1,
                             Character exception2, String exepColour2);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, PRESS_X, PRESS_O, PRESS_Y, PRESS_N, PRESS_P, ESC}
}
