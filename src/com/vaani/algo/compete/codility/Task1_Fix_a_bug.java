/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 20/07/2016
 * 
 * TomTom Codility
 * Problem: Task 1
 * Status: passed
 * 
 * Problem
 * 
 * Given a function find and correct mistakes in it.
 * Function finds min period P of the binary string, where:
 *  P <= length of the string L
 *  S[k] = S[k + P] for each 0 <= k < L - P 
 * 
 * Sample Input
 * 
955
 * 
 * Sample Output
 * 
4
 * 
 */
//asked in tom tom
package com.vaani.algo.compete.codility;

import static org.junit.Assert.assertEquals;

public class Task1_Fix_a_bug {

    public int solution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0 && n <= 1_000_000_000) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (p = 1; p <= l / 2; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p;
            }
        }
        return -1;
    }
    
    public static int solve(int S) {
        return new Task1_Fix_a_bug().solution(S);
    }
    
    public static void main(String[] args) {
        assertEquals(4, solve(955));
        assertEquals(-1, solve(11));
        assertEquals(2, solve(21));
        assertEquals(-1, solve(Integer.MAX_VALUE));
        assertEquals(-1, solve(0b1011_1011_0));
        assertEquals(-1, solve(1_000_000_000));
        assertEquals(-1, solve(0b111111110));
    }
    
}
