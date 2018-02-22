package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

public class CircleFactory {
    static private int circleCount = 0;

    public static Circle createCircle(Point2D center, int radius, Color color) throws ColorException {
        circleCount++;
        return new Circle(center, radius, color);
    }

    public static Circle createCircle(Point2D center, int radius, String colorString) throws ColorException {
        circleCount++;
        return new Circle(center, radius, colorString);
    }

    public static int getCircleCount() {
        return circleCount;
    }

    public static void reset() {
        circleCount = 0;
    }
}
