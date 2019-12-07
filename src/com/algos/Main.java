package com.algos;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        PointList<Point> points = new PointList<>();
        initPoints(points);

        Collections.reverse(points);

        ArrayList<PointList<Point>> pairs = getAllAntipodalPairs(points);
        for(PointList<Point> pair: pairs){
            pair.get(0).print();
            System.out.print(" : ");
            pair.get(1).print();
            System.out.println();
        }

        double diameter = 0; PointList<Point> finalPair = new PointList<>();
        for(PointList<Point> pair: pairs){
            double distance = getDistance(pair.get(0), pair.get(1));
            if(distance >diameter) diameter = distance;
            finalPair = pair;
        }

        System.out.println("---------------");
        System.out.println(diameter);
        finalPair.get(0).print();
        System.out.print(":");
        finalPair.get(1).print();
        System.out.println();
    }

    private static double getDistance(Point a, Point b) {
        double x1 = a.x;
        double y1 = a.y;
        double x2=  b.x;
        double y2=  b.y;

        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    private static ArrayList<PointList<Point>> getAllAntipodalPairs(ArrayList<Point> points) {
        ArrayList<PointList<Point>> pairs = new ArrayList<>();
        int i0 = points.size()-1, i = 1, j = i+1, j0 = 0;
        while(area(points.get(i), points.get(i+1), points.get(j+1)) >
                area(points.get(i), points.get(i+1), points.get(j))){
            j = j+1;
            j0 = j;
        }
        while(j != i0){
            i++;
            System.out.println(i + " " + j + " " + i0);
            yield(i, j ,pairs, points);
            while(j != i0 && area(points.get(i), points.get(i+1), points.get(j+1)) >
                    area(points.get(i), points.get(i+1), points.get(j))){
                j++;
                if(!(points.get(i).equals(points.get(j0)) && points.get(j).equals(points.get(i0))))
                    yield(i, j, pairs, points);
                else return pairs;
            }

            if(area(points.get(j), points.get(i+1), points.get(j+1)) >
                    area(points.get(i), points.get(i+1), points.get(j))){
                if(!(points.get(i).equals(points.get(j0)) && points.get(j).equals(points.get(i0))))
                    yield(i, j+1, pairs, points);
                else
                    yield(i+1, j, pairs, points);
            }
        }
        return pairs;
    }

    private static void yield(int i, int j, ArrayList<PointList<Point>> pairs, ArrayList<Point> points) {
        PointList<Point> pair = new PointList<>();
        pair.add(points.get(i));
        pair.add(points.get(j));

        pairs.add(pair);
    }

    private static float area(Point A, Point B, Point C) {
        float area = (A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y)) / 2.0f;
        return Math.abs(area);
    }

    private static void initPoints(ArrayList<Point> points) {
        points.add(new Point(235, 774));
        points.add(new Point(245, 740));
        points.add(new Point(230, 710));
        points.add(new Point(240, 703));
        points.add(new Point(274, 733));
        points.add(new Point(306, 710));
        points.add(new Point(272, 690));
        points.add(new Point(277, 639));
        points.add(new Point(305, 645));
        points.add(new Point(347, 611));
        points.add(new Point(340, 639));
        points.add(new Point(298, 674));
        points.add(new Point(325, 702));
        points.add(new Point(335, 663));
        points.add(new Point(355, 645));
        points.add(new Point(350, 686));
        points.add(new Point(400, 710));
        points.add(new Point(360, 725));
        points.add(new Point(357, 755));
        points.add(new Point(328, 723));
        points.add(new Point(291, 741));
        points.add(new Point(289, 754));
        points.add(new Point(266, 757));
    }
}
