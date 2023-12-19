package model.TTTtest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.model.board.Player;

public class PlayerTest {

    @Property
    void getSymbol(@ForAll int score, @ForAll Character car){
        Player player = new Player(car,score);
        Assertions.assertEquals(car,player.getSymbol());
    }

    @Property
    void getScore(@ForAll int score, @ForAll Character car){
        Player player = new Player(car,score);
        Assertions.assertEquals(score,player.getScore());
    }

    @Property
    void addScore(@ForAll("smallPositiveIntegers") int score, @ForAll Character car){
        Player player = new Player(car,score);
        player.addScore();
        score++;
        Assertions.assertEquals(score,player.getScore());
    }

    @Property
    void addScoreTie(@ForAll("smallPositiveIntegers") int score, @ForAll Character car){
        Player player = new Player(car,score);
        player.addScoreTie();
        Assertions.assertEquals(score+0.5,player.getScore());
    }

    @Provide
    Arbitrary<Integer> smallPositiveIntegers() {
        return Arbitraries.integers().between(0, 1000); // Adjust the upper limit as needed
    }

}
