package project.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.model.Menu.Menu;

import static org.mockito.Mockito.when;

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

        menu.previousEntry();
        Assertions.assertTrue(menu.isSelected(1));

        menu.previousEntry();
        Assertions.assertTrue(menu.isSelected(0));
    }

    @Test
    void menuLoopsDownwards () {
        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(1));

        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(2));

        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(0));
    }

    @Test
    void getEntryTest() {
        Assertions.assertEquals("Start",menu.getEntry(0));
        Assertions.assertEquals("Rules",menu.getEntry(1));
        Assertions.assertEquals("Exit",menu.getEntry(2));
    }

    @Test
    void startSelected() {
        Assertions.assertTrue(menu.isSelectedStart());
    }

    @Test
    void exitSelected() {
        menu = Mockito.mock(Menu.class);
        when(menu.isSelected(2)).thenReturn(true);
        when(menu.isSelectedExit()).thenCallRealMethod();
        Assertions.assertTrue(menu.isSelectedExit());
    }

    @Test
    void rulesSelected() {
        menu = Mockito.mock(Menu.class);
        when(menu.isSelected(1)).thenReturn(true);
        when(menu.isSelectedRules()).thenCallRealMethod();
        Assertions.assertTrue(menu.isSelectedRules());
    }

    @Test
    void numEntriesTest(){
        Assertions.assertEquals(3,menu.getNumberEntries());
    }
}
