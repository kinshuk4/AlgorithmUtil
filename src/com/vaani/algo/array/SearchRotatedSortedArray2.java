package com.vaani.algo.array;

/**
 * Follow up for "Search in Rotated Sorted Array":
 * <p>
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Write a function to determine if a given target is in the array.
 * <p>
 * Created by Xiaomeng on 7/29/2014.
 */
public class SearchRotatedSortedArray2 {
    public boolean search(int[] A, int target) {
        return search(A, target, 0, A.length - 1);
    }

    public boolean search(int[] A, int target, int start, int end) {
        if (start > end) return false;
        int mid = start + (end - start) / 2;
        if (target == A[start] || target == A[mid] || target == A[end]) return true;

        while (A[mid] == A[start] && start < mid) {
            start++;
        }

        while (A[mid] == A[end] && end > mid) {
            end--;
        }

        if (A[start] <= A[mid]) {
            if (target > A[start] && target < A[mid])
                return search(A, target, start, mid - 1);
            else
                return search(A, target, mid + 1, end);
        } else if (A[mid] <= A[end]) {
            if (target > A[mid] && target <= A[end])
                return search(A, target, mid + 1, end);
            else
                return search(A, target, start, mid - 1);
        }
        return false;
    }

    /**
     * Another solution
     */
    public boolean search2(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[start] == target) return true;
            if (A[mid] == target) return true;
            if (A[end] == target) return true;

            if (A[start] < A[mid]) {
                if (target > A[start] && target < A[mid]) {
                    end = mid - 1;
                    start++;
                } else {
                    start = mid + 1;
                    end--;
                }
            } else if (A[mid] < A[end]) {
                if (target > A[mid] && target < A[end]) {
                    start = mid + 1;
                    end--;
                } else {
                    end = mid - 1;
                    start++;
                }
            } else {
                start++;
            }
        }
        return false;
    }
}
