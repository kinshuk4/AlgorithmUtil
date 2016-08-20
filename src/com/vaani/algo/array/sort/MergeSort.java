package com.vaani.algo.array.sort;

public class MergeSort extends Sort {

    @Override
    public void sort(int[] A) {
        int[] copy = new int[A.length];
        sort(A, copy, 0, A.length - 1);
    }

    private void sort(int[] A, int[] copy, int first, int last) {
        if (first >= last) {
            return;
        }
        int mid = (first + last) / 2;
        sort(A, copy, first, mid);
        sort(A, copy, mid + 1, last);
        merge(A, copy, first, mid, last);
    }

    private void merge(int[] A, int[] copy, int first, int mid, int last) {
        // copy
        for (int i = first; i <= last; ++i) {
            copy[i] = A[i];
        }
        int i = first;
        int j = mid + 1;

        for (int k = first; k <= last; ++k) {
            if (i > mid) { // no element in first half arr
                A[k] = copy[j++];
            } else if (j > last) {
                A[k] = copy[i++];
            } else if (copy[i] <= copy[j]) {
                A[k] = copy[i++];
            } else {
                A[k] = copy[j++];
            }
        }
    }

}
