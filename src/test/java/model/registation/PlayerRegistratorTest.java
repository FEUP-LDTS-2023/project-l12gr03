package model.registation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.model.registation.PlayerRegistrator;

public class PlayerRegistratorTest {

    private PlayerRegistrator playerRegistrator;

    @BeforeEach
    void setUp() {
        this.playerRegistrator = new PlayerRegistrator();
    }

    @Test
    void testSymbolChosenInitiallyFalse() {

        Assertions.assertFalse(playerRegistrator.symbolChosen());
    }

    @Test
    void testAssignX() {

        playerRegistrator.assignX();
        Assertions.assertTrue(playerRegistrator.symbolChosen());
        Assertions.assertEquals('X', playerRegistrator.getPlayerSymbol(1));
        Assertions.assertEquals("Player 1, pick crosses or circles[X/O]: X", playerRegistrator.getMessage());
    }

    @Test
    void testAssignO() {
        playerRegistrator.assignO();
        Assertions.assertTrue(playerRegistrator.symbolChosen());
        Assertions.assertEquals('O', playerRegistrator.getPlayerSymbol(1));
        Assertions.assertEquals("Player 1, pick crosses or circles[X/O]: O", playerRegistrator.getMessage());
    }
}
