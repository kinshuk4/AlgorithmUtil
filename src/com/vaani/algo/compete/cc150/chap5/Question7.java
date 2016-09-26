package com.vaani.algo.compete.cc150.chap5;

/**
 * An array A contains all the integers from 0 to n, except for one number which
 * is missing. In this problem, we cannot access an entire integer in A with a
 * single operation. The elements of A are represented in binary, and the only
 * operation we can use to access them is "fetch the jth bit of A[i]", which
 * takes constant time. Write code to find the missing integer. Can you do it in
 * O(n) time?
 */
public class Question7 {

    /**
     * Fetch the jth bit of ith integer from arr.
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    private int fetch(int[] arr, int i, int j) {
        return (arr[i] >> j) & 1;
    }

    public int findMissing(int[] arr, int n) {
        int sum = n * (n + 1) / 2;
        for (int i = 0; i < arr.length; ++i) {
            int factor = 1;
            for (int j = 0; j < 32; ++j) {
                sum -= fetch(arr, i, j) * factor;
                factor <<= 1;
            }
        }
        return sum;
    }

}

