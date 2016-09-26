package com.vaani.algo.compete.codility.isoneedition;

class Solution {

    public static final String INSERT = "INSERT";
    public static final String DELETE = "DELETE";
    public static final String SWAP = "SWAP";
    public static final String NOTHING = "NOTHING";
    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    /**
     * @param S source string
     * @param T target string
     */
    public String solution(String S, String T) {
        if (Math.abs(S.length() - T.length()) > 1) {
            return IMPOSSIBLE;
        }
        int firstMismatchIndex = findFirstMismatchIndex(S, T);
        if (firstMismatchIndex == -1) {
            return NOTHING;
        }
        if (S.length() == T.length()) {
            return checkSwap(S, T, firstMismatchIndex);
        } else if (S.length() > T.length()) {
            return checkDelete(S, T, firstMismatchIndex);
        } else {
            return checkInsert(S, T, firstMismatchIndex);
        }
    }

    private String checkSwap(String S, String T, int mismatchIndex) {
        if (mismatchIndex != S.length() - 1) {
            char firstSwapChar = S.charAt(mismatchIndex);
            char secondSwapChar = S.charAt(mismatchIndex + 1);
            S = swapChars(S, mismatchIndex, mismatchIndex + 1);
            if (S.equals(T)) {
                return SWAP + " " + firstSwapChar + " " + secondSwapChar;
            } else {
                return IMPOSSIBLE;
            }
        } else {
            return IMPOSSIBLE;
        }
    }

    private String checkDelete(String S, String T, int mismatchIndex) {
        char deleteCharacter = S.charAt(mismatchIndex);
        if (equalsAfterIndex(S, mismatchIndex + 1, T, mismatchIndex)) {
            return DELETE + " " + deleteCharacter;
        } else {
            return IMPOSSIBLE;
        }
    }

    private String checkInsert(String S, String T, int mismatchIndex) {
        char insertChar = T.charAt(mismatchIndex);
        if (equalsAfterIndex(S, mismatchIndex, T, mismatchIndex + 1)) {
            return INSERT + " " + insertChar;
        } else {
            return IMPOSSIBLE;
        }
    }

    private int findFirstMismatchIndex(String f, String s) {
        for (int i = 0; i < f.length() && i < s.length(); i++) {
            if (f.charAt(i) != s.charAt(i)) {
                return i;
            }
        }
        return f.length() == s.length() ? -1 : f.length() < s.length() ? f.length() : s.length();
    }

    private boolean equalsAfterIndex(String f, int fIndex, String s, int sIndex) {
        while (fIndex < f.length() && sIndex < s.length()) {
            if (f.charAt(fIndex) != s.charAt(sIndex)) {
                return false;
            }
            fIndex++;
            sIndex++;
        }
        return fIndex == f.length() && sIndex == s.length();
    }

    private String swapChars(String s, int fIndex, int sIndex) {
        char[] c = s.toCharArray();
        char temp = c[fIndex];
        c[fIndex] = c[sIndex];
        c[sIndex] = temp;
        return new String(c);
    }

}

