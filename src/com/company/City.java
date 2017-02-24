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
}
