package project.model;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PositionTest {

    @Property
    void getLeft(@ForAll int x,@ForAll int y) {
        assertEquals(x - 1, new Position(x, y).getLeft().getX());
        assertEquals(y, new Position(x, y).getLeft().getY());
    }

    @Property
    void getRight(@ForAll int x, @ForAll int y) {
        assertEquals(x + 1, new Position(x, y).getRight().getX());
        assertEquals(y, new Position(x, y).getRight().getY());
    }

    @Property
    void getUp(@ForAll int x, @ForAll int y) {
        assertEquals(x, new Position(x, y).getUp().getX());
        assertEquals(y - 1, new Position(x, y).getUp().getY());
    }

    @Property
    void getDown(@ForAll int x, @ForAll int y) {
        assertEquals(x, new Position(x, y).getDown().getX());
        assertEquals(y + 1, new Position(x, y).getDown().getY());
    }

    @Property
    void hash(@ForAll int x, @ForAll int y){
        Position pos = new Position(x,y);
        Position pos2 = new Position(x,y);
        assertEquals(pos.hashCode(),pos2.hashCode());
    }

    @Test
    void equalsTest() {
        Character car = 'J';
        Position pos = new Position(0,0);
        Assertions.assertNotEquals(pos, car);
        assertEquals(pos, pos);
        Assertions.assertNotEquals(pos, pos.getDown());


    }
}