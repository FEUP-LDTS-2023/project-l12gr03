import java.io.IOException;

public class RuleController extends Controller<Rule> {

    public RuleController(Rule rule){super (rule);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case DOWN:
                game.setState(new MenuState(new Menu()));
                break;
        }
    }
} // this is the controller

