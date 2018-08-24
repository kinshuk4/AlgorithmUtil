package com.vaani.algo.paradigm.dp;

/*
Maximum sub array sum

use dynamic programming
*/

public class MaxSubarraySum {
    public static int getMaximumSubarraySum(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }
        if (inputArray.length == 1) {
            return inputArray[0];
        }


        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        for (int i = 0; i < inputArray.length; i++) {
            maxEndingHere = maxEndingHere + inputArray[i];
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
            }


            if (maxEndingHere < 0) {
                maxEndingHere = 0;
            }

        }


        return maxSoFar;
    }
}
