package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        String input = interceptInput(getScannerFromInputFile("vidi"));

        Solution.main(input);
    }


    public static String interceptInput(Scanner in) {
        StringBuilder sb = new StringBuilder();
        int numberOfVideos = in.nextInt();
        int numberOfEndpoints = in.nextInt();
        int numberOfRequests = in.nextInt();
        int numberOfCaches = in.nextInt();
        int capacityOfEachCache = in.nextInt();

        sb.append(numberOfVideos + " ");
        sb.append(numberOfEndpoints + " ");
        sb.append(numberOfRequests + " ");
        sb.append(numberOfCaches + " ");
        sb.append(capacityOfEachCache + " \n");
        for (int i = 0; i < numberOfVideos; i++) {
            sb.append(in.nextInt() + " ");
        }
        sb.append("\n");
        for (int i = 0; i < numberOfEndpoints; i++) {
            sb.append(in.nextInt() + " ");
            int numberOfCachesFor = in.nextInt();
            sb.append(numberOfCachesFor + "\n");
            for (int j = 0; j < numberOfCachesFor; j++) {
                sb.append(in.nextInt() + " ");
                sb.append(in.nextInt() + "\n");
            }
        }
        for (int i = 0; i < numberOfRequests; i++) {
            sb.append(in.nextInt() + " ");
            sb.append(in.nextInt() + " ");
            sb.append(in.nextInt() + "\n");
        }
        return sb.toString();
    }

    public static void writeOutput(String out) throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.write(out.trim());
        pw.close();
    }

    public static Scanner getScannerFromInputFile(String fileName) throws FileNotFoundException {
        return new Scanner(new FileInputStream(new File("Input/" + fileName)));
    }

    public static City createClearCity(String input) {
        Scanner in = new Scanner(input);
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
