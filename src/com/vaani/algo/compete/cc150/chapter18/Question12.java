package com.vaani.algo.compete.cc150.chapter18;


/**
 * Given an NxN matrix of positive and negative integers, write code to find the
 * submatrix with the largest possible sum.
 */
// O(n) space, O(n^3) time
public class Question12 {

    /**
     * @param matrix
     * @return return the max.
     */
    public int maxSubMatrix(int[][] matrix) {
        // write implementation here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int start = 0; start < matrix.length; ++start) {
            int[] arr = new int[matrix[0].length];
            for (int end = start; end < matrix.length; ++end) {
                for (int i = 0; i < matrix[0].length; ++i) {
                    arr[i] += matrix[end][i];
                }
                max = Math.max(max, maxArea(arr));
            }
        }
        return max;
    }

    // use max subarray to find max
    private int maxArea(int[] arr) {
        // compress
        int max = Integer.MIN_VALUE;
        int maxSoFar = 0;
        for (int i = 0; i < arr.length; ++i) {
            maxSoFar = Math.max(0, maxSoFar + arr[i]);
            max = Math.max(max, maxSoFar);
        }
        return max;
    }

}
