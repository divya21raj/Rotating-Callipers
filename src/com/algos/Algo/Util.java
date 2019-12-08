package com.algos.Algo;

import com.algos.Ds.Point;

public class Util {
    static float getArea(Point A, Point B, Point C) {
        float area = (A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y)) / 2.0f;
        return Math.abs(area);
    }

    static double getDistance(Point a, Point b) {
        double x1 = a.x;
        double y1 = a.y;
        double x2=  b.x;
        double y2=  b.y;

        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
