package com.vaani.algo.misc;

/**
 * Given a sorted array, count the number of pairs such that the sum of two
 * elements is smaller than or equal to a target value, e.g. a + b <= c.
 * <p>
 * Time complexity: O(NlogN), space complexity: O(1)
 */
public class SatisfiedPairs {

    public int numSatisfiedPairs(int[] arr, int target) {
        int numPairs = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            int left = i + 1, right = arr.length - 1, tmpTarget = target - arr[i];
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] == tmpTarget) {
                    numPairs += mid - i;
                    break;
                } else if (arr[mid] > tmpTarget) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (left > right) {  // the whole remaining array satisfied
                numPairs += left - 1 - i;
            }
        }
        return numPairs;
    }

}
