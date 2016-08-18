package com.vaani.algo.array;

//https://github.com/xiaoningning/LeetCode-1/blob/master/Java/src/net/kenyang/algorithm/BestTimeToBuyAndSellStock.java

/**
 * Say you have an array for which the i element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int size = prices.length;

        if (size <= 1) {
            return 0;
        }

        int min = prices[0];
        int max = prices[1] - min;
        for (int i = 2; i < size; i++) {

            if (min > prices[i - 1]) {
                min = prices[i - 1];
            }

            if (max < (prices[i] - min)) {
                max = prices[i] - min;
            }

        }


        return (max < 0) ? 0 : max;
    }
}
