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
 * Problem: Extreme numbers
 * Status: passed
 * 
 * Problem
 * 
 * Given an array of integers find the extreme number in it and print its index.
 * Extreme it is difference between number and the average of array.
 * 
 * Input Format
 * 
 * Integer array
 * 
 * Output Format
 * 
 * Index of extreme
 * 
 * Sample Input
 * 
9, 4, -3, -10
 * 
 * Sample Output
 * 
3
 * 
 */
//asked in po
package com.vaani.algo.compete.codility;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class Task3_Extreme_numbers {

    public int solution(int[] A) {
        
        if (A.length == 0) 
            return -1;
        
        int m = Arrays.stream(A).sum() / A.length;
        
        // candidate index
        int ci = 0;
        
        // candidate extreme
        int ce = 0; 
        
        for (int i = 0; i < A.length; i++) {
            int extreme = Math.abs(A[i] - m);
            if (extreme < ce) 
                continue;
            ce = extreme;
            ci = i;
        }
        
        return ci;
    }
    
    public static int solve(int[] A) {
        return new Task3_Extreme_numbers().solution(A);
    }
    
    public static void main(String[] args) {
        assertEquals(3, solve(new int[]{9, 4, -3, -10}));
        assertEquals(-1, solve(new int[0]));
        assertEquals(0, solve(new int[]{3}));
        assertEquals(1, solve(new int[]{3, 1, 6, 4, 6}));
        assertEquals(1, solve(new int[]{-3, -1, -6, -4, -6}));
        assertEquals(2, solve(new int[]{-33234, -1123, -644444, 23144, 456546}));
    }
    
}
