package com.vaani.algo.string;

/**
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * <p>
 * Created by Xiaomeng on 9/8/2014.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "bb";
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome(s));
    }

    /**
     * Time: O(n^2)
     * Alternative solution with Time O(n) and space O(n): http://leetcode.com/2011/11/longest-palindromic-substring-part-i.html
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String s1 = expand(s, i, i);
            if (s1.length() > longest.length()) longest = s1;
            String s2 = expand(s, i, i + 1);
            if (s2.length() > longest.length()) longest = s2;
        }
        return longest;
    }

    public String expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
