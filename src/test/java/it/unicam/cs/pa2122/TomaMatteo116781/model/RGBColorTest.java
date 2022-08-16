package it.unicam.cs.pa2122.TomaMatteo116781.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RGBColorTest {

    @Test
    public void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(-1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(1, -2, 3));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(-1, -2, -3));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(444, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(1, 256, 3));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(1, 2, 378));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(256, 256, 256));
    }

    @Test
    public void RGBColorShouldBeCreated() {
        RGBColor color = new RGBColor(0, 1, 2);
        assertEquals(0, color.getRed());
        assertEquals(1, color.getGreen());
        assertEquals(2, color.getBlue());
        assertEquals(new RGBColor(0, 1, 2), color);
    }

    @Test
    public void testToString() {
        int r = 0;
        int g = 0;
        int b = 0;
        RGBColor color = new RGBColor(r, g, b);
        String actual = color.toString();
        assertEquals("0 0 0", actual);
    }

}