import java.io.IOException;

public abstract class State<T> {

    private final T model;
    private final Viewer<T> viewer;

    private final Controller controller;

    public State(T model)
    {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    public T getModel() {
        return model;
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}
