package net.thumbtack.school.figures.v2;

public class Triangle extends Figure {
    private Point2D point1, point2, point3;

    public Triangle(Point2D point1, Point2D point2, Point2D point3, int color) {
        super(color);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Point2D getPoint1() {
        return point1;
    }

    public void setPoint1(Point2D point1) {
        this.point1 = point1;
    }

    public Point2D getPoint2() {
        return point2;
    }

    public void setPoint2(Point2D point2) {
        this.point2 = point2;
    }

    public Point2D getPoint3() {
        return point3;
    }

    public void setPoint3(Point2D point3) {
        this.point3 = point3;
    }

    private double calcDistance(Point2D point1, Point2D point2) {
        return Math.sqrt(Math.pow((point1.getX() - point2.getX()), 2) + Math.pow((point1.getY() - point2.getY()), 2));
    }

    public double getSide12() {
        return calcDistance(point1, point2);
    }

    public double getSide13() {
        return calcDistance(point1, point3);
    }

    public double getSide23() {
        return calcDistance(point2, point3);
    }

    @Override
    public void moveRel(int dx, int dy) {
        point1.moveRel(dx, dy);
        point2.moveRel(dx, dy);
        point3.moveRel(dx, dy);
    }

    @Override
    public double getArea() {
        double a = getSide12();
        double b = getSide13();
        double c = getSide23();
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double getPerimeter() {
        return getSide12() + getSide13() + getSide23();
    }

    private double side(Point2D linePoint1, Point2D linePoint2, Point2D point) {
        return (point.getX() - linePoint1.getX()) * (linePoint2.getY() - linePoint1.getY())
                - (point.getY() - linePoint1.getY()) * (linePoint2.getX() - linePoint1.getX());
    }

    private boolean sameSide(Point2D linePoint1, Point2D linePoint2, Point2D point1, Point2D point2) {
        return side(linePoint1, linePoint2, point1) * side(linePoint1, linePoint2, point2) >= 0;
    }

    @Override
    public boolean isInside(Point2D point) {
        return  sameSide(point1, point2, point3, point) &&
                sameSide(point1, point3, point2, point) &&
                sameSide(point2, point3, point1, point);
    }

    @Override
    public boolean isInside(int x, int y) {
        return isInside(new Point2D(x, y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Triangle triangle = (Triangle) o;

        if (!point1.equals(triangle.point1)) return false;
        if (!point2.equals(triangle.point2)) return false;
        return point3.equals(triangle.point3);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + point1.hashCode();
        result = 31 * result + point2.hashCode();
        result = 31 * result + point3.hashCode();
        return result;
    }
}
