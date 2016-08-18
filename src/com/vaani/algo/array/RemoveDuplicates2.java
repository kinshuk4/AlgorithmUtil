package com.vaani.algo.array;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * <p>
 * Created by Xiaomeng on 7/20/2014.
 */
public class RemoveDuplicates2 {
    public static int removeDuplicates(int[] A) {
        if (A.length <= 2) return A.length;
        int first = 1, second = 2;
        while (second < A.length) {
            if (A[first] == A[second] && A[first - 1] == A[second]) {
                second++;
            } else {
                first++;
                A[first] = A[second];
                second++;
            }
        }
        return first + 1;
    }

    public static void main(String[] args) {
        int[] A = {1, 1, 1, 1, 3, 3};
        int len = removeDuplicates(A);
        for (int i = 0; i < len; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
