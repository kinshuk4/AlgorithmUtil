package com.vaani.algo.compete.cc150.chapter9;

/**
 * A child is running up a staircase with n steps, and can hop either 1 step, 2
 * steps or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 */
// O(n) space, O(n) time
public class Question1 {
    public long numOfWays(int numOfStairs) {
        // write the implement here

        long[] stairs = new long[numOfStairs + 1];
        for (int i = 0; i <= numOfStairs; ++i) {
            if (i == 0) {
                stairs[i] = 0;
            } else if (i == 1) {
                stairs[i] = 1 + stairs[i - 1];
            } else if (i == 2) {
                stairs[i] = 1 + stairs[i - 2] + stairs[i - 1];
            } else if (i == 3) {
                stairs[i] = 1 + stairs[i - 3] + stairs[i - 2] + stairs[i - 1];
            } else {
                stairs[i] = stairs[i - 3] + stairs[i - 2] + stairs[i - 1];
            }
        }
        return stairs[numOfStairs];
    }
}

