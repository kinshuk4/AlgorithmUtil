package com.vaani.algo.array;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * <p>
 * Created by Xiaomeng on 7/30/2014.
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition test = new SearchInsertPosition();
        int[] A = {1, 3, 5, 6};
        System.out.println(test.searchInsert(A, 0));
    }

    public int searchInsert(int[] A, int target) {
        return searchInsert(A, target, 0, A.length - 1);
    }

    public int searchInsert(int[] A, int target, int start, int end) {
        if (start > end) {
            return start;
        }

        int mid = start + (end - start) / 2;
        if (target < A[mid]) {
            return searchInsert(A, target, start, mid - 1);
        } else if (target > A[mid]) {
            return searchInsert(A, target, mid + 1, end);
        } else {
            return mid;
        }
    }
}
