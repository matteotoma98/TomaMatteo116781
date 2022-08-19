package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Line;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericaAreaTest {

    private GenericaArea genericaAreaUnderTest;

    @BeforeEach
    void setUp() {
        genericaAreaUnderTest = new GenericaArea(List.of(), new RGBColor(0, 0, 0));

    }

    @Test
    void testGetArea() {
        final List<Line<Point<Double>>> expectedResult = List.of();
        final List<Line<Point<Double>>> result = genericaAreaUnderTest.getArea();
        assertEquals(expectedResult, result);
    }

    @Test
    void testToString() {
        StringBuilder lines = new StringBuilder();
        for (Line<Point<Double>> l : genericaAreaUnderTest.getLines())
            lines.append("\n").append(l);
        assertEquals("AREA{ " +
                "lines= " + lines
                +
                "\n, color= " + genericaAreaUnderTest.getColor() +
                " }", genericaAreaUnderTest.toString());
    }

    @Test
    void testEquals() {
        assertFalse(genericaAreaUnderTest.equals("o"));
    }

    @Test
    void testHashCode() {
        assertEquals(30783, genericaAreaUnderTest.hashCode());
        Object x = new GenericaArea(List.of(), new RGBColor(0, 0, 0));
        Object y = new GenericaArea(List.of(), new RGBColor(0, 0, 0));
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }

}
