/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 21/06/2016
 * 
 * Hacker rank
 * 101 Hack Aug 2016
 * Problem: Lazy Mayor and Lasers
 * Status: accepted
 * 
 * The Mayor of Byteland wants to shorten some buildings that are 
 * obstructing Byteland's skyline.
 * There are n vertical buildings having heights h1, h2, .., hn. 
 * Each building can be assumed to be of infinitesimally small 
 * width. The base of building i is located at position on the 
 * x-axis. This means the first building is at position 1, the 
 * second is at position 2, and so on.
 * The Mayor decides to use lasers to reduce the height of the 
 * buildings using a laser with an infinite beam which can be 
 * placed at a position on x-axis. However, this laser can be fired 
 * only at a 45 angle in direction of the negative x-axis. When the 
 * laser passes through an existing building, the part of the 
 * building above the laser is destroyed. Note that the laser 
 * doesn't affect the building at the position from which the laser 
 * was fired.
 * The Mayor orders that lasers be fired from positions xm1, xm2, .. xmn, 
 * one by one. Help the Mayor by finding and printing the remaining 
 * heights of each building after all m lasers are fired.
 * 
 * Input Format
 * 
 * The first line contains a single integer, n, denoting the number 
 * of buildings.
 * The second line contains n space-separated integers denoting the 
 * respective initial heights of the buildings (i.e., h1, h2, .. hn).
 * The third line contains a single integer, m, denoting the number 
 * of lasers that will fire.
 * The fourth line contains m space-separated integers describing the 
 * respective positions from which the lasers will be fired 
 * (i.e., xm1, xm2, .. xmm).
 * 
 * Output Format
 * 
 * Print a single integer denoting the sum of the remaining building 
 * heights after all the lasers are fired.
 * 
 * Sample Input
 * 
5
3 1 4 5 1
2
3 5
 *
 * Sample Output
 * 
7
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.hack101._2016.aug;

import static java.util.Arrays.setAll;
import static java.util.Arrays.sort;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lazy_Mayor_and_Lasers {
    
    static long[] H;
    static int[] L;
    
    static void solve() {
        sort(L);
        long S = 0;
        for (int i = 0; i < L.length; i++) {
            int s = i > 0? L[i - 1]: 0;
            int lh = 1;
            for (int j = L[i] - 1; j >= s; j--) {
                if (H[j] > lh)
                    S += lh;
                else
                    S += H[j];
                lh++;
            }
        }
        for (int i = L[L.length - 1]; i < H.length; i++)
            S += H[i];
        System.out.println(S);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Lazy_Mayor_and_Lasers.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Lazy_Mayor_and_Lasers.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            H = new long[scanner.nextInt()];
            setAll(H, i-> scanner.nextLong());
            L = new int[scanner.nextInt()];
            setAll(L, i-> scanner.nextInt() - 1);
            solve();
        }
        scanner.close();
    }

}
