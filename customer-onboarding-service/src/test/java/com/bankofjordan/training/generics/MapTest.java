package com.bankofjordan.training.generics;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<Integer, String> numbers = new HashMap<>();
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");
        System.out.println(numbers.get(1));
        System.out.println(numbers.get(2));
        System.out.println(numbers.get(3));

        System.out.println(numbers.put(1, "one again"));
        System.out.println(numbers.get(1));


        numbers.putIfAbsent(10, "ten");
        numbers.putIfAbsent(10, "ten again");
        System.out.println(numbers);

        System.out.println(numbers.containsKey(10));
        System.out.println(numbers.containsValue("ten"));
    }
}
