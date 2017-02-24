package com.company;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Video {
    public int id;
    public int size;

    Video(int id, int size){
        this.id = id;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Video("+id +" , " + size+")";
    }
}
