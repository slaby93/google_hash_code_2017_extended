package com.company;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Solution {


    public static void main(String input, int threadId, int numberOfIterationr) throws IOException {
        long score = 0;
        City sol = null;

        for (int i = 0; i < numberOfIterationr; i++) {
            City c = Main.createClearCity(input);
//            fillRandomlyCity(c);
            long tmp = c.computeScore();
            if (tmp > score) {
                sol = c;
                score = tmp;
            }
            System.out.println("threadId:" + threadId + " //   " + tmp);
        }
        System.out.println("FINAL FINAL SCORE " + score);
        Main.addResult(score, sol.returnOutput());


    }


    public static City fillRandomlyCity(City in) {
        int numberOfCaches = in.cacheList.size();
        List<Video> videoList = in.videoList;
        List<Cache> cacheList = in.cacheList;

        while (in.canFitMore()) {
            for (Endpoint e : in.endpointList) {
                for (Video v : e.getVideos()) {
                    for (Cache c : e.getCaches()) {
                        if(canPutVideoToCache(v,c)){
                            c.addVideo(v);
                        }
                    }
                }
            }
        }
        return in;
    }

    public static boolean canPutVideoToCache(Video video, Cache c) {
        return c.capacity >= video.size && !c.doesContainVideo(video);
    }

    public static int randomBetween(int minimum, int maximum) {
        return minimum + (int) (Math.random() * maximum);

    }

}
