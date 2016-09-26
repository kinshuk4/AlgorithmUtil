package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSearch {

    private final char[][] BOARD = new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}};
    private final int BOARD_ROW_SIZE = BOARD[0].length;
    private final int BOARD_SIZE = BOARD.length * BOARD[0].length;

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        WordSearch wordSearch = new WordSearch();
        while (sc.hasNextLine()) {
            String word = sc.nextLine().trim();
            System.out.println(wordSearch.contains(word) ? "True" : "False");
        }
    }

    public boolean contains(String word) {
        char[] wordChars = word.toCharArray();
        List<Integer> boardIndexes = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (getCharAtIndex(i) == wordChars[0]) {
                boardIndexes.add(i);
                if (checkWord(boardIndexes, 0, wordChars)) {
                    return true;
                } else {
                    boardIndexes.clear();
                }
            }
        }
        return false;
    }

    boolean checkWord(List<Integer> charBoardIndexes, int charWordIndex, char[] wordChars) {
        if (charWordIndex == wordChars.length - 1) {
            return true;
        }
        int currentCharBoardIndex = charBoardIndexes.get(charBoardIndexes.size() - 1);
        return checkIndex(charBoardIndexes, getLeftCharIndex(currentCharBoardIndex), charWordIndex, wordChars) ||
                checkIndex(charBoardIndexes, getRightCharIndex(currentCharBoardIndex), charWordIndex, wordChars) ||
                checkIndex(charBoardIndexes, getTopCharIndex(currentCharBoardIndex), charWordIndex, wordChars) ||
                checkIndex(charBoardIndexes, getBottomCharIndex(currentCharBoardIndex), charWordIndex, wordChars);
    }

    private boolean checkIndex(List<Integer> charBoardIndexes, int index, int charWordIndex, char[] wordChars) {
        char wordNextChar = wordChars[charWordIndex + 1];
        if (index >= 0 && !charBoardIndexes.contains(index) && getCharAtIndex(index) == wordNextChar) {
            charBoardIndexes.add(index);
            if (checkWord(charBoardIndexes, charWordIndex + 1, wordChars)) {
                return true;
            } else {
                charBoardIndexes.remove((Integer) index);
            }
        }
        return false;
    }

    int getLeftCharIndex(int charIndex) {
        int x = charIndex % BOARD_ROW_SIZE;
        return x == 0 ? -1 : charIndex - 1;
    }

    int getRightCharIndex(int charIndex) {
        int x = charIndex % BOARD_ROW_SIZE;
        return x == BOARD_ROW_SIZE - 1 ? -1 : charIndex + 1;
    }

    int getTopCharIndex(int charIndex) {
        return charIndex < BOARD_ROW_SIZE ? -1 : charIndex - BOARD_ROW_SIZE;
    }

    int getBottomCharIndex(int charIndex) {
        return charIndex + BOARD_ROW_SIZE >= BOARD_SIZE ? -1 : charIndex + BOARD_ROW_SIZE;
    }

    char getCharAtIndex(int charIndex) {
        if (charIndex < 0 || charIndex >= BOARD_SIZE) throw new IllegalArgumentException();
        return BOARD[charIndex / BOARD_ROW_SIZE][charIndex % BOARD_ROW_SIZE];
    }

}
