package com.vaani.algo.compete.cc150.chapter17;

/**
 * Given an array of integers, write a method to find indices m and n such that
 * if you sorted elements m through n, the entire array would be sorted.
 * Minimize n - m (that is, find the smallest such sequence).
 * <p>
 * EXAMPLE
 * Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
 * Result: 3, 9
 */
// O(1) space, O(n) time
public class Question6 {

    public int[] findIndices(int[] arr) {
        //  write implementation here
        int leftEnd = leftSequenceEnd(arr);
        int rightStart = rightSequenceStart(arr);
        if (leftEnd + 1 == arr.length) { // already sorted
            return new int[]{-1, -1};
        }

        int maxMid = Integer.MIN_VALUE;
        int minMid = Integer.MAX_VALUE;
        for (int i = leftEnd; i <= rightStart; ++i) {
            if (arr[i] < minMid) {
                minMid = arr[i];
            }
            if (arr[i] > maxMid) {
                maxMid = arr[i];
            }
        }

        int leftIndex = shrinkLeft(arr, leftEnd, minMid);
        int rightIndex = shrinkRight(arr, rightStart, maxMid);

        return new int[]{leftIndex, rightIndex};
    }

    private int leftSequenceEnd(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i] <= arr[i + 1]) {
                ++index;
            } else {
                break;
            }
        }
        return index;
    }

    private int rightSequenceStart(int[] arr) {
        int index = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; --i) {
            if (arr[i] <= arr[i + 1]) {
                --index;
            } else {
                break;
            }
        }
        return index;
    }

    private int shrinkLeft(int[] arr, int end, int min) {
        while (end >= 0 && arr[end] > min) {
            --end;
        }
        return end + 1;
    }

    private int shrinkRight(int[] arr, int start, int max) {
        while (start < arr.length && arr[start] < max) {
            ++start;
        }
        return start - 1;
    }

}

