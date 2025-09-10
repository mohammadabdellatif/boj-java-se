package com.bankofjordan.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CollectionsTest {

    public static void modern_main() {
        Arrays.asList("John", "Jane", "Jill").forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Jane");
        names.add("Jill");
        System.out.println("=====normal for loop====");
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }
        System.out.println("=====enhanced for loop (for each)====");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("=====loop through forEach functional method====");
        Consumer<String> namePrinter = new NamePrinter();
        names.forEach(namePrinter);
        System.out.println("==========using lambda============");
//        Consumer<String> consumer = n -> System.out.println(n);
        Consumer<String> consumer = System.out::println;
        names.forEach(consumer);
        names.forEach(System.out::println);
    }

    public static class NamePrinter implements Consumer<String> {

        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    }
}
