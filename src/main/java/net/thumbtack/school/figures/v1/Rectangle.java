package net.thumbtack.school.figures.v1;

public class Rectangle {
    private Point2D topLeft, bottomRight;

    public Rectangle(Point2D leftTop, Point2D rightBottom) {
        topLeft = leftTop;
        bottomRight = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this(new Point2D(xLeft, yTop), new Point2D(xRight, yBottom));
    }

    public Rectangle(int length, int width) {
        this(0, -Math.abs(width), Math.abs(length), 0);
    }

    public Rectangle() {
        this(1, 1);
    }

    public Point2D getTopLeft() {
        return topLeft;
    }

    public Point2D getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point2D topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point2D bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getLength() {
        return bottomRight.getX() - topLeft.getX();
    }

    public int getWidth() {
        return bottomRight.getY() - topLeft.getY();
    }

    public void moveRel(int dx, int dy) {
        topLeft.moveRel(dx, dy);
        bottomRight.moveRel(dx, dy);
    }

    public void enlarge(int nx, int ny) {
        bottomRight.moveRel(this.getLength() * (nx - 1), this.getWidth() * (ny - 1));
    }

    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return getLength() * 2 + getWidth() * 2;
    }

    public boolean isInside(int x, int y) {
        return (x >= topLeft.getX()) && (y >= topLeft.getY()) && (x <= bottomRight.getX()) && (y <= bottomRight.getY());
    }

    public boolean isInside(Point2D point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Rectangle rectangle) {
        return isInside(rectangle.getTopLeft()) || isInside(rectangle.getBottomRight())
                || rectangle.isInside(topLeft) || rectangle.isInside(bottomRight);
    }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.getTopLeft()) && isInside(rectangle.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (!topLeft.equals(rectangle.topLeft)) return false;
        return bottomRight.equals(rectangle.bottomRight);
    }

    @Override
    public int hashCode() {
        int result = topLeft.hashCode();
        result = 31 * result + bottomRight.hashCode();
        return result;
    }
}
