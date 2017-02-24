package com.company;

import java.util.List;

/**
 * Created by Slaby on 24.02.2017.
 */
public class City {

    List<Cache> cacheList;
    List<Endpoint> endpointList;
    List<Video> videoList;

    City(List<Cache> caches, List<Endpoint> endpoints, List<Video> videos) {
        cacheList = caches;
        endpointList = endpoints;
        videoList = videos;
    }

    public int computeScore() {
        final int[] score = {0};
        final int[] totalRequests = {0};
        this.endpointList.forEach(endpoint -> {
            int[] sct = endpoint.computeScore();
            score[0] += sct[0];
            totalRequests[0] += sct[1];
        });
        return ((score[0] * 10) / totalRequests[0]) * 100;
    }
}