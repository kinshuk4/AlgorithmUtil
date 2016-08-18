package com.vaani.algo.paradigm.dp;

import java.util.Arrays;

/**
 * Return the coins combination with the minimum number of coins.
 * Time complexity O(MN), where M is the target value and N is the number of distinct coins.
 * Space complexity O(M).
 */
public class CoinChange {

    public int[] minCoins(int value, int[] coins) {
        Arrays.sort(coins);
        int[] cache = new int[value + 1]; // stores the min number of coins
        Arrays.fill(cache, Integer.MAX_VALUE);
        int[] prev = new int[value + 1]; // stores the previous location
        cache[0] = 0;
        prev[0] = 0;

        for (int i = 0; i <= value; ++i) {
            for (int j = coins.length - 1; j >= 0; --j) {
                if (coins[j] <= i) {
                    if (1 + cache[i - coins[j]] < cache[i]) { // find better combination
                        cache[i] = 1 + cache[i - coins[j]];
                        prev[i] = i - coins[j];
                    }
                }
            }
        }

        int[] res = new int[cache[value]];
        int idx = 0;
        int remain = value;
        while (remain != 0) {
            int coin = remain - prev[remain];
            remain = prev[remain];
            res[idx] = coin;
            ++idx;
        }

        return res;
    }

}
