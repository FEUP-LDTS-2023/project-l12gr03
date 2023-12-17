package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import project.gui.LanternaGUI;
import project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    void drawText() {
        gui.drawText(new Position(1, 1), "Testing draw Text", "#8C2D19");
        verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(140, 45, 25));
        verify(tg, Mockito.times(1)).putString(1, 1, "Testing draw Text");
    }

    @Test
    void LanternaGUITest() throws IOException {

        LanternaGUI newGUI = Mockito.spy(new LanternaGUI(100, 101));
        verify(newGUI,times(1)).createTerminal(100, 101);
        verify(newGUI,times(1)).createScreen(any(Terminal.class));
    }


}