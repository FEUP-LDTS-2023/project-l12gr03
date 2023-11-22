public class BoardViewer extends Viewer<Board>{

    public BoardViewer(Board board) {
        super(board);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(10, 5), "Menu", "#FFFFFF");
        gui.drawText(new Position(10,7),getModel().dummy.toString(),"#FFD700");
        /*for (int i = 0; i < getModel().getElements(); i++)
            gui.drawText(
                    new Position(10, 7 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");*/
    }

}
