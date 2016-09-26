package com.vaani.algo.compete.cc150.chap8recursiondp;

/**
 * A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs.
 */
public class ClimbStairs2 {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        int first = 1, second = 2, third = 4;
        for (int i = 4; i <= n; i++) {
            int tmp = first + second + third;
            first = second;
            second = third;
            third = tmp;
        }
        return third;
    }
}
