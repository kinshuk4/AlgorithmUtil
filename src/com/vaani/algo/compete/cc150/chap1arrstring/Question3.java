package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Given two strings, write a method to decide if one is a permutation of the
 * other.
 */
//  O(1) space, O(n) time
public class Question3 {

    public boolean isPermutation(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        int histFirst[] = new int[256];
        int histSecond[] = new int[256];
        for (int i = 0; i < first.length(); ++i) {
            ++histFirst[first.charAt(i)];
        }
        for (int i = 0; i < second.length(); ++i) {
            ++histSecond[second.charAt(i)];
        }
        for (int i = 0; i < histFirst.length; ++i) {
            if (histFirst[i] != histSecond[i]) {
                return false;
            }
        }
        return true;
    }

}

