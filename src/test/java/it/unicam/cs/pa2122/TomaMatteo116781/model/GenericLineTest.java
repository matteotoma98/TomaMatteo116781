package it.unicam.cs.pa2122.TomaMatteo116781.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GenericLineTest {

    private RGBColor mockColor;

    private GenericLine<String> genericLineUnderTest;

    @BeforeEach
    void setUp() {
        genericLineUnderTest = new GenericLine<>("startingPoint", "endPoint", mockColor, 0);
    }

    @Test
    void testToString() {
        assertEquals("L{ " + genericLineUnderTest.getStartingPoint() + " , " + genericLineUnderTest.getEndPoint() + " color = " + genericLineUnderTest.getColor() + " } ", genericLineUnderTest.toString());
    }

    @Test
    void testEquals() {
        assertFalse(genericLineUnderTest.equals("o"));
    }

    @Test
    void testHashCode() {
        assertEquals(437698694, genericLineUnderTest.hashCode());
    }
}
