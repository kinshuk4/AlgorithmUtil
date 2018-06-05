package com.vaani.algo.array.matrix;

import java.util.*;


/**
 * Given a matrix (i.e. an array of arrays), find its submatrix obtained by deleting the specified rows and columns.
 * <p>
 * Example
 * <p>
 * For
 * <p>
 * matrix = [[1, 0, 0, 2],
 * [0, 5, 0, 1],
 * [0, 0, 3, 5]]
 * rowsToDelete = [1], and columnsToDelete = [0, 2], the output should be
 * <p>
 * constructSubmatrix(matrix, rowsToDelete, columnsToDelete) = [[0, 2],
 * [0, 5]]
 */
public class ConstructSubmatrix {
    static int[][] constructSubmatrix(int[][] matrix, int[] rowsToDelete, int[] columnsToDelete) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] result = new int[m - rowsToDelete.length][n - columnsToDelete.length];

        Set<Integer> rowSetToDelete = new HashSet<>();
        Set<Integer> colSetToDelete = new HashSet<>();

        for (int a : rowsToDelete) {
            rowSetToDelete.add(a);
        }

        for (int a : columnsToDelete) {
            colSetToDelete.add(a);
        }

        int k = 0;
        int l = 0;
        for (int i = 0; i < m; i++) {
            if (rowSetToDelete.contains(i)) {
                continue;
            }
            l = 0;
            for (int j = 0; j < n; j++) {
                if (colSetToDelete.contains(j)) {
                    continue;
                }

                result[k][l++] = matrix[i][j];
            }
            k++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 0, 2},
                {0, 5, 0, 1},
                {0, 0, 3, 5}
        };
        int[] rowsToDelete = {1};
        int[] colsToDelete = {0, 2};
        System.out.println(Arrays.toString(constructSubmatrix(matrix, rowsToDelete, colsToDelete)));
    }
}
