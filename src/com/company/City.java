package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Slaby on 24.02.2017.
 */
public class City implements Serializable {

    List<Cache> cacheList;
    List<Endpoint> endpointList;
    List<Video> videoList;
    int smallestVideoSize = Integer.MAX_VALUE;

    City(List<Cache> caches, List<Endpoint> endpoints, List<Video> videos, int smallestVideoSize) {
        cacheList = caches;
        endpointList = endpoints;
        videoList = videos;
        this.smallestVideoSize = smallestVideoSize;
    }

    public boolean canFitMore() {
        boolean result = false;
        for (Cache cache : cacheList) {
            if (cache.capacity >= smallestVideoSize) {
                result = true;
            }
        }
        return result;
    }

    public long computeScore() {
        long finalScore = 0;
        long numberOfRequests = 0;
        long[] result;
        for(Endpoint e : endpointList){
            result = e.computeScore();
            finalScore += result[0];
            numberOfRequests += result[1];
        }
        return (finalScore*1000)/numberOfRequests;
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
