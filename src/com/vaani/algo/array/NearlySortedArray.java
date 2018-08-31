package com.vaani.algo.array;

import java.util.Comparator;
import java.util.PriorityQueue;
import static com.vaani.algo.ds.utils.ArrayUtils.swap;

/**
 * Given an array of n elements, where each element is at most k away from its target position, devise an algorithm that sorts in O(n log k) time.
 * For example, let us consider k is 2, an element at index 7 in the sorted array, can be at indexes 5, 6, 7, 8, 9 in the given array.
 * <p>
 * Reference: http://www.geeksforgeeks.org/nearly-sorted-algorithm/
 */
public class NearlySortedArray {
    /**
     * Time: O(nlogk)
     * Space: O(k)
     */
    public static void sort(int[] arr, int k) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
                return n1 - n2;
            }
        };

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k + 1, comparator);
        for (int i = 0; i < k + 1; i++) {
            queue.offer(arr[i]);
        }

        int index = 0;
        int i = k + 1;
        while (i < arr.length) {
            arr[index] = queue.poll();
            queue.offer(arr[i]);
            index++;
            i++;
        }

        while (index < arr.length) {
            arr[index] = queue.poll();
            index++;
        }
    }

    /**
     * Sort a list of numbers in which each number is at a distance k from its actual position
     * <p>
     * Time: O(n)
     * Space: O(1)
     * <p>
     * Reference: http://www.glassdoor.com/Interview/Sort-a-list-of-numbers-in-which-each-number-is-at-a-distance-k-from-its-actual-position-QTN_122494.htm
     */
    public static void sort2(int[] arr, int k) {
        int len = arr.length;
        int count = 0;
        while (len > 0) {
            for (int i = 0; i < k; i++) {
                swap(arr, 2 * k * count + i, 2 * k * count + k + i);
            }
            len -= 2 * k;
            count++;
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 2, 8, 9, 6, 7};
        sort(arr, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] num = {3, 4, 1, 2, 8, 9, 6, 7};
        sort2(num, 2);
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
    }
}
