package com.vaani.algo.compete.codesignal.interviewpractice.hashtable;

import java.util.*;

/**
 * You have a collection of coins, and you know the values of the coins and the quantity of each type of coin in it. You want to know how many distinct sums you can make from non-empty groupings of these coins.
 * Example
 * For coins = [10, 50, 100] and quantity = [1, 2, 1], the output should be
 * possibleSumsRecursiveHelper(coins, quantity) = 9.
 * Here are all the possible sums:
 * 50 = 50;
 * 10 + 50 = 60;
 * 50 + 100 = 150;
 * 10 + 50 + 100 = 160;
 * 50 + 50 = 100;
 * 10 + 50 + 50 = 110;
 * 50 + 50 + 100 = 200;
 * 10 + 50 + 50 + 100 = 210;
 * 10 = 10;
 * 100 = 100;
 * 10 + 100 = 110.
 * As you can see, there are 9 distinct sums that can be created from non-empty groupings of your coins.
 */
public class PossibleSums {
    public static int possibleSumsRecursive(final int[] coins, final int[] quantities) {
        return possibleSumsRecursiveHelper(new HashSet<>(), coins, quantities, 0, 0) - 1; // -1 because 0 is also in the result
    }

    private static int possibleSumsRecursiveHelper(final Set<Integer> set, final int[] coins,
                                                   final int[] quantities,
                                                   final int coinIterator, final int sum) {
        int result = 0;
        if (coinIterator == coins.length) {
            result = set.contains(sum) ? 0 : 1;
            set.add(sum);
        } else {
            int currentQuantity = quantities[coinIterator];
            for (int j = 0; j <= currentQuantity; ++j) {
                int newSum = sum + (j * coins[coinIterator]);
                result += possibleSumsRecursiveHelper(set, coins, quantities, coinIterator + 1, newSum);
            }
        }
        return result;
    }
}
