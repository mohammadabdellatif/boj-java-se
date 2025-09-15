package com.bankofjordan.training.generics;

public class ObjectReferenceTest {

    public static void main(String[] args) {
        Box<Pencil> pencilBox = new Box<>();
        Box<Pen> penBox = new Box<>();
        Box<Stationery> stationeryBox = new Box<>();

        penBox.replace(new Pen());
        pencilBox.replace(new Pencil());
        Pencil pencil = pencilBox.remove();
        System.out.println(pencil);

        stationeryBox.replace(new Pen());
        stationeryBox.replace(new Pencil());
        Stationery stationery = stationeryBox.remove();
        System.out.println(stationery);
    }
}
