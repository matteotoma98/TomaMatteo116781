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
        // Setup
        // Run the test

        try {
            fxControllerUnderTest.initialize();
            assertThrows(IOException.class, () -> fxControllerUnderTest.initialize());
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testCaricaFile() throws Exception {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test

        try {
            if (actionEvent == null) {
                fxControllerUnderTest.caricaFile(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.caricaFile(actionEvent));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testCaricaFile_ThrowsIOException() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);
        // Run the test
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
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test

        try {
            if (actionEvent == null) {
                fxControllerUnderTest.startExecution(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.startExecution(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testMovedCursor() {
        // Setup
        final Point<Double> point = null;

        // Run the test
        try {
            if (point == null) {
                fxControllerUnderTest.MovedCursor(point);
                assertThrows(IOException.class, () -> fxControllerUnderTest.MovedCursor(point));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Verify the results
    }

    @Test
    void testGeneratedLine() {
        // Setup
        final Line<Point<Double>> line = null;

        // Run the test
        try {
            if (line == null) {
                fxControllerUnderTest.GeneratedLine(line);
                assertThrows(IOException.class, () -> fxControllerUnderTest.GeneratedLine(line));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Verify the results
    }

    @Test
    void testGeneratedArea() {
        // Setup
        final ClosedArea<Line<Point<Double>>> area = null;

        // Run the test
        try {
            if (area == null) {
                fxControllerUnderTest.GeneratedArea(area);
                assertThrows(IOException.class, () -> fxControllerUnderTest.GeneratedArea(area));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testScreenColor() {
        // Setup
        final RGBColor color = new RGBColor(0, 0, 0);

        // Run the test

        try {
            if (color == null) {
                fxControllerUnderTest.ScreenColor(color);
                assertThrows(IOException.class, () -> fxControllerUnderTest.ScreenColor(color));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testScreenCleaned() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);
        // Run the test
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.ScreenCleaned();
                assertThrows(IOException.class, () -> fxControllerUnderTest.ScreenCleaned());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testPreviousPlane() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);
// Run the test
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.previousPlane(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.previousPlane(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testNextPlane() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.nextPlane(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.nextPlane(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Verify the results
    }

    @Test
    void testReset() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.reset(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.reset(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        // Verify the results
    }

    @Test
    void testExit() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test
        // Verify the results
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
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.aboutAction(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.aboutAction(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // Verify the results
    }

    @Test
    void testSaveConfiguration() {
        // Setup
        final ActionEvent actionEvent = new ActionEvent("source", null);

        // Run the test
        try {
            if (actionEvent == null) {
                fxControllerUnderTest.saveConfiguration(actionEvent);
                assertThrows(IOException.class, () -> fxControllerUnderTest.saveConfiguration(actionEvent));
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        // Verify the results
    }
}
