/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 29/06/2016
 * 
 * Cimpress Codility
 * Problem: Task 3
 * Status: passed
 * 
 * Problem
 * 
 * Given integer array and number K. Print how many pairs in
 * the array sums to K (are K-complimentary)
 * 
 * Sample Input
 * 
1, 8, -3, 0, 1, 3, -2, 4, 5
K = 6
 * 
 * Sample Output
 * 
7
 * 
 */
// asked in cimpress
package com.vaani.algo.compete.codility;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class Task3_K_complimentary_pairs {
   
    public int solution(int K, int[] A) {
        Arrays.sort(A);
        int c = 0;
        for (int i = 0; i < A.length; i++) {
            int b = K - A[i];
            int l = binSearch(A, b, 0, A.length - 1);
            if (l == -1 || A[l] != b) continue;
//            System.out.println(A[i] + " " + b);
            int r = binSearch(A, b + 1, 0, A.length - 1);
            if (r == -1) 
                r = A.length - 1;
            else
                r--;
            c += (r - l) + 1;
        }
        return c;
    }

    /*
     * Use binary search to find index where elements v
     * begins or index of the first element bigger than v. 
     * Search range is [s, e].
     * If all elements are less than v -1 is returned.
     */
    static int binSearch(int[] a, int v, int s, int e)
    {
        if (e < s)
            return -1;
        if (e == s) {
            if (a[e] >= v)
                return s;
            else
                return -1;
        }
        int m = (e - s) / 2 + s;
        if (a[m] < v)
            return binSearch(a, v, m + 1, e);
        else {
            int p = binSearch(a, v, s, m - 1);
            return p == -1? m: p;
        }
    }
    
    public static int solve(int K, int[] A) {
        return new Task3_K_complimentary_pairs().solution(K, A);
    }
    
    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 3, 3, 4, 4, 4, 5};
        assertEquals(2, binSearch(a, 3, 0, a.length - 1));
        assertEquals(0, binSearch(a, 1, 0, a.length - 1));
        assertEquals(0, binSearch(a, -1, 0, a.length - 1));
        assertEquals(4, binSearch(a, 4, 0, a.length - 1));
        assertEquals(-1, binSearch(a, 7, 0, a.length - 1));
        assertEquals(2, binSearch(a, 2, 0, a.length - 1));
        assertEquals(7, binSearch(a, 5, 0, a.length - 1));
        
        assertEquals(7, solve(6, new int[]{1, 8, -3, 0, 1, 3, -2, 4, 5}));
        assertEquals(4, solve(6, new int[]{6, 0, 0}));
        assertEquals(0, solve(6, new int[]{4, 0, 0}));
        assertEquals(5, solve(6, new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(4, solve(8, new int[]{4, 4}));
        assertEquals(2, solve(-1, new int[]{-4, -1, -2, 3, 2}));
    }
    
}
