package com.bankofjordan.training;

import com.bankofjordan.math.ImmutablePoint;

public class ImmutableTest {

    public static void main(String[] args) {
        ImmutablePoint p1 = new ImmutablePoint(1, 2);
        System.out.println(p1.asCoordinates());
        System.out.println("move right");
        ImmutablePoint movedPoint = p1.moveRight();
        System.out.println(p1.asCoordinates());
        System.out.println(movedPoint.asCoordinates());
    }
}
