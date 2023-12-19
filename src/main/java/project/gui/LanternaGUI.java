package project.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import project.model.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {

    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal);
    }

    public Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    public Terminal createTerminal(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        return terminalFactory.createTerminal();
    }


    @Override
    public ACTION getNextAction() throws IOException {

        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType()  == KeyType.Escape) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Character &&
                (keyStroke.getCharacter() == 'x' || keyStroke.getCharacter() == 'X')) return ACTION.PRESS_X;
        if (keyStroke.getKeyType() == KeyType.Character &&
                (keyStroke.getCharacter() == 'o' || keyStroke.getCharacter() == 'O')) return ACTION.PRESS_O;
        if (keyStroke.getKeyType() == KeyType.Character &&
                (keyStroke.getCharacter() == 'n' || keyStroke.getCharacter() == 'N')) return ACTION.PRESS_N;
        if (keyStroke.getKeyType() == KeyType.Character &&
                (keyStroke.getCharacter() == 'y' || keyStroke.getCharacter() == 'Y')) return ACTION.PRESS_Y;
        if (keyStroke.getKeyType() == KeyType.Character &&
                (keyStroke.getCharacter() == 'P' || keyStroke.getCharacter() == 'p')) return ACTION.PRESS_P;
        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;

        return ACTION.NONE;
    }


    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawCharacter(Position position, Character character, String color)
    {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), "" + character);
    }

    @Override
    public void drawWith2Exceptions(Position position, String text, String defaultColour,
                             Character exception1, String excepColour1,
                             Character exception2, String excepColour2){
        int size = text.length();
        for (int index = 0; index<size ; index++)
        {
            if (text.charAt(index)==exception1) drawCharacter(position,exception1,excepColour1);
            else if (text.charAt(index)==exception2) drawCharacter(position,exception2,excepColour2);
            else drawCharacter(position,text.charAt(index),defaultColour);

            position = position.getRight();
        }
    }

    public Screen getScreen(){return screen;}

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

}
