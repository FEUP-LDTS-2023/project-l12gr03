package project.model.board;

import project.model.Position;
import project.model.board.Board.TickTockToe;

import java.io.IOException;
import java.util.ArrayList;

public class Mini extends TickTockToe {

    char[] miniGame = new char[9];

    public Mini(Player player1, Player player2, int x, int y) throws IOException {
        super(player1, player2, x, y);
    }

    @Override
    public void move(Position position) {}

    @Override
    public void select(Position position){}
}
