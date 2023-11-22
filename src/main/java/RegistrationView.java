public class RegistrationView extends Viewer<PlayerRegistration> {

    public RegistrationView(PlayerRegistration registration) {super(registration);}

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(
                new Position(10,5),
                getModel().getMessage(),"#FFFFFF");
    }

}
