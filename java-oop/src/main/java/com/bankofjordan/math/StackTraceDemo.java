package com.bankofjordan.math;

public class StackTraceDemo {

    public static void main(String[] args) {
        int f = 10;
        int s = 20;
        int r = sum(f, s);
        System.out.println(r);
    }

    private static int sum(int l, int r) {
        int result = l + r;
        return result;
    }
}
