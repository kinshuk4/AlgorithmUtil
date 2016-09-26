package com.vaani.algo.compete.cc150.chap7;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Design an algorithm to find the kth number such that the only prime factors
 * are 3, 5, and 7.
 */
// O(n) space, O(n) time
public class Question7 {

    public long kth(int k) {
        // write implementation here
        Queue<Long> three = new LinkedList<Long>();
        Queue<Long> five = new LinkedList<Long>();
        Queue<Long> seven = new LinkedList<Long>();
        three.add(3l);
        five.add(5l);
        seven.add(7l);

        int c = 0;
        long kth = 0;
        while (c++ < k) {
            kth = Math.min(three.peek(), Math.min(five.peek(), seven.peek()));
            if (kth == three.peek()) {
                three.poll();
                three.add(3 * kth);
                five.add(5 * kth);
            } else if (kth == five.peek()) {
                five.poll();
                five.add(5 * kth);
            } else if (kth == seven.peek()) {
                seven.poll();
            }
            seven.add(7 * kth);
        }
        return kth;
    }

}

