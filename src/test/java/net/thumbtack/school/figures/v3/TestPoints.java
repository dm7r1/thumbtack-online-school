package net.thumbtack.school.figures.v3;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPoints {

    @Test
    public void testPoint2D() {
        Point2D point2D = new Point2D();

        point2D.setX(50);
        point2D.setY(75);
        assertEquals(new Point2D(50, 75), point2D);
        point2D = new Point2D();
        point2D.moveTo(75, 95);
        assertEquals(new Point2D(75, 95), point2D);
    }

    @Test
    public void testPoint3D() {
        assertEquals(new Point3D(99, 100, 101), new Point3D(99, 100, 101));
        assertNotEquals(new Point2D(), new Point3D());
        assertNotEquals(null, new Point3D());

        Point3D point3D = new Point3D();
        assertEquals(new Point3D(20), new Point3D(0, 0, 20));
        point3D.setZ(20);
        assertEquals(point3D.getZ(), 20);
        point3D = new Point3D();
        point3D.move(70, 80, 60);
        assertEquals(point3D, new Point3D(70, 80, 60));
    }
}
