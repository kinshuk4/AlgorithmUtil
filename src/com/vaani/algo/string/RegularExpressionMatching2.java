package com.vaani.algo.string;

/**
 * Implement a simple regex parser which, given a string and a pattern, returns a boolean indicating whether the input matches the pattern.
 * By simple, we mean that the regex can only contain special character: * (star), . (dot), + (plus).
 * The star means what you'd expect, that there will be zero or more of previous character in that place in the pattern.
 * The dot means any character for that position. The plus means one or more of previous character in that place in the pattern.
 */
public class RegularExpressionMatching2 {
    public static void main(String[] args) {
        RegularExpressionMatching2 test = new RegularExpressionMatching2();
        //Regular test cases with only * and .
        System.out.println(test.isMatch("aa", "a"));         //--> false
        System.out.println(test.isMatch("aa", "aa"));        //--> true
        System.out.println(test.isMatch("aa", "aaa"));       //--> false
        System.out.println(test.isMatch("aa", "a*"));        //--> true
        System.out.println(test.isMatch("aa", ".*"));        //--> true
        System.out.println(test.isMatch("ab", ".*"));        //--> true
        System.out.println(test.isMatch("aab", "c*a*b*"));   //--> true
        System.out.println(test.isMatch("ab", ".*c"));       //--> false

        //More test cases with +
        System.out.println(test.isMatch("abcdef", "a+c"));                 //--> false
        System.out.println(test.isMatch("abcccccccdef", "abc+def"));       //--> true
        System.out.println(test.isMatch("abdef", "abc+def"));              //--> false
    }

    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p, int i, int j) {
        if (j == p.length() - 1) return (i == s.length() - 1) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j == p.length()) return i == s.length();

        if (p.charAt(j + 1) != '*') {
            if (i == s.length()) return false;
            if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.') {
                return false;
            }

            if (p.charAt(j + 1) == '+') {
                while (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
                    if (isMatch(s, p, i + 1, j + 2)) {
                        return true;
                    }
                    i++;
                }
                return false;
            } else {
                return isMatch(s, p, i + 1, j + 1);
            }
        }

        while (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
            if (isMatch(s, p, i, j + 2)) {
                return true;
            }
            i++;
        }
        return isMatch(s, p, i, j + 2);
    }
}
