package project;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
public class Application
{
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.start();
    }

}
