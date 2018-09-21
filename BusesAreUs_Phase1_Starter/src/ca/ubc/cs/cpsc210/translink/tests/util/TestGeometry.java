package ca.ubc.cs.cpsc210.translink.tests.util;

import ca.ubc.cs.cpsc210.translink.util.Geometry;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the Geometry class
 */

// TODO: Write more tests

public class TestGeometry {
    LatLon northWest, southEast;
    LatLon inside = new LatLon(49.25000, -122.75000);
    LatLon outside = new LatLon(48.25000, -122.75000);
    LatLon outside2 = new LatLon(47.25000, -122.75000);

    @Before
    public void setup() {
        northWest = new LatLon(49.50000, -123.00000);
        southEast = new LatLon(49.00000, -122.50000);
    }

    @Test
    public void testPointInRectangle() {
        assertTrue(Geometry.rectangleContainsPoint(northWest, southEast, inside));
    }
    @Test
    public void testLineInRectangle() {
        assertTrue(Geometry.rectangleIntersectsLine(northWest, southEast, inside, southEast));
    }

    @Test
    public void testPointOutsideOfRectangle() {
        assertEquals(false, Geometry.rectangleContainsPoint(northWest, southEast, outside));
    }
    @Test
    public void testLineOutsideOfRectangle() {
        assertEquals(false, Geometry.rectangleIntersectsLine(northWest, southEast, outside, outside2));
    }
}
