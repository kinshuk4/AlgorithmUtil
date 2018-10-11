package com.vaani.algo.paradigm.backtracking;

import java.util.Arrays;

public class NQueensIsSolvable_O_N {
    static int[] result; // this array will store the result

    // result[i]=j; means queen at i-th row is placed at j-th column.
    // Queens placed at (x1, y1) and (x2,y2)
    // x1==x2 means same rows, y1==y2 means same columns, |x2-x1|==|y2-y1| means
    // they are placed in diagonals.
    public boolean canPlace(int x2, int y2) {
        // This function will check if queen can be placed (x2,y2), or we can
        // say that Can queen at x2 row is placed at y2 column.
        // for finding the column for x2 row, we will check all the columns for
        // all the rows till x2-1.
        for (int i = 0; i < x2; i++) {
            //result[i] == y2 => columns are same
            //|i - x2| == |result[i] - y2| => in diagonals.
            if ((result[i] == y2)
                    || (Math.abs(i - x2) == Math.abs(result[i] - y2))) {
                return false;
            }
        }
        return true;
    }

    public void placeQueens(int x, int size) {
        for (int i = 0; i < size; i++) {
            //check if queen at xth row can be placed at i-th column.
            if (canPlace(x, i)) {
                result[x] = i; // place the queen at this position.
                if (x == size - 1) {
                    System.out.println("Order of " + size + " queens"
                            + Arrays.toString(result));
                }
                placeQueens(x + 1, size);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        result = new int[n];
        NQueensIsSolvable_O_N i = new NQueensIsSolvable_O_N();
        i.placeQueens(0, n);
    }
}
