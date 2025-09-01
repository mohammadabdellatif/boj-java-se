package com.bankofjordan.training;

import com.bankofjordan.math.Point;

public class Main {

    public static void main(final String[] args) {
        final int x = 10;
        final int y = 9;
        Point p1 = new Point(x, y);
        Point p2 = new Point(10, -8);
        System.out.println(p1.asCoordinates());
    }
}
