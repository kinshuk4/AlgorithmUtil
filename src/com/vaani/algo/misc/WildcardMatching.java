package com.vaani.algo.misc;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * <p>
 * Created by Xiaomeng on 9/21/2014.
 */
public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching test = new WildcardMatching();
        System.out.println(test.isMatch("aa", "a"));
        System.out.println(test.isMatch("aa", "aa"));
        System.out.println(test.isMatch("aaa", "aa"));
        System.out.println(test.isMatch("aa", "*"));
        System.out.println(test.isMatch("aa", "a*"));
        System.out.println(test.isMatch("ab", "?*"));
        System.out.println(test.isMatch("aab", "c*a*b*"));
        System.out.println(test.isMatch("a", "aa"));
    }

    /*
    * Greedy: http://blog.csdn.net/perfect8886/article/details/22689147
    * */
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int star = -1, mark = -1;

        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                star = j++;
                mark = i;
            } else if (star != -1) {
                j = star + 1;
                i = ++mark;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*')
            j++;

        return j == p.length();
    }
}
