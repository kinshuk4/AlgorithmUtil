package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */
//      use an array to store, O(1) space and O(n) time
public class Question1 {
    public boolean allUnique(String strings) {
        boolean table[] = new boolean[256];

        for (int i = 0; i < strings.length(); ++i) {
            char ch = strings.charAt(i);
            if (table[ch] == true) {
                return false;
            } else {
                table[ch] = true;
            }
        }

        return true;
    }
}

