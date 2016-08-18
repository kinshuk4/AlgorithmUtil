package com.vaani.algo.misc;

/*
 * Check whether the edit distance of two words are 1.
 */

public class OneDistance {

    public static boolean oneDistance(String w1, String w2) {
        if (w1.length() > w2.length()) {
            String tmp = w1;
            w1 = w2;
            w2 = tmp;
        }
        if (w2.length() - w1.length() > 1) {
            return false;
        }

        boolean diff = false;
        int i = 0, j = 0;
        while (i < w1.length()) {
            if (w1.charAt(i) != w2.charAt(j)) {
                if (diff) {
                    return false;
                }
                diff = true;
                if (w1.length() != w2.length()) {
                    ++j;
                    continue;
                }
            }
            ++i;
            ++j;
        }

        return diff || (w1.length() < w2.length());
    }

    public static void main(String[] args) {
        System.out.println(oneDistance("cat", "dog"));
        System.out.println(oneDistance("bag", "bug"));
        System.out.println(oneDistance("Internet", "Internet"));
        System.out.println(oneDistance("cat", "at"));
    }

}
