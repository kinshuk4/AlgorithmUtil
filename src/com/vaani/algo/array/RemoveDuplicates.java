package com.vaani.algo.array;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array A = [1,1,2],
 * Your function should return length = 2, and A is now [1,2].
 * <p>
 * Created by Xiaomeng on 7/20/2014.
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] A) {
        if (A.length <= 1) return A.length;
        int first = 0, second = 1;
        while (second < A.length) {
            if (A[first] == A[second]) {
                second++;
            } else {
                first++;
                A[first] = A[second];
                second++;
            }
        }
        return first + 1;
    }
}
