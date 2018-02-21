package net.thumbtack.school.figures.v2;

public class Rectangle extends Figure {
    private Point2D topLeft, bottomRight;

    public Rectangle(Point2D leftTop, Point2D rightBottom, int color) {
        super(color);
        topLeft = leftTop;
        bottomRight = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        this(new Point2D(xLeft, yTop), new Point2D(xRight, yBottom), color);
    }

    public Rectangle(int length, int width, int color) {
        this(0, -Math.abs(width), Math.abs(length), 0, color);
    }

    public Rectangle(int color) {
        this(1, 1, color);
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

    @Override
    public void moveRel(int dx, int dy) {
        topLeft.moveRel(dx, dy);
        bottomRight.moveRel(dx, dy);
    }

    public void enlarge(int nx, int ny) {
        bottomRight.moveRel(this.getLength() * (nx - 1), this.getWidth() * (ny - 1));
    }

    @Override
    public double getArea() {
        return getLength() * getWidth();
    }

    @Override
    public double getPerimeter() {
        return getLength() * 2 + getWidth() * 2;
    }

    @Override
    public boolean isInside(int x, int y) {
        return (x >= topLeft.getX()) && (y >= topLeft.getY()) && (x <= bottomRight.getX()) && (y <= bottomRight.getY());
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
        if (!super.equals(o)) return false;

        Rectangle rectangle = (Rectangle) o;

        if (!topLeft.equals(rectangle.topLeft)) return false;
        return bottomRight.equals(rectangle.bottomRight);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + topLeft.hashCode();
        result = 31 * result + bottomRight.hashCode();
        return result;
    }
}
