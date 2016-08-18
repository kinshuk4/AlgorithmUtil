package com.vaani.algo.misc;

/**
 * Reverse the order of words in a string.
 * <p>
 * e.g. "How are you?" -> "you? are How"
 */
public class ReverseWordsInString {

    /**
     * O(1) space, O(n) time.
     *
     * @param arr
     */
    public void reverseWordsInString(char[] arr) {

        // reverse the whole string
        int start = 0, end = arr.length - 1;

        while (start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            ++start;
            --end;
        }

        // reverse in each word
        start = 0;
        end = 0;
        while (true) {
            while (end < arr.length && arr[end] != ' ') {
                ++end;
            }
            int nextStart = end + 1;
            // reverse the chars in a word
            --end;
            while (start < end) {
                char tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
                ++start;
                --end;
            }
            if (nextStart >= arr.length) {
                return;
            }
            start = nextStart;
            end = nextStart;
        }

    }

}
