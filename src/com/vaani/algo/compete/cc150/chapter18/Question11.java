package com.vaani.algo.compete.cc150.chapter18;

/**
 * Imagine you have a square matrix, where each cell (pixel) is either black or
 * white. Design an algorithm to find the maximum subsquare such that all four
 * borders are filled with black pixels.
 */
// O(1) space, O(n^4) time
public class Question11 {

    /**
     * @param matrix 'w' denotes white while 'b' denotes black
     * @return return the coordinates (x, y) of upper left point (first two
     * elements) and bottom right points
     */
    // O(n^4) time
    public int[] maximumSubSquare(String[] matrix) {
        // write implementation here
        int[] tmp = {-1, -1, -1, -1};
        if (matrix == null || matrix.length == 0 || matrix[0].length() == 0) {
            return tmp;
        }
        for (int len = matrix.length; len >= 0; --len) {
            if (checkSquareWithSize(matrix, len, tmp)) {
                return tmp;
            }
        }
        return tmp;
    }

    private boolean checkSquareWithSize(String[] matrix, int len, int[] res) {
        int end = matrix.length - len + 1;
        for (int row = 0; row < end; ++row) { // upper left
            for (int col = 0; col < end; ++col) {
                if (checkSquare(matrix, row, col, len, res)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSquare(String[] matrix, int row, int col, int len, int[] res) {
        for (int i = col; i < col + len; ++i) {  // check upper and lower border
            if (matrix[row].charAt(col) == 'w' || matrix[row + len - 1].charAt(col) == 'w') {
                return false;
            }
        }
        for (int i = row; i < row + len; ++i) {  // check left and right border
            if (matrix[i].charAt(col) == 'w' || matrix[i].charAt(col + len - 1) == 'w') {
                return false;
            }
        }
        res[0] = row;
        res[1] = col;
        res[2] = row + len - 1;
        res[3] = col + len - 1;
        return true;
    }

}

