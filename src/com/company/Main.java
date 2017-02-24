package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = getScannerFromInputFile("basic.txt");

        City enterCity = handleInput(in);
        enterCity.cacheList.get(0).addVideo(enterCity.videoList.get(2));
        enterCity.cacheList.get(1).addVideo(enterCity.videoList.get(3));
        enterCity.cacheList.get(1).addVideo(enterCity.videoList.get(1));
        enterCity.cacheList.get(2).addVideo(enterCity.videoList.get(0));
        enterCity.cacheList.get(2).addVideo(enterCity.videoList.get(1));
//        System.out.println(enterCity.computeScore());
//        System.out.println();
         writeOutput(enterCity.returnOutput());
    }

    public static void writeOutput(String out) throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.write(out.trim());
        pw.close();
    }

    public static Scanner getScannerFromInputFile(String fileName) throws FileNotFoundException {
        return new Scanner(new FileInputStream(new File("Input/" + fileName)));
    }

    public static City handleInput(Scanner in) {
        int numberOfVideos = in.nextInt();
        int numberOfEndpoints = in.nextInt();
        int numberOfRequests = in.nextInt();
        int numberOfCaches = in.nextInt();
        int capacityOfEachCache = in.nextInt();

        //////////////////////////////// CREATE CACHES ////////////////////////////////
        List<Cache> listOfCaches = new ArrayList<>();
        for (int i = 0; i < numberOfCaches; i++) {
            listOfCaches.add(new Cache(i, capacityOfEachCache));
        }
        ///////////////////////////////////////////////////////////////////////////////


        //////////////////////////////// CREATE VIDEOS ////////////////////////////////
        List<Video> listOfVideos = new ArrayList<>();
        for (int i = 0; i < numberOfVideos; i++) {
            int sizeOfVideo = in.nextInt();
            listOfVideos.add(new Video(i, sizeOfVideo));
        }
        ///////////////////////////////////////////////////////////////////////////////

        /////////////////////////////// CREATE ENDPOINTS //////////////////////////////
        List<Endpoint> listOfEndpoints = new ArrayList<>();
        for (int i = 0; i < numberOfEndpoints; i++) {
            int latencyToDataCenter = in.nextInt();
            int numberOfConnectedCaches = in.nextInt();
            Endpoint tmp = new Endpoint(i, latencyToDataCenter);

            for (int j = 0; j < numberOfConnectedCaches; j++) {
                int cacheId = in.nextInt();
                int latencyToCache = in.nextInt();

                tmp.addCache(listOfCaches.get(cacheId), latencyToCache);
            }

            listOfEndpoints.add(tmp);


        }


        ///////////////////////////////////////////////////////////////////////////////

        /////////////////////////////// CREATE ENDPOINTS //////////////////////////////
        for (int i = 0; i < numberOfRequests; i++) {
            int videoId = in.nextInt();
            int endpointId = in.nextInt();
            int numberOfTimesVideoIsRequested = in.nextInt();
            listOfEndpoints.get(endpointId).addRequest(listOfVideos.get(videoId), numberOfTimesVideoIsRequested);
        }
        ///////////////////////////////////////////////////////////////////////////////
        return new City(listOfCaches, listOfEndpoints, listOfVideos);
    }

}
