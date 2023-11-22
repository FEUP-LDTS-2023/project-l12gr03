public abstract class State<T> {

    private final T model;
    private final Viewer<T> viewer;

    public State(T model)
    {
        this.model = model;
        this.viewer = getViewer();
    }


    public T getModel() {
        return model;
    }

    protected abstract Viewer<T> getViewer();
}
