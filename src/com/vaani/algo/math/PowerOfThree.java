package com.vaani.algo.math;

/**
 * Check whether a number is power of 3.
 * Time complexity: O(log_9 N)
 */
public class PowerOfThree {

    public boolean isPowerOfThree(int num) {
        if (num == 0) {
            return true;
        }

        while (num % 9 == 0) {
            num /= 9;
        }

        return num == 1 || num == 3;
    }
}
