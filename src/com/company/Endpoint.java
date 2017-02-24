package com.company;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Endpoint implements Serializable {
    public int id;
    Map<Cache, Integer> connectedCaches;
    Map<Video, Integer> requests;
    public final int latencyToDataCenter;
    private List<Video> listOfVideos;
    private List<Cache> listOfCaches;

    Endpoint(int id, int latToDataCenter) {
        this.id = id;
        this.latencyToDataCenter = latToDataCenter;
        connectedCaches = new HashMap<>();
        requests = new HashMap<>();
        listOfVideos = new ArrayList<>();
        listOfCaches = new ArrayList<>();
    }

    public List<Video> getVideos() {
        return this.listOfVideos;
    }

    public List<Cache> getCaches() {
        return this.listOfCaches;
    }

    public int[] computeScore() {
        final int[] score = {0};
        final int[] sumOfRequests = {0};
        this.getVideos().forEach(video -> {
            int lowestLat = findLowestLatForVideo(video);
            int numberOfRequests = this.requests.get(video);
            score[0] += lowestLat * numberOfRequests;
            sumOfRequests[0] += numberOfRequests;
        });
        int finalScore = score[0] / sumOfRequests[0];
        return new int[]{score[0], sumOfRequests[0]};

    }

    private int findLowestLatForVideo(Video v) {
        final int[] minimalLatency = {latencyToDataCenter};
        this.getCaches().forEach(cache -> {
            if (cache.doesContainVideo(v)) {
                if (minimalLatency[0] > this.connectedCaches.get(cache)) {
                    minimalLatency[0] = this.connectedCaches.get(cache);
                }
            }

        });
        return latencyToDataCenter - minimalLatency[0];
//        return minimalLatency[0];
    }

    public void addCache(Cache c, Integer latency) {
        listOfCaches.add(c);
        listOfCaches.sort(Comparator.comparingInt(a -> a.id));
        connectedCaches.put(c, latency);
    }

    public void addRequest(Video v, Integer numberOfRequests) {
        listOfVideos.add(v);
        listOfVideos.sort(Comparator.comparingInt(a -> a.id));
        requests.put(v, numberOfRequests);
    }

}
