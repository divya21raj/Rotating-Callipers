package com.algos.Algo;

import com.algos.Ds.Point;
import com.algos.Ds.PointList;

import java.util.ArrayList;
import java.util.Collections;

import static com.algos.Algo.Util.getArea;
import static com.algos.Algo.Util.getDistance;

public class Shamos {
    static ArrayList<PointList<Point>> pairs = new ArrayList<>();
    static PointList<Point> points = new PointList<>();


    public Shamos(PointList<Point> points) {
        Shamos.points = points;
        pairs.clear();
    }

    public void clean(){
        points.clear();
        pairs.clear();
    }


    public ArrayList<PointList<Point>> getAllAntipodalPairs() {
        Collections.reverse(points);
        for(Point point: points) System.out.println(point.id);
        pairs = new ArrayList<>();
        int i0 = points.size()-1, i = 1, j = i+1, j0 = 0;
        while(getArea(points.get(i), points.get(i+1), points.get(j+1)) >
                getArea(points.get(i), points.get(i+1), points.get(j))){
            j = j+1;
            j0 = j;
        }
        while(j != i0){
            i++;
            yield(i, j ,pairs, points);
            while(j != i0 && getArea(points.get(i), points.get(i+1), points.get(j+1)) >
                    getArea(points.get(i), points.get(i+1), points.get(j))){
                j++;
                if(!(points.get(i).equals(points.get(j0)) && points.get(j).equals(points.get(i0))))
                    yield(i, j, pairs, points);
                else return pairs;
            }

            if(getArea(points.get(j), points.get(i+1), points.get(j+1)) >
                    getArea(points.get(i), points.get(i+1), points.get(j))){
                if(!(points.get(i).equals(points.get(j0)) && points.get(j).equals(points.get(i0))))
                    yield(i, j+1, pairs, points);
                else
                    yield(i+1, j, pairs, points);
            }
        }
        return pairs;
    }

    public static PointList<Point> getDiameterPoints(){
        double diameter = 0; PointList<Point> finalPair = new PointList<>();
        for(PointList<Point> pair: pairs){
            double distance = getDistance(pair.get(0), pair.get(1));
            if(distance >diameter){
                diameter = distance;
                finalPair = pair;
            }
            pair.get(0).printId();
            System.out.print(" : ");
            pair.get(1).printId();
            System.out.print(" - ");
            System.out.print(distance);
            System.out.print(" - ");
            System.out.println(diameter);
        }

        return finalPair;
    }

    private static void yield(int i, int j, ArrayList<PointList<Point>> pairs, ArrayList<Point> points) {
        PointList<Point> pair = new PointList<>();
        pair.add(points.get(i));
        pair.add(points.get(j));

        pairs.add(pair);
    }
}
