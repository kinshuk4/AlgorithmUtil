/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 11/06/2016
 *
 * Hacker rank
 * BlackRock CodeSprint
 * Problem: Trade Analysis
 * Status: accepted
 * 
 * Problem
 * 
 * Investment managers are always trying to find new information about 
 * the market to better their competitive advantage and help increase 
 * their holdings. In her quest to find such information, your boss asks 
 * you to analyze a set of dollar amounts from previous trades according 
 * to formula G(S). She can then combine the resultant number with other 
 * information to gain crucial insight about the market.
 * Given an -element sequence, S = (a1..an), of dollar amounts, calculate 
 * and print the value of function G(S).
 * 
 *   G(S) = sum<T:non empty subsequences S>(|T| * (product of all numbers in T))
 * 
 * where |T| is the length of the sequence T. As the value of G(S) can be 
 * large, print the result modulo 1_000_000_007.
 * 
 * Input Format
 * 
 * The first line contains an integer denoting the value of N.
 * The second line contains space-separated integers a1..an, describing the 
 * elements in S.
 * 
 * Output Format
 * 
 * Print the value of G(S) mod 1_000_000_007 on a new line.
 * 
 * Input
 * 
3
1 2 3
 * 
 * Output
 * 
46
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.blackrock;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Trade_Analysis {

    static long[] G = new long[10_0000];
    static long[] T = new long[10_0000];
    
    static long solve(int[] a) {
        for (int i = 0; i < a.length; i++) {
            G[i] += a[i];
            int s = i + 1;
            for (int l = 1; l < s; l++) {
                G[i] += (T[l] * l) % 1_000_000_007;
                G[i] %= 1_000_000_007;
                G[i] += ((T[l] * a[i]) % 1_000_000_007) * (l + 1);
                G[i] %= 1_000_000_007;
            }
            for (int l = s; l >= 1; l--) {
                T[l] += a[i] * (l == 1? 1: T[l - 1]);
                T[l] %= 1_000_000_007;
            }
        }
        return G[a.length - 1];
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Trade_Analysis.class.getResourceAsStream("Trade_Analysis.in"));
        int[] a = new int[scanner.nextInt()];
        Arrays.setAll(a, i -> a[i] = scanner.nextInt());
        System.out.println(solve(a));
        scanner.close();
    }

}
