package com.vaani.algo.misc;

public class TwoDTrappingRainWater {

    public int twoDTrappingRainWater(int[][] matrix) {
        int total = 0;

        int[][] left = new int[matrix.length][matrix[0].length];
        int[][] right = new int[matrix.length][matrix[0].length];
        int[][] top = new int[matrix.length][matrix[0].length];
        int[][] bottom = new int[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length; ++r) {
            int leftMax = 0;
            for (int c = 0; c < matrix[r].length; ++c) {
                leftMax = Math.max(leftMax, matrix[r][c]);
                left[r][c] = leftMax;
            }
            int rightMax = 0;
            for (int c = matrix[r].length - 1; c >= 0; --c) {
                rightMax = Math.max(rightMax, matrix[r][c]);
                right[r][c] = rightMax;
            }
        }

        for (int c = 0; c < matrix[0].length; ++c) {
            int topMax = 0;
            for (int r = 0; r < matrix.length; ++r) {
                topMax = Math.max(topMax, matrix[r][c]);
                top[r][c] = topMax;
            }
            int bottomMax = 0;
            for (int r = matrix.length - 1; r >= 0; --r) {
                bottomMax = Math.max(bottomMax, matrix[r][c]);
                bottom[r][c] = bottomMax;
            }
        }

        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[r].length; ++c) {
                int rowHeight = Math.min(left[r][c], right[r][c]);
                int colHeight = Math.min(top[r][c], bottom[r][c]);
                int height = Math.min(rowHeight, colHeight);
                total += height - matrix[r][c];
            }
        }

        return total;
    }
}
