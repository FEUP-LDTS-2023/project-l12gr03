package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.mockito.Mockito;
import project.gui.LanternaGUI;
import project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class LanternaGUITest {

    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawText() {
        gui.drawText(new Position(1, 1), "Testing draw Text", "#8C2D19");
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(140, 45, 25));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "Testing draw Text");

    }


}