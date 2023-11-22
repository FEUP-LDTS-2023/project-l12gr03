public class RegistrationState extends State<PlayerRegistration>{

    public RegistrationState(PlayerRegistration model) {super(model);}
    @Override
    protected Viewer<PlayerRegistration> getViewer() {
        return new RegistrationView(getModel());
    }

    @Override
    protected Controller<PlayerRegistration> getController() {
        return new RegistrationController(getModel());
    }

}
