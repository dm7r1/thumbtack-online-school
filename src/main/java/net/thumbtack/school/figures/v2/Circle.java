package net.thumbtack.school.figures.v2;

public class Circle extends Figure {
    private Point2D center;
    private int radius;
    public Circle(Point2D center, int radius, int color) {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius, int color) {
        this(new Point2D(xCenter, yCenter), radius, color);
    }

    public Circle(int radius, int color) {
        this(new Point2D(), radius, color);
    }

    public Circle(int color) {
        this(1, color);
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void enlarge(int n) {
        radius *= n;
    }

    @Override
    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public boolean isInside(int x, int y) {
        return Math.pow((center.getX() - x), 2) + Math.pow((center.getY() - y), 2) <= radius * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Circle circle = (Circle) o;

        if (radius != circle.radius) return false;
        return center.equals(circle.center);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + center.hashCode();
        result = 31 * result + radius;
        return result;
    }
}
