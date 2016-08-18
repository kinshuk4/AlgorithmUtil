package com.vaani.algo.array;

import java.util.Arrays;

/**
 * Given two arrays: arr1[0..m-1] and arr2[0..n-1]. Find whether arr2[] is a subset of arr1[] or not. Both the arrays are not in sorted order.
 * <p>
 * Examples:
 * Input: arr1[] = {11, 1, 13, 21, 3, 7}, arr2[] = {11, 3, 7, 1}
 * Output: arr2[] is a subset of arr1[]
 * <p>
 * Input: arr1[] = {1, 2, 3, 4, 5, 6}, arr2[] = {1, 2, 4}
 * Output: arr2[] is a subset of arr1[]
 * <p>
 * Input: arr1[] = {10, 5, 2, 23, 19}, arr2[] = {19, 5, 3}
 * Output: arr2[] is not a subset of arr1[]
 */
public class CheckSubsetOfAnotherArray {
    /**
     * m - length of arr1
     * n - length of arr2
     * <p>
     * Method 1: Brute force - O(mn)
     * Method 2: Sorting and Binary Search - O(mlogm + nlogm)
     * Method 3: Sort and merging - O(mlogm + nlogn)
     * Method 4: Hasing - O(n) space O(n) time
     * <p>
     * Note that method 1, method 2 and method 4 don’t handle the cases when we have duplicates in arr2[].
     * For example, {1, 4, 4, 2} is not a subset of {1, 4, 2}, but these methods will print it as a subset.
     */
    public static boolean isSubset(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;

        if (m < n) return false;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0, j = 0;
        while (i < m && j < n) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                return false;
            }
        }
        return j == n;
    }
}
