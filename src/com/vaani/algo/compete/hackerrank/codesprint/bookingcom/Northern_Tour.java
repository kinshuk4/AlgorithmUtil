/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 07/08/2016
 * 
 * Hacker rank
 * Booking.com CodeSprint
 * Problem: Northern Tour
 * Status: accepted
 * 
 * Problem
 * 
 * You have a list of n cities: c1, c2, .., cn. To visit the ith city, 
 * you must spend vi hours in the city. You also have a list of e
 * bidirectional connections between the cities and the respective 
 * travel times (in hours) that it will take to travel between each 
 * pair of connected cities.
 * You and your friends live in the city of Bevagna. You want to 
 * plan an itinerary in such a way that you visit the maximum possible 
 * number of cities while satisfying the following rules:
 * 
 * - You start your journey in Bevagna on Monday at 8 AM and leave the 
 * last city on Sunday morning at 8 AM (at the latest) in order to return 
 * to Bevagna (do not take travel time home at the end of your trip into 
 * consideration). 
 * - You go to sleep at midnight (i.e., 12 AM) and wake up at 8 AM every day.
 * - You only sleep in a city you're already in, so there is no sleep 
 * mid-travel.
 * - You cannot visit the same city twice.
 * - You must be awake to visit a city, so sleeping time does not count 
 * toward elapsed visit time.
 * - You cannot leave a city or complete your journey without spending the 
 * full vi hours there. This means that you must finish visiting the last 
 * city before ending your journey.
 * 
 * Given the city and travel route lists described above, create a travel 
 * itinerary that maximizes the number of cities visited during the allotted 
 * time specified above. Then print your itinerary as a list of city names in 
 * the order in which you will need to visit them. It's guaranteed that the 
 * answer is unique.
 * 
 * Input Format
 * 
 * The first line contains an integer, n, denoting the number of cities in 
 * the list.
 * Each line i of the n subsequent lines describes a city in the form of two 
 * comma-separated values, ci (the city name) and vi (the minimum amount of 
 * time you must spend visiting it). 
 * 
 * The next line contains a single integer, e, denoting the number of connections. 
 * 
 * Each of the subsequent e lines describes a bidirectional connection between two 
 * cities in the form of three comma-separated values. The first two values are 
 * strings, u and w, describing a bidirectional connection between cities u and w; 
 * the third value is an integer, h, describing the number of hours needed to 
 * travel between the two cities.
 * 
 * Output Format
 * 
 * Print your itinerary as a list of city names in the order in which you plan to 
 * visit them. Each city name must be printed on a new line; if there is no solution 
 * satisfying the criteria above, print NONE instead.
 * 
 * Sample Input
 * 
4
Roma,38
Florence,10
Perugia,12
Ancona,12
4
Bevagna,Roma,14
Bevagna,Perugia,1
Roma,Florence,4
Roma,Perugia,2
 * 
 * Sample Output
 * 
Perugia
Roma
Florence
 * 
 */

package com.vaani.algo.compete.hackerrank.codesprint.bookingcom;

import static java.util.Arrays.fill;
import static java.util.Arrays.setAll;
import static java.util.Arrays.stream;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Northern_Tour {
    
    static final String SRC = "Bevagna";
    
    static int N;
    static String[] IC;
    static Map<String, Integer> C = new HashMap<>();
    static int[][] E;
    static int[] VT;
    
    static void reverse(int[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }
    
    static int[] buildItinerary(int i, int d, int h, int left) {
        int[] max = {};
        if (left == 0) return new int[]{i};
        for (int j = 0; j < N; j++) {
            int jthbit = 1 << (N - j - 1);
            if ((left & jthbit) == 0)
                continue;
            if (E[i][j] == -1 || E[i][j] > 16)
                continue;
            int hh = h + E[i][j];
            int dd = d;
            if (hh > 24) {
                dd++;
                hh = 8 + E[i][j];
            }
            hh += VT[j];
            while (hh > 24) {
                dd++;
                hh -= 24;
                hh += 8;
            }
            if (dd >= 7)
                continue;
            int[] r = buildItinerary(j, dd, hh, left & ~jthbit);
            max = r.length > max.length? r: max;
        }
        max = Arrays.copyOf(max, max.length + 1);
        max[max.length - 1] = i;
        return max;
    }
    
    static void solve() {
        int[] r = buildItinerary(0, 1, 8, ~(-1 << N - 1));
        if (r.length == 1) {
            System.out.println("NONE");
            return;
        }
        reverse(r);
        stream(r).skip(1).mapToObj(i -> IC[i] + " ").forEach(System.out::println);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Northern_Tour.class.getResourceAsStream("Northern_Tour.in"));
        scanner.useDelimiter(",| |\n");
        N = scanner.nextInt() + 1;
        IC = new String[N];
        VT = new int[N];
        IC[0] = SRC;
        C.put(SRC, 0);
        for (int i = 1; i < N; i++) {
            String city = scanner.next();
            C.put(city, i);
            IC[i] = city;
            VT[i] = scanner.nextInt();
        }
        int[] t = new int[N];
        fill(t, -1);
        E = new int[N][];
        setAll(E, i -> t.clone());
        int e = scanner.nextInt();
        for (int i = 0; i < e; i++) {
            int v = C.get(scanner.next());
            int u = C.get(scanner.next());
            int p = scanner.nextInt();
            E[v][u] = p;
            E[u][v] = p;
        }
        solve();
        scanner.close();
    }
    
}
