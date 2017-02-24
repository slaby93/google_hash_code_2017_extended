package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Cache implements Serializable {
    public int capacity;
    public final int id;
    public List<Video> listOfVideos;

    Cache(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.listOfVideos = new ArrayList<>();
    }

    public void addVideo(Video v) {
        this.capacity -= v.size;
        if(this.capacity < 0 || this.doesContainVideo(v)){
            throw new Error("Blad przy wrzucaniu video do cache");
        }
        listOfVideos.add(v);
    }

    public Video getVideo(int id) {
        return listOfVideos.stream().filter(item -> item.id == id).collect(Collectors.toList()).get(0);
    }

    public boolean doesContainVideo(Video v) {
        return this.listOfVideos.contains(v);
    }

    @Override
    public String toString() {
        return "(" + this.id + " , " + this.capacity + ")";
    }
}
