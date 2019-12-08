package com.algos.Ds;

import java.util.ArrayList;

public class PointList<P> extends ArrayList<P> {
    @Override
    public P get(int index) {
        index = index%super.size();
        return super.get(index);
    }
}
