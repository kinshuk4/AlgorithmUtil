package com.vaani.algo.array.sorted;

/**
 * Implement the following function, FindSortedArrayRotation, which takes as its input an array of unique integers that has been sorted in ascending order,
 * then rotated by an unknown amount X where 0 <= X <= (arrayLength – 1). An array rotation by amount X moves every element array[i] to array[(i + X) % arrayLength].
 * FindSortedArrayRotation discovers and returns X by examining the array.
 * <p>
 * http://how-to-crack-algorithms.blogspot.com/2011/09/searching-pivot-in-sorted-rotated-array.html
 */
public class FindRotationLength {
    public static int findPivotIndex(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (arr[start] > arr[end]) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 4, 5, 1};
        int[] arr2 = {4, 5, 6, 1, 2, 3};
        System.out.println(findPivotIndex(arr1));
        System.out.println(findPivotIndex(arr2));
    }
}
