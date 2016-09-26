package com.vaani.algo.compete.cc150.chapter18;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Numbers are randomly generated and passed to a method. Write a program to
 * find and maintain the median value as new values are generated.
 */
// O(n) space, O(logn) time
public class Question9 {

    // max heap
    private PriorityQueue<Integer> smallerHalf = new PriorityQueue<Integer>(100, new MaxHeapComparator());
    // min heap
    private PriorityQueue<Integer> largerHalf = new PriorityQueue<Integer>();

    public void add(int newData) {
        //  write implementation here
        largerHalf.add(newData);
        if (smallerHalf.size() < largerHalf.size()) {
            int elem = largerHalf.poll();
            smallerHalf.add(elem);
        }
    }

    public int median() {
        // write implementation here
        if (smallerHalf.size() + largerHalf.size() == 0) {
            return 0;
        }
        if (smallerHalf.size() == largerHalf.size()) { // even number
            return (smallerHalf.peek() + largerHalf.peek()) / 2;
        }
        return smallerHalf.peek(); // odd number
    }

    public static class MaxHeapComparator implements Comparator<Integer> {
        public int compare(Integer first, Integer second) {
            return -first.compareTo(second);
        }
    }

}

