package com.vaani.algo.array;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * <p>
 * Created by Xiaomeng on 7/29/2014.
 */
public class SearchForRange {
    int[] result = new int[2];

    public static void main(String[] args) {
        SearchForRange test = new SearchForRange();
        int[] A = {1};
        int[] result = test.searchRange(A, 1);
        System.out.println(result[0] + " " + result[1]);
    }

    public int[] searchRange(int[] A, int target) {
        result[0] = -1;
        result[1] = -1;
        return searchRange(A, target, 0, A.length - 1);
    }

    public int[] searchRange(int[] A, int target, int start, int end) {
        if (start > end) return result;
        int mid = (start + end) / 2;
        if (target < A[mid]) {
            return searchRange(A, target, start, mid - 1);
        } else if (target > A[mid]) {
            return searchRange(A, target, mid + 1, end);
        } else {
            int left = mid - 1, right = mid + 1;
            while (left >= start && A[left] == A[mid]) left--;
            while (right <= end && A[right] == A[mid]) right++;
            result[0] = left + 1;
            result[1] = right - 1;
            return result;
        }
    }
}
