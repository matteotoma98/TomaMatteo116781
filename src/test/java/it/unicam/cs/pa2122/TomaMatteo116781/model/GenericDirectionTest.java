package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericDirectionTest {

    @Test
    public void IllegalArgumentExceptionshouldBeThrown() {
        Direction<Integer> direction = new GenericDirection();
        assertThrows(IllegalArgumentException.class, () -> direction.setDirectionWay(999));
        assertThrows(IllegalArgumentException.class, () -> direction.setDirectionWay(-123));
        assertEquals(0, (int) direction.getDirectionWay());
        assertThrows(IllegalArgumentException.class, () -> new GenericDirection(-1));
        assertThrows(IllegalArgumentException.class, () -> new GenericDirection(361));
    }

    @Test
    public void directionShouldBeCreated() {
        Direction<Integer> direction = new GenericDirection();
        assertEquals(0, (int) direction.getDirectionWay());
        direction.setDirectionWay(200);
        assertEquals(200, (int) direction.getDirectionWay());
        assertDoesNotThrow(() -> new GenericDirection());
        assertDoesNotThrow(() -> new GenericDirection(90));
        assertDoesNotThrow(() -> new GenericDirection(345));
        assertDoesNotThrow(() -> new GenericDirection(87));
    }

    @Test
    public void testToString() {
        GenericDirection genericDirection = new GenericDirection();
        int angle;
        String actual = genericDirection.toString();
        assertEquals("0", actual);
    }

}