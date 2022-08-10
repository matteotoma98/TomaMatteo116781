package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Cursor;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Directional;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Plane;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDirectionTest {

    @Test
    public void IllegalArgumentExceptionshouldBeThrown() {
        Directional<Integer> direction = new SimpleDirection();
        assertThrows(IllegalArgumentException.class, () -> direction.setDirectionWay(999));
        assertThrows(IllegalArgumentException.class, () -> direction.setDirectionWay(-123));
        assertEquals(0, (int) direction.getDirectionWay());
        assertThrows(IllegalArgumentException.class, () -> new SimpleDirection(-1));
        assertThrows(IllegalArgumentException.class, () -> new SimpleDirection(361));
    }

    @Test
    public void directionShouldBeCreated() {
        Directional<Integer> direction = new SimpleDirection();
        assertEquals(0, (int) direction.getDirectionWay());
        direction.setDirectionWay(200);
        assertEquals(200, (int) direction.getDirectionWay());
        assertDoesNotThrow(() -> new SimpleDirection());
        assertDoesNotThrow(() -> new SimpleDirection(90));
        assertDoesNotThrow(() -> new SimpleDirection(345));
        assertDoesNotThrow(() -> new SimpleDirection(87));
    }

    @Test
    public void testToString() {
        SimpleDirection simpleDirection= new SimpleDirection();
        int angle;
        String actual = simpleDirection.toString();
        assertEquals("0", actual);
    }

}