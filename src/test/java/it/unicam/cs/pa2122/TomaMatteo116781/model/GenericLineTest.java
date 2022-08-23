package it.unicam.cs.pa2122.TomaMatteo116781.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericLineTest {

    public RGBColor mockColor;

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
    public void ReflexiveTest()
    {
        var x = genericLineUnderTest;
        assertEquals(x, x);
    }

    @Test
    public void Transitive()
    {
        var x = genericLineUnderTest;
        var y= genericLineUnderTest;
        var z= genericLineUnderTest;
        assertEquals(x, x);
        assertEquals(y,z);
        assertEquals(x,z);
    }

    @Test
    public void SymmetricTest()
    {
        var x = genericLineUnderTest;
        var y= genericLineUnderTest;

        assertEquals(x, y);
        assertEquals(y, x);
        System.out.println(x);
    }

    @Test
    public void NullTest()
    {
        var x = genericLineUnderTest;
        assertNotEquals(null, x);
    }
    @Test
    void testHashCode() {
        assertEquals(437698694, genericLineUnderTest.hashCode());
    }
}
