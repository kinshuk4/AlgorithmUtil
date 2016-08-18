package com.vaani.algo.array;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Created by Xiaomeng on 7/28/2014.
 */
public class SearchRotatedSortedArray {
    public static int search(int[] A, int target) {
        return search(A, target, 0, A.length - 1);
    }

    public static int search(int[] A, int target, int start, int end) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;
        if (target == A[start]) return start;
        if (target == A[mid]) return mid;
        if (target == A[end]) return end;

        if (A[start] < A[mid]) {
            if (target > A[start] && target < A[mid])
                return search(A, target, start + 1, mid - 1);
            else
                return search(A, target, mid + 1, end - 1);
        } else if (A[mid] < A[end]) {
            if (target > A[mid] && target <= A[end])
                return search(A, target, mid + 1, end - 1);
            else
                return search(A, target, start + 1, mid - 1);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] A = {4, 5, 6, 6, 7, 0, 1, 2, 2};
        System.out.println(search(A, 4));
    }
}
