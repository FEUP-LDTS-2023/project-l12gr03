package project.model.board;

import project.model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Mini extends TicTacToe {

    ArrayList<Character> smallSquares = new ArrayList<>(Collections.nCopies(9, ' '));

    public Mini(Player player1, Player player2, int x, int y) throws IOException {
        super(x, y);
        this.p1 = player1;
        this.p2 = player2;
    }

    @Override
    public void goUp(){}
    @Override
    public void goDown(){}
    @Override
    public void goLeft(){}
    @Override
    public void goRight(){}

    @Override
    public void endGame() {}

    @Override
    public void select(Position position){}
}
