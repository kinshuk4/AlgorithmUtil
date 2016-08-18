package com.vaani.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all the anagrams of the target string from the given dictionary.
 * O(nmlogm) time, O(1) space.
 */
public class FindAnagramInDict {
    public List<String> findAnagramInDict(String[] pool, String target) {
        List<String> res = new ArrayList<String>();
        char[] anagram = target.toCharArray();
        Arrays.sort(anagram);

        for (String str : pool) {
            if (anagram.length == str.length()) {
                char[] ana = str.toCharArray();
                Arrays.sort(ana);
                boolean isAnagram = true;
                for (int i = 0; i < anagram.length; ++i) {
                    if (anagram[i] != ana[i]) {
                        isAnagram = false;
                        break;
                    }
                }
                if (isAnagram) {
                    res.add(str);
                }
            }
        }
        return res;
    }
}
