package com.vaani.algo.array;

/**
 * Given two sorted integer arrays A and B, mergeIterative B into A as one sorted array.
 * <p>
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
 * <p>
 * Created by Xiaomeng on 7/22/2014.
 */
public class MergeSortedArray {
    public static void merge(int A[], int m, int B[], int n) {
        int index = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (A[m] < B[n]) {
                A[index] = B[n];
                n--;
            } else {
                A[index] = A[m];
                m--;
            }
            index--;
        }
        while (n >= 0) {
            A[index] = B[n];
            n--;
            index--;
        }
    }

    public static void main(String[] args) {
        int[] A = new int[1];
        int[] B = {1};
        merge(A, 0, B, 1);
        for (int k = 0; k < 1; k++) {
            System.out.print(A[k] + " ");
        }
    }
}
