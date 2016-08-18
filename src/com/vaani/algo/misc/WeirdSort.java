package com.vaani.algo.misc;

/**
 * Given an arbitrary array, sort the array into the form where
 * {a_0 <= a_1 >= a_2 <= a_3 ...}.
 * <p>
 * Time complexity O(n), space complexity O(1).
 */
public class WeirdSort {

    public void sort(int[] arr) {
        int first = 0;
        int second = 1;
        boolean firstInOdd = true;

        while (second < arr.length) {
            if (firstInOdd) {
                if (arr[first] > arr[second]) {  // property violated
                    // swap
                    int tmp = arr[first];
                    arr[first] = arr[second];
                    arr[second] = tmp;
                }
            } else {  // first pointer in even position
                if (arr[first] <= arr[second]) {
                    // swap
                    int tmp = arr[first];
                    arr[first] = arr[second];
                    arr[second] = tmp;
                }
            }
            // move forward
            ++first;
            ++second;
            firstInOdd = !firstInOdd;  // swap the marker
        }
    }

}
