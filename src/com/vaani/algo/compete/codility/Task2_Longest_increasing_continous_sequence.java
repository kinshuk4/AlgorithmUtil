/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 25/07/2016
 * 
 * PO Codility
 * Problem: Longest increasing continous sequence
 * Status: passed
 * 
 * Problem
 * 
 * Given sequence of numbers need to find length of the 
 * longest increasing continuous subsequence and return it.
 * 
 * Input Format
 * 
 * Sequence of numbers
 * 
 * Output Format
 * 
 * Length of longest increasing continuous subsequence
 * 
 * Sample Input
 * 
2, 2, 2, 2, 1, 2, -1, 2, 1, 3
 * 
 * Sample Output
 * 
4
 * 
 */
//asked in po
package com.vaani.algo.compete.codility;

import static org.junit.Assert.assertEquals;

public class Task2_Longest_increasing_continous_sequence {

    public int solution(int[] A) {
        if (A.length == 0) return 0;
        int max = 1;
        int s = 0;
        int cs = 0; // candidate s
        for (int i = s + 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) continue;
            if (i - cs > max) {
                max = i - cs;
                s = cs;
            }
            cs = i;
        }
        return A.length - cs > max? cs: s;
    }
    
    public static int solve(int[] A) {
        return new Task2_Longest_increasing_continous_sequence().solution(A);
    }
    
    public static void main(String[] args) {
        assertEquals(4, solve(new int[]{2, 2, 2, 2, 1, 2, -1, 2, 1, 3}));
        assertEquals(0, solve(new int[]{2, 1, 0}));
        assertEquals(0, solve(new int[]{30, 20, 10}));
        assertEquals(3, solve(new int[]{1, 2, 3, 1, 2, 3, 4}));
        assertEquals(0, solve(new int[]{1, 2, 3, 4, 2, 3, 4}));
        assertEquals(0, solve(new int[]{1, 2, 3, 0, -4, -3, 4, 2, 3, 4}));
        assertEquals(3, solve(new int[]{1, 2, 3, -5, -4, -3, 4, 2, 3, 4}));
        assertEquals(0, solve(new int[]{1, 2, 3}));
        assertEquals(0, solve(new int[]{1}));
        assertEquals(4, solve(new int[]{1, 2, 1, 2, 2, 3, 4}));
    }
    
}
