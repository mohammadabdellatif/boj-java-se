package com.bankofjordan.math;

public class StackTraceDemo2 {

    public static void main(String[] args) {
        int x = 1;
        int y = 0;
        Point p = createPoint(x, y);
        printPoint(p);
        printPoint(p);
    }

    public static Point createPoint(int xPost, int yPost) {
        xPost++;
        yPost--;
        Point p = new Point(xPost, yPost);
//        p.move(xPost, yPost);
        return p;
    }

    public static void movePoint(Point p1) {
        p1 = new Point(0, 0);
        p1.moveRight();
        p1.moveDown();
    }

    private static void printPoint(Point p) {
        System.out.println(p.quadrantIdx());
    }
}
