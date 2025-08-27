package com.bankofjordan.math;
// class names Start with capital letter
// class names should be in camel case format
// Method names should start with small letters, then camel case
public class Point {
    // attributes
    // instance fields
    public int x;
    public int y;

    // capabilities (methods)
    // method
    // [access modified] [return type] [name]([parameters]) { [statements] }
    public int quadrantIdx() {
        if (x >= 0 && y >= 0) return 1;
        if (x < 0 && y >= 0) return 2;
        if (x < 0 && y < 0)
            return 3;
        return 4;
    }

    // method parameters
    public void set(int xPost, int yPost){
        x = xPost;
        y = yPost;
    }
}
