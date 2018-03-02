package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;

public class Box<T extends Figure> implements HasArea {
    private T figure;

    public Box(T figure) {
        this.figure = figure;
    }

    public void setContent(T figure) {
        this.figure = figure;
    }

    public T getContent() {
        return figure;
    }

    public boolean isAreaEqual(Box another) {
        return getArea() == another.getArea();
    }

    static  public boolean isAreaEqual(Box first, Box second) {
        return first.getArea() == second.getArea();
    }

    @Override
    public double getArea() {
        return figure.getArea();
    }
}
