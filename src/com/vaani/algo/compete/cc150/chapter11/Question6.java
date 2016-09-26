package com.vaani.algo.compete.cc150.chapter11;

/**
 * Given an M x N matrix in which each row and each column is sorted, write a
 * method to find an element.
 */
public class Question6 {

    // this question is available at leetcode, question Search a 2D matrix
    // O(1) space, O(n + m) time
    public class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            // Start typing your Java solution below
            // DO NOT write main() function
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int r = 0;
            int c = matrix[0].length - 1;
            while (true) {
                if (r >= matrix.length || c < 0) {
                    break;
                }
                if (matrix[r][c] == target) {
                    return true;
                } else if (matrix[r][c] < target) {
                    ++r;
                } else {
                    --c;
                }
            }
            return false;
        }
    }


}
