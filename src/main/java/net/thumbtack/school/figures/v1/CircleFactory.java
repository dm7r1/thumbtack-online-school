package net.thumbtack.school.figures.v1;

public class CircleFactory {
    static private int circleCount = 0;
    public static Circle createCircle(Point2D center, int radius) {
        circleCount++;
        return new Circle(center, radius);
    }

    public static int getCircleCount() {
        return circleCount;
    }

    public static void reset() {
        circleCount = 0;
    }
}
