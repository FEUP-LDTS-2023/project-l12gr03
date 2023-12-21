package project.viewer;

import project.gui.GUI;
import project.model.Menu.Menu;
import project.model.Position;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(9, 5), "M E N U", "#FFFFFF");
        for (int x=6; x<19;x++){gui.drawText(new Position(x, 6), "-", "#FFFFFF");}

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(10, 7 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}