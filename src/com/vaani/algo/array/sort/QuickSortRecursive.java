package com.vaani.algo.array.sort;

public class QuickSortRecursive extends Sort {

    @Override
    public void sort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int start, int end) {
        if (start < end) {
            int pivot = partition(A, start, end);
            quickSort(A, start, pivot - 1);
            quickSort(A, pivot + 1, end);
        }
    }

    public int partition(int[] A, int start, int end) {
        int i = start, j = end + 1;
        int val = A[i];
        while (true) {
            while (A[++i] < val) {  // move forward until >= val
                if (i == end) {
                    break;
                }
            }
            while (val < A[--j]) {  // move back until <= val
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
        int tmp = A[start];  // exchange pivot to proper location
        A[start] = A[j];
        A[j] = tmp;
        return j;
    }

    private int paritionSingle(int[] A, int start, int end) {
        int pivot = A[end];
        int bar = start - 1;
        for (int i = start; i < end; ++i) {
            if (A[i] <= pivot) {
                ++bar;
                int tmp = A[i];
                A[i] = A[bar];
                A[bar] = tmp;
            }
        }
        int tmp = A[bar + 1];
        A[bar + 1] = A[end];
        A[end] = tmp;
        return bar + 1;
    }


}
