package com.bankofjordan.math;

public class MathMain {

    /*

     */
    public static void main(String[] args) {
        // constructed two instances of class Point
        // new operator = construct a new instance (object) of type Point
        Point p1 = new Point();
        Point p2 = new Point();
        Point p3 = p1;
        // write access
        p1.x = 1;
        p1.y = 1;

        int xPost = 2;
        int yPost = 2;
        p2.set(xPost, yPost);
        p2.set(2, 2);

        p3.x = 2;

        // calling a method, not inputs ()
        int q = p1.quadrant();
        // read access
        System.out.println(p1.x + " " + p1.y);
        System.out.println(p2.x + " " + p2.y);
        System.out.println(p3.x + " " + p3.y);
        System.out.println("p1 quadrant: " + p1.quadrant());
        System.out.println("p2 quadrant: " + p2.quadrant());
        System.out.println("p1 quadrant: " + q);
    }
}
