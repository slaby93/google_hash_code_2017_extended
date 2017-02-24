package com.company;

import java.io.Serializable;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Video implements Serializable {
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
