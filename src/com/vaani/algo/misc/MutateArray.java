package com.vaani.algo.misc;

/**
 * Given an input array and another array that describes a new index for each element,
 * mutate the input array so that each element ends up in their new index.
 * Discuss the runtime of the algorithm and how you can be sure there won't be any infinite loops.
 */
public class MutateArray {
    /**
     * Time: O(n)
     * Space: O(1)
     */
    public static void mutate(int[] A, int[] B) {
        int len = A.length;
        for (int i = 0; i < len; i++) {
            while (i != B[i]) {
                swap(A, i, B[i]);
                swap(B, i, B[i]);
            }
        }
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {22, 44, 11, 88, 33};
        int[] B = {1, 3, 0, 4, 2};
        mutate(A, B);
        for (int j = 0; j < A.length; j++) {
            System.out.print(A[j] + " ");
        }
    }
}
