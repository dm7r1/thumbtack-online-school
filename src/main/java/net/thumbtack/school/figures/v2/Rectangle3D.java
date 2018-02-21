package net.thumbtack.school.figures.v2;


public class Rectangle3D extends Rectangle {
    private int height;

    public Rectangle3D(Point2D leftTop, Point2D rightBottom, int height, int color) {
        super(leftTop, rightBottom, color);
        this.height = height;
    }

    public Rectangle3D(int xLeft, int yTop, int xRight, int yBottom, int height, int color) {
        this(new Point2D(xLeft, yTop), new Point2D(xRight, yBottom), height, color);
    }

    public Rectangle3D(int length, int width, int height, int color) {
        super(length, width, color);
        this.height = height;
    }

    public Rectangle3D(int color) {
        this(1, 1, 1, color);
    }

    public int getHeight() {
        return height;
    }

    public double getVolume() {
        return getArea() * height;
    }

    public boolean isInside(Point3D point) {
        return isInside((Point2D) point) && point.getZ() <= height && point.getZ() >= 0;
    }

    public boolean isIntersects(Rectangle3D rectangle) {
        return isIntersects((Rectangle) rectangle);
    }

    public boolean isInside(Rectangle3D rectangle) {
        return isInside((Rectangle) rectangle) && rectangle.getHeight() <= height;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rectangle3D that = (Rectangle3D) o;

        return height == that.height;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + height;
        return result;
    }
}
