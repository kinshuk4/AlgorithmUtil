package com.vaani.algo.compete.cc150.chapter9;

import java.util.ArrayList;

/**
 * Write an algorithm to print all ways of arranging eight queens on an 8x8
 * chess board so that none of them share the same row, column or diagonal. In
 * this cases, "diagonal" means all diagonals, not just the two that bisect the
 * board.
 */
public class Question9 {

    // available at leetcode.com online judge, question N-Queens
    //  [
    //   [".Q..",  // Solution 1
    //    "...Q",
    //    "Q...",
    //    "..Q."],
    //
    //   ["..Q.",  // Solution 2
    //    "Q...",
    //    "...Q",
    //    ".Q.."]
    //  ]
    public static class Solution {

        public ArrayList<String[]> solveNQueens(int n) {
            // Start typing your Java solution below
            // DO NOT write main() function
            ArrayList<String[]> solutions = new ArrayList<String[]>();
            solve(0, new int[n], solutions, n);
            return solutions;
        }

        private void solve(int curIdx, int[] history, ArrayList<String[]> solutions, int n) {
            if (curIdx == n) {
                solutions.add(addSolution(history));
            } else {
                for (int i = 0; i < n; ++i) {
                    history[curIdx] = i;
                    boolean valid = true;
                    for (int j = 0; j < curIdx; ++j) {
                        if (history[j] == history[curIdx] // column collision
                                || history[curIdx] - curIdx == history[j] - j    // diagonal collision
                                || j + history[j] == curIdx + history[curIdx]) { // neg-diagonal collision
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        solve(curIdx + 1, history, solutions, n);
                    }
                }
            }
        }

        private String[] addSolution(int[] rows) {
            String[] res = new String[rows.length];
            for (int i = 0; i < rows.length; ++i) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < rows.length; ++j) {
                    sb.append('.');
                }
                sb.setCharAt(rows[i], 'Q');
                res[i] = sb.toString();
            }
            return res;
        }
    }

}
