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

        }
    }

}
