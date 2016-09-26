/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 02/12/2015
 *
 * Hacker rank
 * Problem: Bike Racers
 * Status: accepted
 * 
 * Problem
 * 
 * There are N bikers present in a city (shaped as a grid) having M bikes. 
 * All the bikers want to participate in the HackerRace competition, but 
 * unfortunately only K bikers can be accommodated in the race. Jack is 
 * organizing the HackerRace and wants to start the race as soon as possible. 
 * He can instruct any biker to move towards any bike in the city. In order to 
 * minimize the time to start the race, Jack instructs the bikers in such a way 
 * that the first K bikes are acquired in the minimum time.
 * 
 * Every biker moves with a unit speed and one bike can be acquired by only one 
 * biker. A biker can proceed in any direction. Consider distance between bikes 
 * and bikers as Euclidean distance.
 * 
 * Jack would like to know the square of required time to start the race as 
 * soon as possible. 
 * 
 * Input Format
 * 
 * The first line contains three integers, N, M, and K, separated by a single space.
 * The following N lines will contain N pairs of integers denoting the co-ordinates 
 * of N bikers. Each pair of integers is separated by a single space. The next M 
 * lines will similarly denote the co-ordinates of the M bikes.
 * 
 * Output Format
 * 
 * A single line containing the square of required time.
 * 
 * Input
 * 
3 3 2
0 1
0 2
0 3
100 1
200 2 
300 3
 * 
 * Output
 * 
40000
 * 
 */
package com.vaani.algo.compete.hackerrank.misc;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntUnaryOperator;

public class BikeRacers {

    static Point[] bikers, bikes;
    static int N, M, K;
    static List<Integer>[] G;
    static int[] Matching;
    static boolean[] visited;
    
    static int sqrDistance(Point p1, Point p2) {
        return (int) (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
    
    @SuppressWarnings("unchecked")
    static List<Integer>[] buildGraph(int t) {
        List<Integer>[] g = new List[bikers.length + bikes.length];
        Arrays.setAll(g, (i) -> new ArrayList<Integer>());
        for (int biker = 0; biker < bikers.length; ++biker) {
            for (int bike = 0; bike < bikes.length; ++bike) {
                if (sqrDistance(bikers[biker], bikes[bike]) > t)
                    continue;
                g[biker].add(bikers.length + bike);
                g[bikers.length + bike].add(biker);
            }
        }
        return g;
    }
    
    static boolean augment(int v) {
        if (visited[v]) return false;
        visited[v] = true;
        for(int a: G[v])
        {
            if (visited[a]) continue;
            if (Matching[a] == -1 || augment(Matching[a]))
            {
                Matching[a] = v;
                Matching[v] = a;
                return true;
            }   
        }
        return false;
    }
    
    static int countMatches(int t) {
        G = buildGraph(t);
        Matching = new int[G.length];
        Arrays.fill(Matching, -1);
        for (int v = 0; v < G.length; ++v) 
        {
            visited = new boolean[G.length];
            if (Matching[v] == -1)
                augment(v);
        }
        int c = (int) Arrays.stream(Matching).limit(bikers.length).filter(v -> v != -1).count();
        if (c == K) return 0;
        if (c < K) return -1;
        return 1;
    }
    
    /*
     * Applies lambda for values in range s..e.
     * Lambda return codes:
     *  negative - current value is small
     *  0 or positive - current value is big
     *  Returns latest big value encountered.
     */
    static int binarySearch(int s, int e, IntUnaryOperator oper) {
        if (e - s < 0) return -1;
        int m = (s + e) / 2;
        int r = oper.applyAsInt(m);
        if (r < 0)
            return binarySearch(m + 1, e, oper);
        int res = binarySearch(s, m - 1, oper);
        return res == -1? m: res;
    }

    static long solve() {
        return binarySearch(0, Integer.MAX_VALUE, BikeRacers::countMatches);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(BikeRacers.class.getResourceAsStream("BikeRacers.in"));
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        bikers = new Point[N];
        bikes = new Point[M];
        Arrays.setAll(bikers, i -> new Point(scanner.nextInt(), scanner.nextInt()));
        Arrays.setAll(bikes, i -> new Point(scanner.nextInt(), scanner.nextInt()));
        System.out.println(solve());
        scanner.close();
    }
    
}
