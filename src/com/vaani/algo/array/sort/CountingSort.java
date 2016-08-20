package com.vaani.algo.array.sort;


public class CountingSort extends Sort {

    @Override
    public void sort(int[] A) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int val : A) {
            min = val < min ? val : min;
            max = val > max ? val : max;
        }

        int[] counting = new int[max - min + 1];
        for (int val : A) {
            ++counting[val - min];
        }
        for (int i = 1; i < counting.length; ++i) {
            counting[i] += counting[i - 1];   // calculate the accumulated stats
        }

        int[] B = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
            int index = counting[A[i] - min] - 1; // calculate the index to insert
            B[index] = A[i];
            --counting[A[i] - min];
        }
    }

}
