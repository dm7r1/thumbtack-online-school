package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;

public class PairBox<T1 extends Figure, T2 extends Figure> implements HasArea {
    T1 first;
    T2 second;

    public PairBox(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getContentFirst() {
        return first;
    }
    public T2 getContentSecond() {
        return second;
    }

    public void setContentFirst(T1 first) {
        this.first = first;
    }

    public void setContentSecond(T2 second) {
        this.second = second;
    }

    public boolean isAreaEqual(PairBox another) {
        return getArea() == another.getArea();
    }

    static public boolean isAreaEqual(PairBox firstPB, PairBox secondPB) {
        return firstPB.getArea() == secondPB.getArea();
    }

    @Override
    public double getArea() {
        return first.getArea() + second.getArea();
    }
}
