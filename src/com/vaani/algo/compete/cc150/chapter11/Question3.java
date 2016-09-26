package com.vaani.algo.compete.cc150.chapter11;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of
 * times, write code to find an element in the array. You may assume that the
 * array was originally sorted in increasing order.
 * <p>
 * EXAMPLE Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14} Output:
 * 8 (the index of 5 in the array)
 */
public class Question3 {

    // this question is available at leetcode, question Search in Rotated Sorted
    // Array
    public class Solution {
        public int search(int[] A, int target) {
            // Start typing your Java solution below
            // DO NOT write main() function
            return search(A, target, 0, A.length - 1);
        }

        private int search(int[] A, int target, int left, int right) {
            if (left > right || right < 0) {
                return -1;
            }
            int mid = (left + right) / 2;
            int vMid = A[mid];
            if (target == vMid) {
                return mid;
            }

            if (A[left] < vMid) { // left in order
                if (A[left] <= target && target < vMid) { // in range of left
                    return search(A, target, left, mid - 1);
                } else {
                    return search(A, target, mid + 1, right);
                }
            } else if (A[left] > vMid) {  // right in order
                if (vMid <= target && target <= A[right]) { // in range of right
                    return search(A, target, mid + 1, right);
                } else {
                    return search(A, target, left, mid - 1);
                }
            } else {
                if (A[mid] != A[right]) {
                    return search(A, target, mid + 1, right);
                } else {
                    int idx = search(A, target, left, mid - 1);
                    if (idx != -1) {
                        idx = search(A, target, mid + 1, right);
                    }
                    return idx;
                }
            }

        }
    }


}
