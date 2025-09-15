package com.bankofjordan.training.generics;

// Generic type (label): type safety feature
public class Box<T extends Object> {
    // General class. Works with any subtype of Object
    private T item;

    public T replace(T item) {
        T i = null;
        if (this.item != null)
            i = remove();
        put(item);
        return i;
    }

    public void put(T item) {
        if (this.item != null)
            throw new IllegalStateException("Box already contains an item");
        this.item = item;
    }

    public T remove() {
        if (this.item == null)
            throw new IllegalStateException("Box is empty");
        T i = this.item;
        this.item = null;
        return i;
    }
}
