package com.vaani.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string, S, and a list of words, L, that are all of the same length.
 * Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
 * <p>
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 * <p>
 * You should return the indices: [0,9].
 * (order does not matter).
 * <p>
 * Created by Xiaomeng on 9/14/2014.
 */
public class SubstringWithConcatenationAllWords {
    public static void main(String[] args) {
        SubstringWithConcatenationAllWords test = new SubstringWithConcatenationAllWords();
        String s = "barfoothefoobarman";
        String[] l = {"foo", "bar"};
        System.out.println(test.findSubstring(s, l));
    }

    /*
    * Better solution exists
    * */
    public List<Integer> findSubstring(String S, String[] L) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : L) {
            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            map.put(word, map.get(word) + 1);
        }

        List<Integer> result = new ArrayList<Integer>();
        int wordLen = L[0].length();
        int len = L.length * wordLen;

        for (int i = 0; i <= S.length() - len; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            String sub = S.substring(i, i + len);
            while (true) {
                String word = sub.substring(0, wordLen);
                if (copy.containsKey(word)) {
                    int count = copy.get(word) - 1;
                    if (count < 0) break;
                    copy.put(word, count);
                    sub = sub.substring(wordLen);
                    if (sub.isEmpty()) {
                        result.add(i);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Slightly better solution
     */
    public List<Integer> findSubstring2(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < L.length; i++) {
            if (map.containsKey(L[i])) {
                map.put(L[i], map.get(L[i]) + 1);
            } else {
                map.put(L[i], 1);
            }
        }

        int wordLen = L[0].length();
        int totalLen = L.length * wordLen;
        int i = 0;
        while (i + totalLen <= S.length()) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            int j = 0;
            while (j < L.length) {
                String word = S.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (copy.containsKey(word)) {
                    int count = copy.get(word);
                    if (count == 0) break;
                    copy.put(word, count - 1);
                    j++;
                } else {
                    break;
                }
            }
            if (j == L.length) result.add(i);
            i++;
        }
        return result;
    }
}
