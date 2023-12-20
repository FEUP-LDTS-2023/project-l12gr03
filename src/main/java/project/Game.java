package project;

import project.controller.MenuController;
import project.gui.LanternaGUI;
import project.model.Menu.Menu;
//import project.model.Music.MusicPlayer;
import project.states.MenuState;
import project.states.State;
import project.viewer.MenuViewer;
import project.viewer.Viewer;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.System.exit;

public class Game {

    private final LanternaGUI gui;
    private State state;


    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(100, 50);
        Menu menu = new Menu();
        MenuViewer viewer = new MenuViewer(menu);
        MenuController controller = new MenuController(menu);
        this.state = new MenuState(menu,viewer,controller);


    }


    public void setState(State state) {
        this.state = state;
    }

    public void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException {


        int FPS = 25;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
                // Dummy text so the compiler knows the catch is not being ignored, poor guy
            }
        }

        gui.close();
        exit(0);
    }
}
