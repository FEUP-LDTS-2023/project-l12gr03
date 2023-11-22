import java.util.List;
import java.util.Scanner;

public class RulesViewer extends Viewer<Menu> {
    public RulesViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        int r = 0;
        int c = 0;

        Scanner myReader = new Scanner(file,  Charset.defaultCharset().name());
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            getGraphics().setForegroundColor(TextColor.ANSI.WHITE);
            getGraphics().putString(new TerminalPosition(r, c), data);
            c = c + 1;
        }

        gui.drawText(new Position(5, 5), "Menu", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(5, 7 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
}
