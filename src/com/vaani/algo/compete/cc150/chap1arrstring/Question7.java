package com.vaani.algo.compete.cc150.chap1arrstring;

import java.util.Arrays;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire
 * row and column are set to 0. Do this in place.
 */
// O(1) space, O(n) space, n is size of matrix
public class Question7 {

    //  this problem is available at leetcode, problem Set Matrix Zeros
    public void setZeroes(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        boolean firstRowZero = false;
        // scan and mark
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[r].length; ++c) {
                if (matrix[r][c] == 0) {
                    if (r == 0) {
                        firstRowZero = true;  // set row marker to 0
                    } else {
                        matrix[r][0] = 0; // set row marker to 0
                        matrix[0][c] = 0; // set column marker to 0
                    }
                }
            }
        }

        // set matrix by row markers
        for (int r = 1; r < matrix.length; ++r) {
            if (matrix[r][0] == 0) {
                Arrays.fill(matrix[r], 0);
            }
        }

        // set matrix by col markers
        for (int c = 0; c < matrix[0].length; ++c) {
            if (matrix[0][c] == 0) {
                for (int r = 1; r < matrix.length; ++r) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (firstRowZero) {
            Arrays.fill(matrix[0], 0);
        }

    }

}

