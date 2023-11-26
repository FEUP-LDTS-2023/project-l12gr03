package project;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application
{
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        Game jogo = new Game();
        jogo.start();
    }

}
