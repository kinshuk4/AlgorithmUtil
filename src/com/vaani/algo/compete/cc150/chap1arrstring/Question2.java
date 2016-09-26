package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Implement a function void reverse(String str) which reverses a
 * null-terminated string.
 */
// O(1) space, O(n) time
public class Question2 {
    public char[] reverse(char[] str) {
        if (str == null)
            return new char[]{};

        for (int i = 0; i < str.length / 2; ++i) {
            char tmp = str[i];
            str[i] = str[str.length - i - 1];
            str[str.length - i - 1] = tmp;
        }

        return str;
    }
}

