package com.bankofjordan.training;

public class StringTest {
    public static void main(String[] args) {
        char[] name = {'J', 'a', 'v', 'a'};// string of characters

        String n1 = new String(name);
        String n2 = "mohammad";
        String countryCode = "JOR";
        String countryCD = "JOR";

        System.out.println("n2: " + n2);
        String n3 = n2.toUpperCase();
        System.out.println("n3: " + n3);
        System.out.println("n2: " + n2);
    }
}
