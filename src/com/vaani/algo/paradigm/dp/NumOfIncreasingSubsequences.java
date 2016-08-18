package com.vaani.algo.paradigm.dp;

/**
 * Count the number of distinct increasing subsequences in a given array.
 */
public class NumOfIncreasingSubsequences {

    // O(n) space, O(n^2) time
    public long numOfIncreasingSubsequences(int[] arr) {
        long sum = 0;
        // record the number of distinct increasing subsequences end at i
        long[] count = new long[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            count[i] = 1; //
            for (int j = 0; j < i; ++j) {
                if (arr[j] < arr[i]) {
                    count[i] += count[j];
                }
            }
        }

        // summing all the numbers
        for (long c : count) {
            sum += c;
        }

        return sum;
    }

}
