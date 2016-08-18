package com.vaani.algo.array;

/**
 * Given an int array A[], define: distance=A[i]+A[j]+(j-i), j>=i. Find max
 * distance in A[]?
 * O(n) time, O(1) space.
 */
public class MaxDistanceInArray {
    public int maxDistanceInArray(int[] arr) {
        int maxDistance = 0;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int distance = arr[left] + arr[right] + right - left;
            maxDistance = Math.max(maxDistance, distance);
            if (arr[left] <= arr[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return maxDistance;
    }
}
