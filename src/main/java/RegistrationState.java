public class RegistrationState extends State<PlayerRegistrator>{

    public RegistrationState(PlayerRegistrator model) {super(model);}
    @Override
    protected Viewer<PlayerRegistrator> getViewer() {
        return new RegistrationView(getModel());
    }

    @Override
    protected Controller<PlayerRegistrator> getController() {
        return new RegistrationController(getModel());
    }

}
