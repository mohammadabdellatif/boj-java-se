package com.bankofjordan.training.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountCharactersTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter line: ");
        String line = scanner.nextLine();
        line = line.toLowerCase();
        char[] chars = line.toCharArray();
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : chars) {
//            if (counts.containsKey(c)) {
//                counts.put(c, counts.get(c) + 1);
//            } else {
//                counts.put(c, 1);
//            }
            counts.merge(c, 1, (current, newVal) -> current + newVal);
        }
        counts.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
