import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final LanternaGUI gui;

    //private State state;
    private String message;
    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(17, 17);
        //this.state = new MenuState(new Menu());
    }

    public void start(){}
}
