package com.vaani.algo.array.matrix;

/**
 * Find the largest all 1 square matrix in side the given matrix.
 * O(mn) time, O(mn) space.
 */
public class LargestSquareInMatrix {

    public int largestSquareInMatrix(int[][] matrix) {
        int maxSquare = 0;
        int[][] assocMatrix = new int[matrix.length][matrix[0].length];
        for (int r = matrix.length - 1; r >= 0; --r) {
            for (int c = matrix[r].length - 1; c >= 0; --c) {
                if (matrix[r][c] == 1) {
                    int min = Integer.MAX_VALUE;
                    if (r + 1 >= matrix.length || c + 1 >= matrix[r].length) {
                        min = 0;
                    } else {
                        min = Math.min(min, assocMatrix[r + 1][c]);
                        min = Math.min(min, assocMatrix[r][c + 1]);
                        min = Math.min(min, assocMatrix[r + 1][c + 1]);
                    }
                    assocMatrix[r][c] = 1 + min;
                    maxSquare = Math.max(maxSquare, assocMatrix[r][c]);
                }
            }
        }
        return maxSquare;
    }

}
