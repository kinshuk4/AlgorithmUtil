package com.vaani.algo.string;

/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {
    /**
     * Assume X represents the one-edit character. There are three one-edit distance operations that could be applied to S.
     * i. Modify operation – Modify a character to X in S.
     * S = “abcde”
     * T = “abXde”
     * ii. Insert operation – X was inserted before a character in S.
     * S = “abcde”
     * T = “abcXde”
     * iii. Append operation – X was appended at the end of S.
     * S = “abcde”
     * T = “abcdeX”
     * <p>
     * O(n) runtime, O(1) space – Simple one-pass
     */
    public static boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n) return isOneEditDistance(t, s);
        if (n - m > 1) return false;
        int shift = n - m;
        int i = 0;
        while (i < m && s.charAt(i) == t.charAt(i)) i++;
        if (i == m) return shift > 0;
        if (shift == 0) i++;
        while (i < m && s.charAt(i) == t.charAt(i + shift)) i++;
        return i == m;
    }

    public static void main(String[] args) {
        String s = "abxxc";
        String t = "abxac";
        System.out.println(isOneEditDistance(s, t));
    }
}
