package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = getScannerFromInputFile("basic.txt");
        handleInput(in);

    }

    public static Scanner getScannerFromInputFile(String fileName) throws FileNotFoundException {
        return new Scanner(new FileInputStream(new File("Input/" + fileName)));
    }

    public static void handleInput(Scanner in) {
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
    }
}
