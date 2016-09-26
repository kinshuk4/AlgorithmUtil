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
 * Problem: Task 2
 * Status: failed
 * 
 * Note: Once I saw that it fails for some test inputs
 * I fixed mistakes so this function should succeed.
 * Unfortunately I cannot check it after submission.
 * 
 * Problem
 * 
 * Given a matrix of integers where each cell contains color number.
 * Need to count all isolated areas of same color.
 * 
 * Sample Input
 * 
5, 4, 4
4, 3, 4
3, 2, 4
2, 2, 2
3, 3, 4
1, 4, 4
4, 1, 1
 * 
 * Sample Output
 * 
11
 * 
 */
// asked in tom tom
package com.vaani.algo.compete.codility;

import static org.junit.Assert.assertEquals;

public class Task2_Count_colored_areas {

    private static int countObjects(int[][] A) {
        int cnt = 0;
        for (int r = 0; r < A.length; r++) {
            int cand = A[r][0];
            boolean isNew = true;
            for (int c = 0; c < A[0].length; c++) {
                if (cand != A[r][c]) {
                    cnt += isNew? 1: 0;
                    cand = A[r][c];
                    isNew = true;
                }
                if (r > 0 && A[r - 1][c] == A[r][c])
                    isNew = false;
            }
            if (isNew) cnt++;
        }
        return cnt;        
    }
    
    public int solution(int[][] A) {
        return countObjects(A);
    }

    public static int solve(int[][] A) {
        return new Task2_Count_colored_areas().solution(A);
    }
    
    public static void main(String[] args) {
        assertEquals(11, solve(new int[][]{
                {5, 4, 4},
                {4, 3, 4},
                {3, 2, 4},
                {2, 2, 2},
                {3, 3, 4},
                {1, 4, 4},
                {4, 1, 1},
        }));
        assertEquals(5, solve(new int[][]{
            {0, 1, 1},
            {1, 3, 4},
        }));
    }
    
}
