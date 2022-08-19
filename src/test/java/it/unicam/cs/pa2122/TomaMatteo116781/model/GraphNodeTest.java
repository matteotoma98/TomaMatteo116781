package it.unicam.cs.pa2122.TomaMatteo116781.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GraphNodeTest {

    private GraphNode<String, String> graphNodeUnderTest;

    @BeforeEach
    void setUp() {
        graphNodeUnderTest = new GraphNode<>("", "");
    }

    @Test
    void testEquals() {
        assertFalse(graphNodeUnderTest.equals("o"));
        assertEquals("", graphNodeUnderTest.getName());
        assertEquals("", graphNodeUnderTest.getData());
    }

    @Test
    void testHashCode() {
        assertEquals(31, graphNodeUnderTest.hashCode());
    }


    @Test
    void testToString() {
        final String result = graphNodeUnderTest.toString();
        assertEquals("NODO [  ]", result);
    }
}
