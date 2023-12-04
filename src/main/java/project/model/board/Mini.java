package project.model.board;

import project.model.PedroPair;
import project.model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mini extends TicTacToe {

    //ArrayList<Character> smallSquares = new ArrayList<>(Collections.nCopies(9, ' '));
    List<Character> smallSquares = Arrays.asList('A','B','C','D','E','F','G','H','I');
    protected int state;
    public Mini(Player player1, Player player2, int x, int y) throws IOException {
        super(x, y);
        this.p1 = player1;
        this.p2 = player2;
        this.state = 0;
    }

    protected int getState(){ // 0 = jogo está a acontecer
        return state;
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
    public List<String> getContents()
    {
        List<String> res = new ArrayList<String>();
        for (Character car : smallSquares)
        {
            res.add(String.valueOf(car));
        }
        return res;
    }

    @Override
    public void select(Position position){}
}
