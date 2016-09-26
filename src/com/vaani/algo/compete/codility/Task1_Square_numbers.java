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
 * Problem: Square numbers
 * Status: passed
 * 
 * Problem
 * 
 * Count how many numbers have their squares in the given 
 * interval lo..hi
 * 
 * Input Format
 * 
 * A, B
 * 
 * Output Format
 * 
 * How many numbers have squares in the inteval [A, B] 
 * 
 * Sample Input
 * 
4 17
 * 
 * Sample Output
 * 
3
 * 
 */
//asked in po
package com.vaani.algo.compete.codility;

import static org.junit.Assert.assertEquals;

public class Task1_Square_numbers {

    public int solution(int A, int B) {
        if (B < 0) return 0;
        if (A < 0) A = 0;
        int s = (int) Math.sqrt(A);
        int e = (int) Math.sqrt(B);
        int c = 0;
        for (int i = s; i <= e; i++) {
            int v = i * i;
            if (A <= v && v <= B)
                c++;
        }
        return c;
    }
    
    public static int solve(int A, int B) {
        return new Task1_Square_numbers().solution(A, B);
    }
    
    public static void main(String[] args) {
        assertEquals(3, solve(4, 17));
        assertEquals(3, solve(4, 16));
        assertEquals(0, solve(5, 6));
        assertEquals(0, solve(5, 8));
        assertEquals(1, solve(5, 9));
        assertEquals(1, solve(100, 102));
        assertEquals(1, solve(4, 4));
        assertEquals(1, solve(-4, 0));
        assertEquals(0, solve(-4, -1));
        assertEquals(2, solve(4, 9));
    }
    
}
