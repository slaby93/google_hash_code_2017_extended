package com.company;

import java.io.*;
import java.util.List;

/**
 * Created by Slaby on 24.02.2017.
 */
public class City implements Serializable {

    List<Cache> cacheList;
    List<Endpoint> endpointList;
    List<Video> videoList;

    City(List<Cache> caches, List<Endpoint> endpoints, List<Video> videos) {
        cacheList = caches;
        endpointList = endpoints;
        videoList = videos;
    }

    public long computeScore() {
        final long[] score = {0};
        this.endpointList.forEach(endpoint -> {
            score[0] += endpoint.computeScore();
        });
        return score[0];
    }

    public String returnOutput() {
        StringBuilder sb = new StringBuilder();
        final int[] numberOfUsedCacheServers = {0};
        this.cacheList.forEach(cache -> {
            if (cache.listOfVideos.size() > 0) {
                ++numberOfUsedCacheServers[0];
            }
        });
        sb.append(numberOfUsedCacheServers[0] + "\n");
        this.cacheList.forEach(cache -> {
            if (cache.listOfVideos.size() > 0) {
                sb.append(cache.id + " ");
                cache.listOfVideos.forEach(vid -> {
                    sb.append(vid.id + " ");
                });
                sb.append("\n");
            }
        });
        return sb.toString();
    }

    public City deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (City) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
