package com.vaani.algo.string;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * <p>
 * Minimum window is "BANC".
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * <p>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * <p>
 * Created by Xiaomeng on 9/14/2014.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring test = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(test.minWindow(s, t));
    }

    /*
    * Two pointers: O(2n) ~ O(n)
    * Reference: http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
    * */
    public String minWindow(String S, String T) {
        if (T.length() > S.length()) return "";
        int[] needToFind = new int[256];
        int[] hasFind = new int[256];

        for (int i = 0; i < T.length(); i++) {
            needToFind[T.charAt(i)]++;
        }

        int count = 0;
        int min = Integer.MAX_VALUE;
        int minStart = 0, minEnd = 0;
        for (int start = 0, end = 0; end < S.length(); end++) {
            if (needToFind[S.charAt(end)] == 0) continue;
            hasFind[S.charAt(end)]++;
            if (hasFind[S.charAt(end)] <= needToFind[S.charAt(end)])
                count++;

            if (count == T.length()) {
                while (hasFind[S.charAt(start)] == 0
                        || hasFind[S.charAt(start)] > needToFind[S.charAt(start)]) {
                    if (hasFind[S.charAt(start)] > needToFind[S.charAt(start)])
                        hasFind[S.charAt(start)]--;
                    start++;
                }

                if (end - start + 1 < min) {
                    minStart = start;
                    minEnd = end;
                    min = end - start + 1;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : S.substring(minStart, minEnd + 1);
    }
}
