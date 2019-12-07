package com.algos;

import java.util.ArrayList;
import java.util.Collections;

import static com.algos.Util.getArea;
import static com.algos.Util.getDistance;

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


    ArrayList<PointList<Point>> getAllAntipodalPairs() {
        Collections.reverse(points);
        pairs = new ArrayList<>();
        int i0 = points.size()-1, i = 1, j = i+1, j0 = 0;
        while(getArea(points.get(i), points.get(i+1), points.get(j+1)) >
                getArea(points.get(i), points.get(i+1), points.get(j))){
            j = j+1;
            j0 = j;
        }
        while(j != i0){
            i++;
            System.out.println(i + " " + j + " " + i0);
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

    static PointList<Point> getDiameterPoints(){
        double diameter = 0; PointList<Point> finalPair = new PointList<>();
        for(PointList<Point> pair: pairs){
            double distance = getDistance(pair.get(0), pair.get(1));
            if(distance >diameter) diameter = distance;
            finalPair = pair;
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
