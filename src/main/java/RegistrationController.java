import java.io.IOException;

public class RegistrationController extends Controller<PlayerRegistration>{

    public RegistrationController(PlayerRegistration registration){super(registration);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case PRESS_X:
                getModel().assignX();
                break;
            case PRESS_O:
                getModel().assignO();
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;

            case SELECT:
                Player p1 = new Player('X');
                Player p2 = new Player('O');
                if(getModel().symbolChosen()) game.setState(new GameState(new Board(p1,p2)));
        }
    }

}
