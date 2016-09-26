package com.vaani.algo.compete.cc150.chapter11;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer
 * at the end to hold B. Write a method to mergeIterative B into A in sorted order.
 */
public class Question1 {

    // this question is available at leetcode, question Merge Sorted Array
    // O(n) space, O(n) time
    public class Solution {
        public void merge(int A[], int m, int B[], int n) {
            // Start typing your Java solution below
            // DO NOT write main() function
            int i = m - 1;
            int j = n - 1;
            int k = A.length - 1;
            while (k >= 0) {
                if (i >= 0 && j >= 0) {
                    if (A[i] > B[j]) {
                        A[k--] = A[i--];
                    } else {
                        A[k--] = B[j--];
                    }
                } else if (i >= 0) {
                    A[k--] = A[i--];
                } else if (j >= 0) {
                    A[k--] = B[j--];
                }
            }
        }
    }


}
