package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GenericCursorTest {

    @Test
    public void NullPointerExceptionShouldBeThrown() {
        assertThrows(NullPointerException.class, () -> new GenericCursor(null));
        assertThrows(NullPointerException.class, () -> new GenericCursor(new DefaultPlane(100, 100), null, Direction.defaultGenericDirection(), new RGBColor(0, 0, 0), new RGBColor(255, 0, 0)));
        assertThrows(NullPointerException.class, () -> new GenericCursor(new DefaultPlane(100, 100), Point.cartesianPoint(0, 0), null, new RGBColor(0, 0, 0), new RGBColor(255, 0, 0)));
        assertThrows(NullPointerException.class, () -> new GenericCursor(new DefaultPlane(100, 100), Point.cartesianPoint(0, 0), Direction.genericDirection(2), null, new RGBColor(255, 0, 0)));
        assertThrows(NullPointerException.class, () -> new GenericCursor(new DefaultPlane(100, 100), Point.cartesianPoint(0, 0), Direction.genericDirection(2), new RGBColor(255, 0, 0), null));
    }

    @Test
    public void IllegalArgumentExceptionShouldBeThrown() {
        assertThrows(IllegalArgumentException.class, () -> new GenericCursor(new DefaultPlane(200, 200), Point.cartesianPoint(300, 190), Direction.defaultGenericDirection(), new RGBColor(1, 2, 3), new RGBColor(4, 5, 6)));
        Cursor<Point<Double>, GenericDirection> cursor = new GenericCursor(new DefaultPlane(20, 20));
        assertThrows(IllegalArgumentException.class, () -> cursor.setPenSize(0));
    }

    @Test
    public void genericCursorShouldBeCreated() {
        Plane<Point<Double>> plane = new DefaultPlane(500, 500);
        Cursor<Point<Double>, GenericDirection> cursor = plane.getCursor();
        assertEquals(plane, cursor.getPlane());
        assertEquals(plane.getCursor(), cursor);
        assertEquals(plane.getHome(), cursor.getPosition());
        assertEquals(0, cursor.getDirection().getDirectionWay());
        assertEquals(new RGBColor(0, 0, 0), cursor.getLineColor());
        assertEquals(new RGBColor(255, 255, 255), cursor.getAreaColor());
        assertTrue(cursor.isPen());
        assertFalse(cursor.isPlot());
    }

    @Test
    public void cursorShouldBeChangedInPlane() {
        Plane<Point<Double>> plane = new DefaultPlane(500, 500);
        Instruction<Point<Double>> i = Instruction::forward;
        plane = i.execute(plane, 50);
        assertEquals(Point.cartesianPoint(300, 250), plane.getCursorPosition());
        i = Instruction::left;
        plane = i.execute(plane, 156);
        assertEquals(Direction.genericDirection(156).getDirectionWay(), plane.getCursor().getDirection().getDirectionWay());
        i = Instruction::penUp;
        plane = i.execute(plane);
        assertFalse(plane.getCursor().isPen());
        i = Instruction::backward;
        plane = i.execute(plane, 89);
        assertFalse(plane.getCursor().isPlot());
        i = Instruction::penDown;
        plane = i.execute(plane);
        assertTrue(plane.getCursor().isPen());
        i = Instruction::forward;
        plane = i.execute(plane, 34);
        assertTrue(plane.getCursor().isPlot());
        i = Instruction::setPenColor;
        plane = i.execute(plane, 255, 110, 254);
        assertEquals(new RGBColor(255, 110, 254), plane.getCursor().getLineColor());
        i = Instruction::setFillColor;
        plane = i.execute(plane, 29, 15, 0);
        assertEquals(new RGBColor(29, 15, 0), plane.getCursor().getAreaColor());
        i = Instruction::setPenSize;
        plane = i.execute(plane, 23);
        assertEquals(23, plane.getCursor().getPenSize());
    }

    @Test
    public void cursorShouldBeRemainAtBorder() {
        Plane<Point<Double>> plane = new DefaultPlane(1000, 1000);
        Instruction<Point<Double>> i = Instruction::forward;
        plane = i.execute(plane, 501);
        assertEquals(Point.cartesianPoint(999, 500), plane.getCursorPosition());
        i = Instruction::home;
        plane = i.execute(plane);
        i = Instruction::repeat;
        plane = i.execute(plane, 1, Arrays.asList("LEFT", "90", "FORWARD", "501"));
        assertEquals(Point.cartesianPoint(500, 999), plane.getCursorPosition());
        i = Instruction::forward;
        plane = i.execute(plane, 51);
        assertEquals(Point.cartesianPoint(500, 999), plane.getCursorPosition());
        i = Instruction::repeat;
        plane = i.execute(plane, 1, Arrays.asList("LEFT", "90", "FORWARD", "501"));
        assertEquals(plane.getUpLeftPoint(), plane.getCursorPosition());
        i = Instruction::repeat;
        plane = i.execute(plane, 1, Arrays.asList("RIGHT", "90", "BACKWARD", "1000"));
        assertEquals(plane.getOrigin(), plane.getCursorPosition());
        i = Instruction::repeat;
        plane = i.execute(plane, 1, Arrays.asList("HOME", "PENUP", "RIGHT", "90", "PENDOWN", "FORWARD", "500", "RIGHT", "90", "FORWARD", "1000"));
        assertEquals(plane.getDownRightPoint(), plane.getCursorPosition());
    }

    @Test
    public void testToString() {
        Plane<Point<Double>> plane = new DefaultPlane(1000, 500);
        String actual = plane.toString();
        assertEquals("""
                DefaultPlane{ length=1000.0, height=500.0
                home=POINT(500.0,250.0)
                origin=POINT(0.0,0.0)
                cursor=GenericCursor{
                position=POINT(500.0,250.0)
                direction=0
                lineColor=0 0 0
                areaColor=255 255 255
                plot=false
                pen=true
                penSize=1}
                lines=
                closedAreas=
                backgroundColor=255 255 255
                }""", actual);
    }
}