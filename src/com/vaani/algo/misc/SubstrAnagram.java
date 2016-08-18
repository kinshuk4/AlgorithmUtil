package com.vaani.algo.misc;

import java.util.Arrays;

/**
 * Find whether a substring of the haystack is the anagram of the needle.
 * <p>
 * Time complexity: O(N), space complexity: O(1).
 */
public class SubstrAnagram {
    public String substrAnagram(String haystack, String needle) {
        int[] needs = new int[256];
        int[] has = new int[256];

        for (int i = 0; i < needle.length(); ++i) {
            ++needs[(int) needle.charAt(i)];
        }

        int bufSize = 0;
        int start = 0;
        for (int end = 0; end < haystack.length(); ++end) {
            int ch = (int) haystack.charAt(end);
            if (needs[ch] == 0) { // not in needle
                start = end + 1;
                Arrays.fill(has, 0); // reset
                bufSize = 0;
            } else if (has[ch] == needs[ch]) {  // already enough
                while (haystack.charAt(start) != (char) ch) {
                    --has[(int) haystack.charAt(start)];
                    ++start;
                    --bufSize;
                }
                --has[ch];
                ++start;
            } else {
                ++has[ch];
                ++bufSize;
            }

            if (bufSize == needle.length()) {
                return haystack.substring(start, end + 1);
            }
        }
        return "";
    }

}
