package com.vaani.algo.misc;

import java.util.*;

/**
 * Given two arrays A1[] and A2[], sort A1 in such a way that the relative order among the elements will be same as those are in A2. For the elements not present in A2, append them at last in sorted order.
 * <p>
 * Input: A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}
 * A2[] = {2, 1, 8, 3}
 * Output: A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}
 */
public class SortArrayBasedOnAnotherArray {
    /**
     * HashMap
     * Time: O(m + n + plogp) -> remaining elements in A1
     * Space: O(m)
     * <p>
     * http://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/
     */
    public static void sortAccording(int[] A1, int[] A2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : A1) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        int index = 0;
        for (int n : A2) {
            if (map.containsKey(n)) {
                int count = map.get(n);
                for (int i = 0; i < count; i++) {
                    A1[index++] = n;
                }
                map.remove(n);
            }
        }

        List<Integer> keys = new ArrayList<Integer>(map.keySet());
        Collections.sort(keys);
        for (int key : keys) {
            int count = map.get(key);
            for (int i = 0; i < count; i++) A1[index++] = key;
        }
    }

    public static void main(String[] args) {
        int A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 7, 8, 8};
        int A2[] = {2, 1, 8, 3};
        //Expected: 2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9
        sortAccording(A1, A2);
        for (int i = 0; i < A1.length; i++) {
            System.out.print(A1[i] + " ");
        }
    }
}
