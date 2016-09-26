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
 * 101 Hack Jun 2016
 * Problem: Easy GCD
 * Status: timeout
 * 
 * We call a sequence of n non-negative integers, A, awesome if there 
 * exists some positive integer x > 1 such that each element ai in A
 * (where 0 <= i < n) is evenly divisible by x. Recall that a evenly 
 * divides b if there exists some integer c such that a = b * c.
 * Given an awesome sequence, A, and a positive integer, k, find and 
 * print the maximum integer, l, satisfying the following conditions:
 * 
 * 1. 0 <= l <= k
 * 2. A U {l} is also awesome
 * 
 * Input Format
 * 
 * The first line contains two space-separated positive integers, n
 * (the length of sequence A) and k (the upper bound on answer l), 
 * respectively.
 * The second line contains space-separated positive integers describing 
 * the respective elements in sequence A (i.e., a0, a1, .. an-1).
 * 
 * Output Format
 * 
 * Print a single, non-negative integer denoting the value of l (i.e., 
 * the maximum integer <= k such that A U {l} is awesome). As 0 is evenly 
 * divisible by any x > 1, the answer will always exist. 
 * 
 * Sample Input
 * 
3 5
2 6 4
 *
 * Sample Output
 * 
4
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.hack101._2016.jun;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Easy_GCD {

    static int[] N;
    static int K;
    static int[][] GCD = new int[10000][1000];
    
    static int gcd(int a, int b) {
        boolean save = a < GCD.length && b < GCD[a].length;
        if (save && GCD[a][b] != 0) return GCD[a][b];
        int gcd = a;
        if (b != 0) gcd = gcd(b, a % b);
        if (save) GCD[a][b] = gcd;
        return gcd;
    }
    
    static int gcd(int[] n) {
        Arrays.sort(N);
        int gcd = N[0];
        for (int i = 1; i < N.length; i++) {
            gcd = gcd(N[i], gcd);
        }
        return gcd;
    }
    
    static int solve() {
        int gcd = gcd(N);
        if (K == gcd) return gcd;
        if (K < gcd) {
            for (int i = K; i > 1; i--) {
                if (gcd % i == 0) return i;
                if (gcd(i, gcd) != 1) return i;
            }
        } else {
            for (int i = K; i > gcd; i--) {
                if (i % gcd == 0) return i;
                if (gcd(i, gcd) != 1) return i;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Easy_GCD.class.getResourceAsStream("Easy_GCD.in"));
        N = new int[scanner.nextInt()];
        K = scanner.nextInt();
        Arrays.setAll(N, i -> scanner.nextInt());
        System.out.println(solve());
        scanner.close();
    }

}
