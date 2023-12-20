package project.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import project.gui.GUI;
import project.gui.LanternaGUI;
import project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.monitor.Monitor;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class LanternaGUITest {

    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawTextTest() {
        gui.drawText(new Position(1, 1), "Testing draw Text", "#8C2D19");
        verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(140, 45, 25));
        verify(tg, Mockito.times(1)).putString(1, 1, "Testing draw Text");
    }

    @Test
    void LanternaGUITest() throws IOException, URISyntaxException, FontFormatException {

        LanternaGUI lanternaGUI = new LanternaGUI(100, 50);
        Assertions.assertNotNull(lanternaGUI.getScreen());
        Assertions.assertEquals(100,lanternaGUI.getScreen().getTerminalSize().getColumns());
        Assertions.assertEquals(50,lanternaGUI.getScreen().getTerminalSize().getRows());
        Assertions.assertNull(lanternaGUI.getScreen().getCursorPosition());
    }

    @Test
    void getNextActionNONETest() throws IOException {

        when(screen.pollInput()).thenReturn(null);
        Assertions.assertEquals(GUI.ACTION.NONE,gui.getNextAction());

        KeyStroke mockKeyStroke = Mockito.mock(KeyStroke.class);
        when(screen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.F4);
        Assertions.assertEquals(GUI.ACTION.NONE,gui.getNextAction());


    }

    @Test
    void getNextActionMovementTest() throws IOException {

        KeyStroke mockKeyStroke = Mockito.mock(KeyStroke.class);
        when(screen.pollInput()).thenReturn(mockKeyStroke);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.EOF);
        Assertions.assertEquals(GUI.ACTION.QUIT,gui.getNextAction());

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.Escape);
        Assertions.assertEquals(GUI.ACTION.QUIT,gui.getNextAction());

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        Assertions.assertEquals(GUI.ACTION.UP,gui.getNextAction());

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        Assertions.assertEquals(GUI.ACTION.DOWN,gui.getNextAction());

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Assertions.assertEquals(GUI.ACTION.LEFT,gui.getNextAction());

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        Assertions.assertEquals(GUI.ACTION.RIGHT,gui.getNextAction());
    }

    @Test
    void getNextActionCharacterTest() throws IOException {
        KeyStroke mockKeyStroke = Mockito.mock(KeyStroke.class);
        when(screen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.Character);

        when(mockKeyStroke.getCharacter()).thenReturn('X');
        Assertions.assertEquals(GUI.ACTION.PRESS_X,gui.getNextAction());
        when(mockKeyStroke.getCharacter()).thenReturn('x');
        Assertions.assertEquals(GUI.ACTION.PRESS_X,gui.getNextAction());

        when(mockKeyStroke.getCharacter()).thenReturn('O');
        Assertions.assertEquals(GUI.ACTION.PRESS_O,gui.getNextAction());
        when(mockKeyStroke.getCharacter()).thenReturn('o');
        Assertions.assertEquals(GUI.ACTION.PRESS_O,gui.getNextAction());

        when(mockKeyStroke.getCharacter()).thenReturn('N');
        Assertions.assertEquals(GUI.ACTION.PRESS_N,gui.getNextAction());
        when(mockKeyStroke.getCharacter()).thenReturn('n');
        Assertions.assertEquals(GUI.ACTION.PRESS_N,gui.getNextAction());

        when(mockKeyStroke.getCharacter()).thenReturn('Y');
        Assertions.assertEquals(GUI.ACTION.PRESS_Y,gui.getNextAction());
        when(mockKeyStroke.getCharacter()).thenReturn('y');
        Assertions.assertEquals(GUI.ACTION.PRESS_Y,gui.getNextAction());

        when(mockKeyStroke.getCharacter()).thenReturn('P');
        Assertions.assertEquals(GUI.ACTION.PRESS_P,gui.getNextAction());
        when(mockKeyStroke.getCharacter()).thenReturn('p');
        Assertions.assertEquals(GUI.ACTION.PRESS_P,gui.getNextAction());

        when(mockKeyStroke.getCharacter()).thenReturn('M');
        Assertions.assertEquals(GUI.ACTION.PRESS_M,gui.getNextAction());
        when(mockKeyStroke.getCharacter()).thenReturn('m');
        Assertions.assertEquals(GUI.ACTION.PRESS_M,gui.getNextAction());
    }

    @Test
    void drawCharacterTest() {
        gui.drawCharacter(new Position(1, 1), 'E', "#8C2D19");
        verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(140, 45, 25));
        verify(tg, Mockito.times(1)).putString(1,1,"E");
    }

    @Test
    void drawWith2ExceptionsTest() {
        gui = Mockito.spy(new LanternaGUI(screen));
        gui.drawWith2Exceptions(new Position(1, 1), "X O R", "#FF0000",'X',"#00FF00",'O',"#0000FF");
        verify(gui, Mockito.times(1)).drawCharacter(new Position(1,1),'X',"#00FF00");
        verify(gui, Mockito.times(1)).drawCharacter(new Position(2,1),' ',"#FF0000");
        verify(gui, Mockito.times(1)).drawCharacter(new Position(3,1),'O',"#0000FF");
        verify(gui, Mockito.times(1)).drawCharacter(new Position(4,1),' ',"#FF0000");
        verify(gui, Mockito.times(1)).drawCharacter(new Position(5,1),'R',"#FF0000");
    }

    @Test
    void updatingGUI() throws IOException {
        gui = Mockito.spy(new LanternaGUI(screen));

        gui.refresh();
        verify(screen,Mockito.times(1)).refresh();

        gui.clear();
        verify(screen,Mockito.times(1)).clear();

        gui.close();
        verify(screen,Mockito.times(1)).close();

    }
}