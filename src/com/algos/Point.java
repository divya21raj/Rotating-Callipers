package com.algos;

import java.util.Objects;

public class Point {
    int x, y;
    int id;

    public Point(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void print() {
        System.out.print(x + " " + y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Float.compare(point.x, x) == 0 &&
                Float.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void printId() {
        System.out.print(this.id);
    }
}
