package it.unicam.cs.pa2122.TomaMatteo116781.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartesianPointTest {

    private CartesianPoint cartesianPointUnderTest;

    @BeforeEach
    void setUp() {
        cartesianPointUnderTest = new CartesianPoint(0.0, 0.0);
    }

    @Test
    void testToString() {
        assertEquals("POINT(" + cartesianPointUnderTest.getX() + "," + cartesianPointUnderTest.getY() + ")", cartesianPointUnderTest.toString());
    }


    @Test
    void testHashCode() {
        assertEquals(961, cartesianPointUnderTest.hashCode());
    }
}
