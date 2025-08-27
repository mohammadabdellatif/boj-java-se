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
        Point p = new Point();
        p.set(xPost, yPost);
        return p;
    }

    public static void movePoint(Point p1) {
        p1 = new Point();
        int newX = p1.x + 1;
        int newY = p1.y - 1;
        p1.set(newX, newY);
    }

    private static void printPoint(Point p) {
        System.out.println(p.quadrantIdx());
        System.out.println(p.x + "," + p.y);
    }
}
