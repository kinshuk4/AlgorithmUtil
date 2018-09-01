package com.vaani.algo.array.matrix;

import java.util.Stack;

import static com.vaani.algo.array.container.LargestRectangleHistogram.largestRectangleArea;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 * <p>
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows == 0 ? 0 : matrix[0].length;
        int[][] height = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '0') {
                    height[i][j] = 0;
                } else if (matrix[i][j] == '1') {
                    if (i == 0)
                        height[i][j] = 1;
                    else
                        height[i][j] = height[i - 1][j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < rows; i++) {
            max = Math.max(max, largestRectangleArea(height[i]));
        }
        return max;
    }
}
