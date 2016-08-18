package com.vaani.algo.paradigm.recursion;

/**
 * Given a matrix with 0s and 1s, find the number of groups all with 0s.
 */
public class NumberOfZeroGroups {

    // O(n^2) space, O(n^2) time
    public int numberOfZeroGroups(int[][] mat) {
        boolean[][] visit = new boolean[mat.length][mat[0].length];

        int count = 0;
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[i].length; ++j) {
                if (!visit[i][j] && mat[i][j] == 0) {
                    bfs(mat, i, j, visit, count);
                    ++count;
                }
            }
        }

        return count;
    }

    private void bfs(int[][] mat, int i, int j, boolean[][] visit, int count) {
        visit[i][j] = true;
        mat[i][j] = count + 2;
        if (i > 0 && !visit[i - 1][j] && mat[i - 1][j] == 0) {
            bfs(mat, i - 1, j, visit, count);
        }
        if (i < mat.length - 1 && !visit[i + 1][j] && mat[i + 1][j] == 0) {
            bfs(mat, i + 1, j, visit, count);
        }
        if (j > 0 && !visit[i][j - 1] && mat[i][j - 1] == 0) {
            bfs(mat, i, j - 1, visit, count);
        }
        if (j < mat[i].length - 1 && !visit[i][j + 1] && mat[i][j + 1] == 0) {
            bfs(mat, i, j + 1, visit, count);
        }
    }

}

