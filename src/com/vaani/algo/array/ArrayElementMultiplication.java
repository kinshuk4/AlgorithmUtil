package com.vaani.algo.array;

/**
 * Given an array A[0, n - 1], generate another array B such that
 * B[i] = A[0] * ... * A[i - 1] *  A[i + 1] * ... * A[n - 1].
 */
public class ArrayElementMultiplication {

    // O(n) space, O(n) time
    public int[] arrayCalculation(int[] A) {
        int[] auc = new int[A.length];
        auc[0] = 1;
        for (int i = 1; i < A.length; ++i) {
            auc[i] = auc[i - 1] * A[i - 1];
        }

        int[] B = new int[A.length];
        B[A.length - 1] = 1;
        for (int i = A.length - 2; i >= 0; --i) {
            B[i] = B[i + 1] * A[i + 1];
            B[i + 1] *= auc[i + 1];
        }

        return B;
    }

}
