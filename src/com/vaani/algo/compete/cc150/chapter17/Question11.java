package com.vaani.algo.compete.cc150.chapter17;

import java.util.Random;

/**
 * Implement a method rand7() given rand5(). That is, given a method that
 * generates a random number between 0 and 4 (inclusive), write a method that
 * generates a random number between 0 and 6 (inclusive).
 */
public class Question11 {

    private Random rnd = new Random();

    public int rand5() {
        return rnd.nextInt(5);
    }

    public int rand7() {
        //  write implementation here
        int n = 0;
        do {
            // each number appear with probability 4%
            n = 5 * rand5() + rand5();
        } while (n > 20);
        // data in range [1, 21]
        return n % 7;
    }
}
