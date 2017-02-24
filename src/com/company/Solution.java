package com.company;

import java.io.IOException;

/**
 * Created by Slaby on 24.02.2017.
 */
public class Solution {


    public static void main(String input) throws IOException {
        int score = 0;
        City sol = null;
        for (int i = 0; i < 10; i++) {
            City c = Main.createClearCity(input);
            fillRandomlyCity(c);
            int tmp = c.computeScore();
            System.out.println(tmp);
            if(tmp > score){
                sol = c;
                score = tmp;
            }
        }
        System.out.println(score);
        Main.writeOutput(sol.returnOutput());


    }


    public static City fillRandomlyCity(City in) {
        int numberOfCaches = in.cacheList.size();
        in.videoList.forEach(video -> {
            Cache c = in.cacheList.get(randomBetween(0, numberOfCaches));
            if (c.capacity >= video.size) {
                c.addVideo(video);
            }
        });
        return in;
    }

    public static int randomBetween(int minimum, int maximum) {
        return minimum + (int) (Math.random() * maximum);

    }

}
