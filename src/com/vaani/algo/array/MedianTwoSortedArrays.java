package com.vaani.algo.array;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Created by Xiaomeng on 9/22/2014.
 */
public class MedianTwoSortedArrays {
    public static void main(String[] args) {
        MedianTwoSortedArrays test = new MedianTwoSortedArrays();
        int[] A = {1, 3, 5};
        int[] B = {2, 4};
        System.out.println(test.findKthElement(A, B, 5, 0, 2));
    }

    /*
    * Binary Search: http://nriverwang.blogspot.com/2013/04/k-th-smallest-element-of-two-sorted.html
    * */
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = B.length;

        if ((m + n) % 2 == 1) {
            return findKthElement(A, B, (m + n) / 2 + 1, 0, m - 1);
        } else {
            return (findKthElement(A, B, (m + n) / 2, 0, m - 1)
                    + findKthElement(A, B, (m + n) / 2 + 1, 0, m - 1)) / 2.0;
        }
    }

    public int findKthElement(int[] A, int[] B, int k, int start, int end) {
        if (start > end) {
            return findKthElement(B, A, k, 0, B.length - 1);
        }

        int i = start + (end - start) / 2;
        int j = k - 1 - i - 1;

        if ((j < 0 || (j < B.length && A[i] >= B[j]))
                && (j + 1 >= B.length || (j + 1 >= 0 && A[i] <= B[j + 1]))) {
            return A[i];
        } else if (j < 0 || (j + 1 < B.length && A[i] > B[j + 1])) {
            return findKthElement(A, B, k, start, i - 1);
        } else {
            return findKthElement(A, B, k, i + 1, end);
        }
    }
}
