package com.vaani.algo.misc;

/**
 * Reverse a string.
 */
public class ReverseString {

    /**
     * As string is immutable in java, we use char[] instead.
     * O(1) space, O(n) time.
     *
     * @return
     */
    public void reverse(char[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            ++start;
            --end;
        }
    }

}
