package com.vaani.algo.paradigm.dp;

/**
 * Created by Xiaomeng on 11/25/2014.
 */
public class MinCostPath {
    public static int getMinCost(int[][] cost) {
        int m = cost.length;
        int n = cost[0].length;

        for (int i = 1; i < n; i++) {
            cost[0][i] += cost[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            cost[i][0] += cost[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int left = cost[i][j - 1];
                int top = cost[i - 1][j];
                int corner = cost[i - 1][j - 1];
                cost[i][j] += Math.min(Math.min(left, top), corner);
            }
        }
        return cost[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] cost = {
                {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}
        };
        System.out.println(getMinCost(cost));
    }
}
