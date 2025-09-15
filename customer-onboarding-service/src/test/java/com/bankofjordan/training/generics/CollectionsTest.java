package com.bankofjordan.training.generics;

import java.util.*;

public class CollectionsTest {

    public static void main(String[] args) {
        Collection<String> collection;

        // store a sequence of objects and access them by index (like array)
        List<String> list;

        // store a group of objects and assure they are unique (no duplicates)
        Set<String> set;

        // sequence of objects that can be accessed in both directions
        Queue<String> queue;
        Deque<String> deque;

        list = new LinkedList<>();
        list.add("John");
        list.add("Jane");
        String value = list.get(0);
        System.out.println(value);

        Set<String> set1 = new LinkedHashSet<>();
        System.out.println(set1.add("Jane"));
        System.out.println(set1.add("John"));
        System.out.println(set1.add("John"));
        System.out.println(set1);

        for (String s : set1) {
            System.out.println(s);
        }

        // the stored object should implements Comparable
        Set<String> set2 = new TreeSet<>();
        set2.add("b");
        set2.add("A");
        set2.add("A");
        set2.add("C");
        set2.add("B");
        set2.add("B");

        System.out.println(set2);

    }
}
