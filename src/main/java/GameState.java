public class GameState extends State<Board>{

    public GameState(Board board) {
        super(board);
    }

    @Override
    protected Viewer<Board> getViewer() {
        return new BoardViewer(getModel());
    }

    @Override
    protected Controller<Board> getController() {
        return new BoardController(getModel());
    }
}
