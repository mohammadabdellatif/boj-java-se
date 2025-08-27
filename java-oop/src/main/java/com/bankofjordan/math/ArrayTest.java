package com.bankofjordan.math;

public class ArrayTest {

    public static void main(String[] args) {
        int[] arr1 = new int[3];// primitive array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        Point[] points = new Point[2];
        points[0] = new Point();
        points[1] = new Point();

        points[0].x = 1;
        points[0].y = 2;

        Point temp = points[1];
        temp.x = 3;
        temp.y = 4;
    }
}
