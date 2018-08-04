package com.vaani.algo.ds.algos.tree.binary;

import java.util.*;

/**
 * You have two arrays of strings, words and parts. Return an array that contains the strings from words, modified so that any occurrences of the substrings from parts are surrounded by square brackets [], following these guidelines:
 * <p>
 * If several parts substrings occur in one string in words, choose the longest one. If there is still more than one such part, then choose the one that appears first in the string.
 * <p>
 * Example
 * <p>
 * For words = ["Apple", "Melon", "Orange", "Watermelon"] and parts = ["a", "mel", "lon", "el", "An"], the output should be
 * findSubstrings(words, parts) = ["Apple", "Me[lon]", "Or[a]nge", "Water[mel]on"].
 * <p>
 * While "Watermelon" contains three substrings from the parts array, "a", "mel", and "lon", "mel" is the longest substring that appears first in the string.
 * <p>
 * <p>
 * [input] array.string words
 * <p>
 * An array of strings consisting only of uppercase and lowercase English letters.
 * <p>
 * Guaranteed constraints:
 * 0 ≤ words.length ≤ 104,
 * 1 ≤ words[i].length ≤ 30.
 * <p>
 * [input] array.string parts
 * <p>
 * An array of strings consisting only of uppercase and lower English letters. Each string is no more than 5 characters in length.
 * <p>
 * Guaranteed constraints:
 * 0 ≤ parts.length ≤ 105,
 * 1 ≤ parts[i].length ≤ 5,
 * parts[i] ≠ parts[j].
 */
public class FindSubstrings {

    static String[] findSubstrings(String[] words, String[] parts) {
        HashSet<String> set = new HashSet<>(Arrays.asList(parts));

        int MAX_PARTS_LENGTH = 5;

        String[] result = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            result[i] = words[i];
            int longest = 0;
            for (int l = MAX_PARTS_LENGTH; l >= 0; l--) { //intervals
                for (int k = 0; k + l <= cur.length(); k++) {
                    String part = cur.substring(k, k + l);
                    if (set.contains(part) && part.length() > longest) {
                        longest = part.length();
                        result[i] = cur.replaceFirst(part, "[" + part + "]");
                        break;
                    }
                }

            }
        }
        return result;
    }


}
