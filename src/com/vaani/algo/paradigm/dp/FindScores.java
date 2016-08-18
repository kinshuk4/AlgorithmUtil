package com.vaani.algo.paradigm.dp;

/**
 * finding the number of ways a given score could be reached for a game with 3
 * different ways of scoring (e.g. 3, 5 and 10 points).
 */
public class FindScores {

    // O(n) space, O(n) time, where n is the score.
    public int findScores(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 1;
        if (n > 0) {
            ways[1] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            ways[i] += ways[i - 1] + ways[i - 2];
            if (i >= 5) {
                ways[i] += ways[i - 5];
            }
        }

        return ways[n];
    }

}
