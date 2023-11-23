package project.viewer;

import project.gui.GUI;
import project.model.Position;
import project.model.rules.Rule;

public class RuleViewer extends Viewer<Rule>{
    public RuleViewer(Rule rule) {
        super(rule);
    }

    @Override
    public void drawElements(GUI gui) {
        for (int i = 0; i < getModel().getNumberLines(); i++)
            gui.drawText(
                    new Position(10, 7 + i),
                    getModel().getLine(i), "#FFFFFF");
    }
}
