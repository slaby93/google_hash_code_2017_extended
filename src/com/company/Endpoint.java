package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Endpoint {
    public int id;
    Map<Cache, Integer> connectedCaches;
    Map<Video, Integer> requests;
    public final int latencyToDataCenter;

    Endpoint(int id, int latToDataCenter) {
        this.id = id;
        this.latencyToDataCenter = latToDataCenter;
        connectedCaches = new HashMap<>();
        requests = new HashMap<>();
    }

    public void addCache(Cache c, Integer latency) {
        connectedCaches.put(c, latency);
    }

    public void addRequest(Video v, Integer numberOfRequests) {
        requests.put(v, numberOfRequests);
    }

}
