package com.vaani.algo.array;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 * <p>
 * Created by Xiaomeng on 7/24/2014.
 */
public class FirstMissingPositive {
    public static int firstMissingPositive(int[] A) {
        int i = 0;
        while (i < A.length) {
            if (A[i] >= 1 && A[i] != i + 1 && A[i] < A.length && A[i] != A[A[i] - 1])
                swap(A, i, A[i] - 1);
            else
                i++;
        }
        for (i = 0; i < A.length; i++) {
            if (A[i] != i + 1) return i + 1;
        }
        return A.length + 1;
    }

    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {0};
        System.out.println(firstMissingPositive(A));
    }
}
