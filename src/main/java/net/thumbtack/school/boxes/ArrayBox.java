package net.thumbtack.school.boxes;

import net.thumbtack.school.figures.v3.Figure;

public class ArrayBox<T extends Figure> {
    private T[] figures;

    public ArrayBox(T[] figures) {
        this.figures = figures;
    }

    public void setContent(T[] figures) {
        this.figures = figures;
    }

    public T[] getContent() {
        return figures;
    }

    public void setElement(int i, T figure) {
        figures[i] = figure;
    }

    public T getElement(int i) {
        return figures[i];
    }

    public boolean isSameSize(ArrayBox arrayBox) {
        return figures.length == arrayBox.figures.length;
    }
}
