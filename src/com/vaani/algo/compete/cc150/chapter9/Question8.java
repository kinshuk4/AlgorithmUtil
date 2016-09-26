package com.vaani.algo.compete.cc150.chapter9;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5
 * cents) and pennies (1 cent), write code to calculate the number of ways of
 * representing n cents.
 */
public class Question8 {

    public long numberOfWays(int cents) {
        // write the implementation here
        return numberOfWays(cents, 100);
    }

    private long numberOfWays(int cents, int prevDeduct) {
        int sum = 0;
        if (cents == 0) {
            return 1;
        }
        if (cents - 25 >= 0 && prevDeduct >= 25) {
            sum += numberOfWays(cents - 25, 25);
        }
        if (cents - 10 >= 0 && prevDeduct >= 10) {
            sum += numberOfWays(cents - 10, 10);
        }
        if (cents - 5 >= 0 && prevDeduct >= 5) {
            sum += numberOfWays(cents - 5, 5);
        }
        if (cents - 1 >= 0 && prevDeduct >= 1) {
            sum += numberOfWays(cents - 1, 1);
        }
        return sum;
    }

}

