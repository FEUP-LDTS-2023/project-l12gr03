package model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.model.Menu.Menu;

public class MenuTest {

    private Menu menu;
    @BeforeEach
    void set_up()
    {
        this.menu = new Menu();
    }

    @Test
    void menuLoopsUpwards() {
        menu.previousEntry();
        Assertions.assertTrue(menu.isSelected(2));
    }

    @Test
    void menuLoopsDownwards () {
        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(0));
    }
}
