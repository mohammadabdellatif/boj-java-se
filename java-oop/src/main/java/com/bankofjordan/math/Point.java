package com.bankofjordan.math;
// SOLID:
// - Single Responsibility Principle,
// - OCP Open Closed Principle,
// - Liskov Substitution Principle,
// - ISP Interface Segregation Principle,
// - Dependency Inversion Principle

// Encapsulation: encapsulate the relevant data and algorithms, the
// encapsulation (class) should have the full control over the members

import com.bankofjordan.training.Main;

// class names Start with capital letter
// class names should be in camel case format
// Method names should start with small letters, then camel case
// no modified: package view (default)
public class Point {
    // mutable class, I can modify its state after construction

    // attributes
    // instance fields
    private int x;
    private int y;

    // [access modifier] ClassName([args]) { }
    public Point(final int xPos, final int yPost) {
        // Default no argument constructor
        x = xPos;
        y = yPost;
    }

    // capabilities (methods)
    // method
    // [access modified] [return type] [name]([parameters]) { [statements] }
    public int quadrantIdx() {
        if (!isNegativeX() && !isNegativeY())
            return 1;
        if (isNegativeX() && !isNegativeY())
            return 2;
        if (isNegativeX() && isNegativeY())
            return 3;
        return 4;// !NegativeX and NegativeY
    }

    public String asCoordinates() {
        return "(" + x + ", " + y + ")";
    }

    public void move(int xPost, int yPost) {
        x = xPost;
        y = yPost;
    }

    public void moveRight() {
        x += 1;
    }

    public void moveLeft() {
        x -= 1;
    }

    public void moveUp() {
        y += 1;
    }

    public void moveDown() {
        y -= 1;
    }

    public void atCenter() {
        x = y = 0;
    }

    private boolean isNegativeY() {
        return y < 0;
    }

    private boolean isNegativeX() {
        return x < 0;
    }
}
