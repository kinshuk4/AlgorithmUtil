package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Assume you have a method isSubString which checks if one word is substring of
 * another. Given two strings, s1 and s2, write code to check if s2 is a
 * rotation of s1 using only one call to isSubstring (e.g., "waterbottle" is a
 * rotation of "erbottlewat")
 */
public class Question8 {

    private boolean isSubstring(String haystack, String needle) {
        return haystack.contains(needle);
    }

    public boolean isRotation(String testee, String tester) {
        if (tester == null || testee == null) {
            return false;
        }
        String concatenated = tester + tester;
        return isSubstring(concatenated, testee);
    }
}

