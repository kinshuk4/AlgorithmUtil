package com.vaani.algo.array;

/**
 * Given an array of n elements which contains elements from 0 to n-1, with any of these numbers appearing any number of times.
 * Find these repeating numbers in O(n) and using only constant memory space.
 * <p>
 * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer should be 1, 3 and 6.
 */
public class FindDuplicates {
    /**
     * Reference: http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
     */
    public static void printDuplicats(int[] arr) {
        boolean zeroPresent = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[Math.abs(arr[i])] > 0) {
                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
            } else if (arr[Math.abs(arr[i])] < 0) {
                System.out.print(Math.abs(arr[i]) + " ");
            } else {
                if (zeroPresent == false)
                    zeroPresent = true;
                else
                    System.out.print(Math.abs(arr[i]) + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 3, 6, 6};
        printDuplicats(arr);
    }
}
