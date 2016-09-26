package com.vaani.algo.compete.cc150.chapter18;

import java.util.Random;

/**
 * Write a method to randomly generate a set of m integers from an array of size
 * n. Each element must have equal probability of being chosen.
 */
// O(m) space, O(1) time
public class Question3 {

    public void randomGenerate(int[] arr, int m) {
        // write implementation here
        int[] gen = new int[m];
        Random rnd = new Random();

        for (int i = 0; i < arr.length; ++i) {
            if (i < m) {
                gen[i] = arr[i];
            } else {
                // new elements are kept with probability m/i
                int idx = rnd.nextInt(i);
                if (idx < m) {
                    gen[idx] = arr[i];
                }
            }
        }
    }

}
