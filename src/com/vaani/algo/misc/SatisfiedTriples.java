package com.vaani.algo.misc;

/**
 * Given a sorted array, find out the number of triples whose sum is smaller
 * than or equal to a target value, e.g. a + b + c <= t.
 * <p>
 * Time complexity: O(N^2logN), space complexity: O(1).
 */
public class SatisfiedTriples {

    public int numSatisfiedTriples(int[] arr, int target) {
        int numPairs = 0;
        for (int i = 0; i < arr.length - 2; ++i) {
            for (int j = i + 1; j < arr.length - 1; ++j) {
                int left = j + 1, right = arr.length - 1;
                int tmpTarget = target - arr[i] - arr[j];
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (arr[mid] == tmpTarget) {
                        numPairs += mid - j;
                        break;
                    } else if (arr[mid] > tmpTarget) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                if (left > right) {  // the whole remaining array satisfied
                    numPairs += left - 1 - j;
                }
            }
        }
        return numPairs;
    }

}
