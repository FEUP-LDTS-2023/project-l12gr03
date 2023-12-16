package project;

import project.gui.LanternaGUI;
import project.model.Menu.Menu;
//import project.model.Music.MusicPlayer;
import project.states.MenuState;
import project.states.State;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final LanternaGUI gui;
    private State state;
   // private MusicPlayer musicPlayer;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(100, 50);
        this.state = new MenuState(new Menu());
<<<<<<< HEAD
       // this.musicPlayer = new MusicPlayer();

=======
>>>>>>> EndGame
    }


    public void setState(State state) {
        this.state = state;
    }

<<<<<<< HEAD
    public void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //musicPlayer.playMusic("resources/gamemusic.wav");
=======
    public void start() throws IOException {
>>>>>>> EndGame
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
    }
}
