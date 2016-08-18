package com.vaani.algo.misc;

import java.util.Random;

public class RandomShuffle {

    public void shuffle(int[] A) {
        Random rnd = new Random();
        for (int i = 1; i < A.length; ++i) {
            int idx = rnd.nextInt(i); // get random number in range [0, i - 1]
            int tmp = A[i];
            A[i] = A[idx];
            A[idx] = tmp;
        }
    }

}
