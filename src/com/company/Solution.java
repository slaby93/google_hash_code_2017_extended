package com.company;

import java.io.IOException;

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
            c.cacheList.get(0).addVideo(c.videoList.get(2));
            c.cacheList.get(1).addVideo(c.videoList.get(3));
            c.cacheList.get(1).addVideo(c.videoList.get(1));
            c.cacheList.get(2).addVideo(c.videoList.get(0));
            c.cacheList.get(2).addVideo(c.videoList.get(1));
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

            for (Endpoint e : in.endpointList) {
                for (Video v : e.getVideos()) {
                    for (Cache c : e.getCaches()) {
                        if(canPutVideoToCache(v,c)){
                            c.addVideo(v);
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
