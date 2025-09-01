package com.bankofjordan.math;

public class ArrayTest {

    public static void main(String[] args) {
        int[] arr1 = new int[3];// primitive array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        Point[] points = new Point[2];
        points[0] = new Point(0,1);
        points[1] = new Point(10,10);

        points[0].move(1, 2);

        Point temp = points[1];
        temp.atCenter();
        temp.quadrantIdx();
//        temp.isXNegative();
    }
}
