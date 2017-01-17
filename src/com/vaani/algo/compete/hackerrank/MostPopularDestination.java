package com.vaani.algo.compete.hackerrank;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
/*
 * Part of Skyscanner Coding Challenge
 */
public class MostPopularDestination {
    static void printMostPopularDestination(int count, Scanner in) {
        Hashtable<String, Integer> destinationSearched = new Hashtable<String, Integer>();
        String next = "";
        for (int i = 0; i < count; i++) {
            next = in.nextLine();
            if (destinationSearched.containsKey(next)) {
                destinationSearched.put(next, destinationSearched.get(next) + 1);
            } else {
                destinationSearched.put(next, 1);
            }
        }
        int max = Collections.max(destinationSearched.values());
         for (String destination : destinationSearched.keySet()) {
            if (destinationSearched.get(destination) == max) {
                System.out.println(destination);
                return;
            }
        }
}
}
