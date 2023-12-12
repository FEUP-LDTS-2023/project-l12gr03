package model.TTTtest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import project.model.Position;
import project.model.board.Mini;
import project.model.board.Player;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

public class MiniTest {

    Mini mini;

    @BeforeEach
    void setUp() throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        mini = new Mini(p1,p2,0,0);
    }

    @Property
    void getPositionTest(@ForAll int x, @ForAll int y) throws IOException {
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        Mini mini = new Mini(p1,p2,x,y);
        Assertions.assertEquals(new Position(x,y),mini.getPosition());
    }

    @Test
    void IsGameTieTest() {
        boolean result;
        List<Character> squares = List.of('X', 'O', ' ','X', 'O', ' ','X', 'O', ' ');
        result = Mini.isGameTie(squares);
        Assertions.assertFalse(result, "Expected game not to be a tie with empty squares available");

        squares = List.of('X', 'O', 'X', 'O','X', 'O','X', 'O','X');
        result = Mini.isGameTie(squares);
        Assertions.assertTrue(result, "Expected game to be a tie with no empty squares available");

        squares = List.of(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ');
        result = Mini.isGameTie(squares);
        Assertions.assertFalse(result, "Expected game not to be a tie with all squares empty");
    }
/*
    @Test
    void setMiniGameStateTest(){
        mini = Mockito.mock(Mini.class);
        when(Mini.isGameTie(anyList())).thenReturn(false);
        mini.setMiniGameState();
        Assertions.assertEquals(0,mini.getMiniGameState(),"If the game is still occurring it shoul have state 0");

        when(Mini.isGameTie(anyList())).thenReturn(true);
        //change original function TODO
    }*/

}
