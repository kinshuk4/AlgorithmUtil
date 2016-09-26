package com.vaani.algo.compete.cc150.chapter17;

/**
 * Design an algorithm to figure out if someone has won a game of tic-tac-toe.
 * The input is a NxN board with type char[][], where '.' denotes the slot that
 * has not been occupied, '+' denotes the slot that is occupied by player 1, and
 * '-' denotes the slot that is occupied by player 2.
 */
// O(1) space, O(n) time
public class Question2 {

    public boolean won(char[][] board) {
        // write implementation here
        if (checkDiagonal(board) || checkCounterDiagonal(board)) {
            return true;
        }
        for (int i = 0; i < board.length; ++i) {
            if (checkRow(i, board) || checkCol(i, board)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRow(int row, char[][] board) {
        for (int i = 0; i < board.length - 1; ++i) {
            if (board[row][i] != board[row][i + 1]) {
                return false;
            }
        }
        if (board[row][0] != '.') {
            return true;
        }
        return false;
    }

    private boolean checkCol(int col, char[][] board) {
        for (int i = 0; i < board.length - 1; ++i) {
            if (board[i][col] != board[i + 1][col]) {
                return false;
            }
        }
        if (board[0][col] != '.') {
            return true;
        }
        return false;
    }

    private boolean checkDiagonal(char[][] board) {
        char type = board[0][0];
        for (int i = 1; i < board.length; ++i) {
            if (board[i][i] != type) {
                return false;
            }
        }
        if (type != '.') {
            return true;
        }
        return false;
    }

    private boolean checkCounterDiagonal(char[][] board) {
        char type = board[board.length - 1][0];
        for (int i = 1; i < board.length; ++i) {
            if (board[i][board.length - 1 - i] != type) {
                return false;
            }
        }
        if (type != '.') {
            return true;
        }
        return false;
    }

}

