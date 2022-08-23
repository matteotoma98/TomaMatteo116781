package it.unicam.cs.pa2122.TomaMatteo116781.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphNodeTest {

    private GraphNode<String, String> graphNodeUnderTest;

    @BeforeEach
    void setUp() {
        graphNodeUnderTest = new GraphNode<>("", "");
    }

    @Test
    void testEquals() {
        assertEquals("", graphNodeUnderTest.getName());
        assertEquals("", graphNodeUnderTest.getData());
    }

    @Test
    public void ReflexiveTest()
    {
        var x = graphNodeUnderTest;
        assertEquals(x, x);
    }

    @Test
    public void Transitive()
    {
        var x = graphNodeUnderTest;
        var y= graphNodeUnderTest;
        var z= graphNodeUnderTest;
        assertEquals(x, x);
        assertEquals(y,z);
        assertEquals(x,z);
    }

    @Test
    public void SymmetricTest()
    {
        var x = graphNodeUnderTest;
        var y= graphNodeUnderTest;

        assertEquals(x, y);
        assertEquals(y, x);
        System.out.println(x);
    }

    @Test
    public void NullTest()
    {
        var x = graphNodeUnderTest;
        assertNotEquals(null, x);
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
