package com.vaani.algo.misc;
import static com.vaani.algo.ds.utils.ArrayUtils.swap;

/**
 * Given an array of random numbers, Push all the zero’s of a given array to the end of the array.
 * For example, if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}, it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}.
 * The order of all other elements should be same. Expected time complexity is O(n) and extra space is O(1).
 */
public class PlaceZero {
    /**
     * Reference: http://www.geeksforgeeks.org/move-zeroes-end-array/
     */
    public static void placeZero2(int[] arr) {
        if (arr.length == 0) return;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while (count < arr.length) arr[count++] = 0;
    }

    public static void placeZeroToBegining(int[] arr) {
        int count = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[count--] = arr[i];
            }
        }
        while (count >= 0) arr[count--] = 0;
    }

    /**
     * My solution: doesn't keep the order
     */
    public static void placeZero1(int[] arr) {
        if (arr.length == 0) return;

        int start = 0, end = arr.length - 1;
        while (end >= start && arr[end] == 0) end--;
        while (start < end) {
            if (arr[end] == 0) {
                end--;
            }
            if (arr[start] != 0) {
                start++;
            } else if (arr[start] == 0) {
                swap(arr, start++, end--);
            }
        }
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
        placeZero2(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        int[] arr2 = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
        placeZeroToBegining(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }
}
