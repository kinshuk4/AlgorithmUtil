package com.vaani.algo.compete.cc150.chapter17;

/**
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum. EXAMPLE Input: 2,
 * -8, 3, -2, 4, -10 Output: 5
 */
// O(1) space, O(n) time
public class Question8 {

    public int largestContinuousSum(int[] arr) {
        //  write implementation here
        int max = arr[0];
        int maxSoFar = arr[0];
        int largestNeg = arr[0] < 0 ? arr[0] : Integer.MIN_VALUE;
        boolean allNeg = true;

        for (int i = 1; i < arr.length; ++i) {
            if (allNeg == true && arr[i] >= 0) {
                allNeg = false;
            }
            largestNeg = arr[i] < 0 && arr[i] > largestNeg ? arr[i] : largestNeg;
            maxSoFar = Math.max(maxSoFar + arr[i], 0);
            max = Math.max(max, maxSoFar);
        }
        if (allNeg) {
            return largestNeg;
        }
        return max;
    }

}
