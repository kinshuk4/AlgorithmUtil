package com.vaani.algo.array.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * For example,
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * <p>
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * Created by Xiaomeng on 9/16/2014.
 */
public class SurroundedRegions {
    Queue<Integer> queue;
    int rows;
    int cols;

    /*
    * BFS Solution
    * DFS: stack overflow for 250 * 250
    * */
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        queue = new LinkedList<Integer>();
        rows = board.length;
        cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            bfs(board, i, 0);
            bfs(board, i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {
            bfs(board, 0, j);
            bfs(board, rows - 1, j);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'P')
                    board[i][j] = 'O';
            }
        }
    }

    public void bfs(char[][] board, int x, int y) {
        fill(board, x, y);
        while (!queue.isEmpty()) {
            int total = queue.poll();
            int i = total / cols;
            int j = total % cols;
            fill(board, i - 1, j);
            fill(board, i + 1, j);
            fill(board, i, j - 1);
            fill(board, i, j + 1);
        }
    }

    public void fill(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= rows || y >= cols || board[x][y] != 'O') return;
        queue.offer(cols * x + y);
        board[x][y] = 'P';
    }
}
