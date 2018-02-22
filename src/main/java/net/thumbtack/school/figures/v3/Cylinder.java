package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

public class Cylinder extends Circle {
    private int height;

    public Cylinder(Point2D center, int radius, int height, Color color) throws ColorException {
        super(center, radius, color);
        this.height = height;
    }

    public Cylinder(Point2D center, int radius, int height, String colorString) throws ColorException {
        this(center, radius, height, Color.colorFromString(colorString));
    }

    public Cylinder(int xCenter, int yCenter, int radius, int height, Color color) throws ColorException {
        this(new Point2D(xCenter, yCenter), radius, height, color);
    }

    public Cylinder(int xCenter, int yCenter, int radius, int height, String colorString) throws ColorException {
        this(xCenter, yCenter, radius, height, Color.colorFromString(colorString));
    }

    public Cylinder(int radius, int height, Color color) throws ColorException {
        this(new Point2D(), radius, height, color);
    }

    public Cylinder(int radius, int height, String colorString) throws ColorException {
        this(radius, height, Color.colorFromString(colorString));
    }

    public Cylinder(Color color) throws ColorException {
        this(1, 1, color);
    }

    public Cylinder(String colorString) throws ColorException {
        this(Color.colorFromString(colorString));
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getVolume() {
        return getArea() * height;
    }

    public boolean isInside(int x, int y, int z) {
        return isInside(x, y) && z <= height && z >= 0;
    }

    public boolean isInside(Point3D point) {
        return isInside(point.getX(), point.getY(), point.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cylinder cylinder = (Cylinder) o;

        return height == cylinder.height;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + height;
        return result;
    }
}
