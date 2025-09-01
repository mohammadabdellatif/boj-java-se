package com.bankofjordan.math;

// Immutable class: once an instance is constructed from it, you can't change its state
public class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(final int xPos, final int yPost) {
        x = xPos;
        y = yPost;
    }

    public ImmutablePoint moveRight() {
        int newX = x + 1;
        int newY = y;
        return new ImmutablePoint(newX, newY);
    }

    public ImmutablePoint moveDown() {
    }

    public ImmutablePoint moveUp(){

    }

    public ImmutablePoint moveLeft(){

    }

    public String asCoordinates() {
        return "(" + x + ", " + y + ")";
    }

    public int xPos() {
        return x;
    }

    public int yPos() {
        return y;
    }
}
