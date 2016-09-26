package com.vaani.algo.compete.cc150.chapter18;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Describe an algorithm to find the smallest one million numbers in one billion
 * numbers. Assume that the computer memory CANNOT hold all one billion numbers.
 */
// O(n) space, O(lgn) time
public class Question6 {

    public int[] firstK(int arr[], int k) {
        int[] arrK = new int[k];
        // max heap
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {

            @Override
            public int compare(Integer arg0, Integer arg1) {
                return -arg0.compareTo(arg1);
            }

        });

        for (int i = 0; i < arr.length; ++i) {
            heap.add(arr[i]);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int idx = 0;
        while (!heap.isEmpty()) {
            arrK[idx++] = heap.poll();
        }
        return arrK;
    }

}
