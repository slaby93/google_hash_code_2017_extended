package com.company;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Cache {
    public int capacity;
    public final int id;

    Cache(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "(" + this.id + " , " + this.capacity + ")";
    }
}
