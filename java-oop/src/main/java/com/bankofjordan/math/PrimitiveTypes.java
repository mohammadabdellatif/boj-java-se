package com.bankofjordan.math;

public class PrimitiveTypes {

    /*

     */
    public static void main(String[] args) {
        // primitive data types
        // integral numbers (no decimal places)
        byte b = -127; // 1 byte = 8 bits
        short s = 23_456; // 2 bytes = 16 bits
        int i = 899_808_098; // 4 bytes = 32 bits
        long l = 980_809_890_808_980L; // 8 bytes = 64 bits

        // floating point numbers (with decimal places)
        float f = 123.99f; // 4 bytes = 32 bits
        double d = 123.76; // 8 bytes = 64 bits
        i = 0xFF;
        i = 07766;
        // character
        char c = 'A'; // 2 bytes = 16 bits

        l = b;
        i = (int) l;

        short s1 = 0b00000001_00000001;// 0000000 00000001
        byte b1;

        b1 = (byte) s1;
        System.out.println(s1);
        System.out.println(b1);

        System.out.println("byte range: " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
        System.out.println("short range: " + Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
        System.out.println("int range: " + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
        System.out.println("long range: " + Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
    }
}
