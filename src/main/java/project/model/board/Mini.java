package project.model.board;

import project.model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Mini extends TicTacToe {



    List<Character> smallSquares;
    private boolean isOver = false;

    protected int state;
    public Mini(Player player1, Player player2, int x, int y, List<Character> squares) throws IOException {
        this.p1 = player1;
        this.p2 = player2;
        this.state = 0; // O estado default é que o jogo está a acontecer
        selected = -1;
        smallSquares = squares;
        position= new Position(x,y);
    }

    public Integer getMiniGameState(){
        return this.state;
    }

    @Override
    public void setGameState(){
        if (checkWinner(this.smallSquares, 'X')) {
            this.state = 1; // jogador X ganha o jogo :)
            isOver = true;
        } else if (checkWinner(this.smallSquares, 'O')) {
            this.state = 2; // jogador O ganha o jogo :(
            isOver = true;
        } else if (isGameTie(this.smallSquares)){
            this.state = 3; // ora bolas empatou
            isOver = true;
        }
    }

    public static boolean isGameTie(List<Character> squares) {
        return !squares.contains(' '); //true se nn tem mais espaços para jogar, ou seja, é um empate
    }

    public static boolean checkWinner(List<Character> squares, char playerSymbol) {
        return (checkRows(squares, playerSymbol) || checkColumns(squares, playerSymbol) || checkDiagonals(squares, playerSymbol));
    }

    public static boolean checkRows(List<Character> squares, char playerSymbol) {
        for (int i = 0; i < 7; i += 3) {
                if (squares.get(i) == playerSymbol && squares.get(i + 1) == playerSymbol && squares.get(i + 2) == playerSymbol) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumns(List<Character> squares, char playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (squares.get(i) == playerSymbol && squares.get(i + 3) == playerSymbol && squares.get(i + 6) == playerSymbol) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonals(List<Character> squares, char playerSymbol) {
        return (squares.get(0) == playerSymbol && squares.get(4) == playerSymbol && squares.get(8) == playerSymbol) ||
                (squares.get(2) == playerSymbol && squares.get(4) == playerSymbol && squares.get(6) == playerSymbol);
    }

    @Override
    public List<Integer> getPlayState() {
        return List.of(state);
    }

    @Override
    public Position getMinPosition(){
        return getPosition();
    }

    @Override
    public int getInnerSelected(){
        return getSelected();
    }

    @Override
    public void goUp(){ selected = (((selected-3) % 9) + 9) % 9;}
    @Override
    public void goDown(){selected = (selected+3) % 9;}
    @Override
    public void goLeft(){selected = (((selected-1) % 9) + 9) % 9;}
    @Override
    public void goRight(){selected = (selected+1) % 9;}

    @Override
    public void endGame(){}

    @Override
    public boolean select(Player player){
        if (!isOver()) {
            if (getSelected() == MINI_NOT_SELECTED) {
                selected = DEFAULT_SQUARE;
            } else {
                return drawsymbol(player);
            }
        }
        return false;
    }

    public boolean drawsymbol(Player player){
        if (smallSquares.get(getSelected()) == ' '){
            nextgame = getSelected();
            smallSquares.set(getSelected(), player.getSymbol());
            selected = MINI_NOT_SELECTED;
            return true;
        }
        return false;
    }

    @Override
    public List<Character> getContents()
    {
        return smallSquares;
    }

    public boolean isOver(){return isOver;}

}
