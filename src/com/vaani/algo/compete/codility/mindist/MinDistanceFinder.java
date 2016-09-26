package com.vaani.algo.compete.codility.mindist;

import java.util.Arrays;

/**
 * @author Alexey
 *
 * Find the minimum distance between two distinct elements or int array
 */
public class MinDistanceFinder {
    private int minDist;
    public int countIterations = 0;

    /**
     * assumes time complexity O(N*log(N))
     * space complexity O(N)
     *
     * @param array
     * @return
     */
    public int quickSortAndFindMinDist(int[] array) {
        countIterations = 0;
        minDist = Math.abs(array[0] - array[1]);
        quickSortAndFindMinDist(array, 0, array.length - 1);
        System.out.println(minDist);
        return minDist;
    }

    void quickSortAndFindMinDist(int[] array, int start, int end) {
        System.out.println(Arrays.toString(array));
        countIterations++;

        if (start >= end) {
            return;
        }
        int pivot = start;
        for (int i = start; i <= end; i++) {

            if (i != end && Math.abs(array[i] - array[end]) < minDist) {
                System.out.println("|" + array[i] + " - " + array[end] + "| = " + Math.abs(array[i] - array[end]) + "; mindist=" + minDist);
                minDist = Math.abs(array[i] - array[end]);
            }

            if (array[i] <= array[end]) {
                int temp = array[pivot]; // swap
                array[pivot] = array[i];
                array[i] = temp;
                pivot += 1;
            }
        }
        --pivot;

        quickSortAndFindMinDist(array, start, pivot - 1);
        quickSortAndFindMinDist(array, pivot + 1, end);
    }

    /**
     * assumes time complexity O(N*N)
     *
     * @param arr
     * @return
     */
    public int minDistanceBubble(int[] arr) {
        countIterations = 0;
        System.out.println("-------------------");
        System.out.println("arr=" + Arrays.toString(arr));
        int min = Math.abs(arr[0] - arr[1]);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                final int dist = Math.abs(arr[i] - arr[j]);
                if (dist < min) {
                    min = dist;
                }
                System.out.println("arr[" + i + "] " + arr[i] + " - arr[" + j + "] " + arr[j] + " =" + dist + "; min = " + min);
                countIterations++;
            }
        }
        return min;
    }

}
