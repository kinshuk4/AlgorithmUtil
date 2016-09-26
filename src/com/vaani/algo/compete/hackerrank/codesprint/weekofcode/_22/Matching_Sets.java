/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/08/2016
 * 
 * Hacker rank
 * Week of Code 22
 * Problem: Matching Sets
 * Status: accepted
 * 
 * Problem
 * 
 * Consider two n-element multisets (i.e., unordered and possibly 
 * containing duplicate elements) of integers, X = {x1, x2, .., xn}
 * and Y = {y1, y2, .., yn}. You can perform the following operation 
 * on set X:
 * 
 *  - Choose two elements at some postions xi and xj where 
 * 0 <= i,j <= n and i != j.
 *  - Decrement xi by 1 and increment xj by 1.
 * 
 * Given X and Y, find and print the minimum number of operations 
 * you must perform so that X is equal to Y (i.e., both sets contain 
 * the same exact values, and the order doesn't matter); if such a 
 * thing is not possible, print -1 instead.
 * 
 * Input Format
 * 
 * The first line contains a single integer, n.
 * The second line contains n space-separated integers describing the 
 * respective values of set X.
 * The third line contains n space-separated integers describing the 
 * respective values of set Y.
 * 
 * Output Format
 * 
 * Print a single integer denoting the minimum number of operations 
 * required to make set X equal to set Y; if no number of operations will 
 * ever make the two sets equal, print -1 instead.
 * 
 * Sample Input
 * 
3
1 2 3
-1 4 3
 * 
 * Sample Output
 * 
2
 * 
 */

package com.vaani.algo.compete.hackerrank.codesprint.weekofcode._22;

import static java.lang.Math.abs;
import static java.util.Arrays.setAll;
import static java.util.Arrays.sort;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matching_Sets {
    
    static long[] S1;
    static long[] S2;

    static void solve() {
        long pos = 0;
        long neg = 0;
        sort(S1);
        sort(S2);
        for (int i = 0; i < S1.length; i++) {
            long d = 0 - (S1[i] - S2[i]);
            if (d < 0)
                neg += d;
            else
                pos += d;
        }
        System.out.println(abs(neg) != pos? "-1": pos);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Matching_Sets.class.getResourceAsStream("Matching_Sets.in"));
        int n = scanner.nextInt();
        S1 = new long[n];
        S2 = new long[n];
        setAll(S1, i -> scanner.nextInt());
        setAll(S2, i -> scanner.nextInt());
        solve();
        scanner.close();
    }
    
}
