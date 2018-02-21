package net.thumbtack.school.figures.v2;

abstract public class Figure implements Colored {
    int color;

    abstract public double getArea();

    abstract public double getPerimeter();

    Figure(int color) {
        this.color = color;
    }

    abstract public boolean isInside(int x, int y);

    public boolean isInside(Point2D point) {
        return isInside(point.getX(), point.getY());
    }

    abstract public void moveRel(int dx, int dy);

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        return color == figure.color;
    }

    @Override
    public int hashCode() {
        return color;
    }
}
