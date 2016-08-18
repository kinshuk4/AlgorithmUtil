package com.vaani.algo.misc;

import java.util.Arrays;

/**
 * Given an array and a sum s. Find a number k such that the sum of the elements
 * in the array is equal to s if the elements who is larger than k is replaced
 * with k.
 */
public class ReplaceElemToGetSum {

    public int replaceElemToGetSum(int[] arr, int sum) {

        Arrays.sort(arr);
        int l = 0;
        int r = arr[arr.length - 1];

        while (l < r) {
            int k = (l + r) / 2;
            int curSum = 0;
            for (int val : arr) {
                if (val < k) {
                    curSum += val;
                } else {
                    curSum += k;
                }
            }
            if (curSum == sum) {
                return k;
            } else if (curSum < sum) {
                l = k;
            } else {
                r = k;
            }
        }

        return l;
    }

}
