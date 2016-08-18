package com.vaani.algo.array.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix, obtain all the diagonals.
 * <p>
 * Time complexity: O(MN), space complexity: O(MN).
 */
public class DiagonalPrinting {

    public List<List<Character>> diagonalPrinting(char[][] matrix) {
        List<List<Character>> res = new ArrayList<List<Character>>();
        int row = matrix.length;
        if (matrix.length == 0) {
            return res;
        }
        int col = matrix[0].length;

        for (int c = 0; c < col; ++c) {
            List<Character> list = new ArrayList<Character>();
            int tr = 0, tc = c;
            while (tr < row && tc < col) {
                list.add(matrix[tr][tc]);
                ++tr;
                ++tc;
            }
            res.add(list);
        }

        for (int r = 1; r < row; ++r) {
            List<Character> list = new ArrayList<Character>();
            int tr = r, tc = 0;
            while (tr < row && tc < col) {
                list.add(matrix[tr][tc]);
                ++tr;
                ++tc;
            }
            res.add(list);
        }
        return res;
    }
}
