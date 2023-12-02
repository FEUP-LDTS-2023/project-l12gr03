package project;

import project.gui.LanternaGUI;
import project.model.Menu.Menu;
import project.states.MenuState;
import project.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final LanternaGUI gui;
    private State state;


    private String message;
    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(100, 50);
        this.state = new MenuState(new Menu());

    }


    public void setState(State state) {
        this.state = state;
    }

    public void start() throws IOException {

        int FPS = 50;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }
        gui.close();
    }
}
