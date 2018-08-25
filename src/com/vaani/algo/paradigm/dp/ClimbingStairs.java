package com.vaani.algo.paradigm.dp;

/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class ClimbingStairs {
    public static int climbStairsHelper(int n, int[] count) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (count[n] != 0) {
            return count[n];
        } else {
            count[n] = climbStairsHelper(n - 1, count) + climbStairsHelper(n - 2, count);
            return count[n];
        }
    }

    public int climbStairsRecursive(int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        // if (n < 0) {
        //     return 0;
        // }
        // else if (n == 0) {
        //     return 1;
        // }
        // else {
        //     return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
        // }
        int[] c = new int[n + 1];
        return climbStairsHelper(n, c);
    }


    public int climbStairsDP(int n) {
        if (n == 1)
            return 1;
        else if (n == 2)
            return 2;

        int[] table = new int[n + 1];

        table[0] = 1;
        table[1] = 1;
        table[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        return table[n];
    }
}
