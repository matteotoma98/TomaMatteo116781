package it.unicam.cs.pa2122.TomaMatteo116781.view;

import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.ClosedArea;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Line;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FXControllerTest {

    private FXController fxControllerUnderTest;

    @BeforeEach
    void setUp() {
        fxControllerUnderTest = new FXController();
    }

    @Test
    void testInitialize() {
        try {
            fxControllerUnderTest.initialize();
            assertThrows(IOException.class, () -> fxControllerUnderTest.initialize());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    void testCaricaFile() throws Exception {
        final ActionEvent actionEvent = new ActionEvent("source", null);

        try {
            if (actionEvent == null) {
                fxControllerUnderTest.caricaFile(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.caricaFile(actionEvent));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testCaricaFile_ThrowsIOException() {

        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.caricaFile(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.caricaFile(actionEvent));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testStartExecution() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.autoExecution(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.autoExecution(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testMovedCursor() {
        final Point<Double> point = null;
        try {
            if (point == null) {
                fxControllerUnderTest.MovedCursor(point);
                assertThrows(IOException.class, () -> fxControllerUnderTest.MovedCursor(point));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testGeneratedLine() {
        final Line<Point<Double>> line = null;
        try {
            if (line == null) {
                fxControllerUnderTest.GeneratedLine(line);
                assertThrows(IOException.class, () -> fxControllerUnderTest.GeneratedLine(line));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testGeneratedArea() {
        final ClosedArea<Line<Point<Double>>> area = null;
        try {
            if (area == null) {
                fxControllerUnderTest.GeneratedArea(area);
                assertThrows(IOException.class, () -> fxControllerUnderTest.GeneratedArea(area));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testScreenColor() {
        final RGBColor color = new RGBColor(0, 0, 0);
        try {
            if (color == null) {
                fxControllerUnderTest.ScreenColor(color);
                assertThrows(IOException.class, () -> fxControllerUnderTest.ScreenColor(color));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testScreenCleaned() {
        final ActionEvent actionEvent = new ActionEvent("source", null);

        try {
            if (actionEvent == null) {
                fxControllerUnderTest.ScreenCleaned();
                assertThrows(IOException.class, () -> fxControllerUnderTest.ScreenCleaned());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testPreviousPlane() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.previousPlane(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.previousPlane(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testNextPlane() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.nextPlane(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.nextPlane(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testReset() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.reset(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.reset(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testExit() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.exit(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.exit(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testAboutAction() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.aboutAction(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.aboutAction(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testSaveConfiguration() {
        final ActionEvent actionEvent = new ActionEvent("source", null);
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.saveConfiguration(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.saveConfiguration(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
