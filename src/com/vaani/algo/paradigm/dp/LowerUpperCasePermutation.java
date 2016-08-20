package com.vaani.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, generate all strings with identical character sequence,
 * but with different upper/lower cases.
 * <p>
 * Time complexity: O(2^N), Space complexity: O(2^N).
 */
public class LowerUpperCasePermutation {

    public List<String> lowerUpperCasePermutation(String str) {
        List<String> res = new ArrayList<String>();
        if (str.length() == 0) {
            return res;
        }
        String upperCase = str.toUpperCase();
        int pos = 0;
        char[] chars = upperCase.toCharArray();
        generate(chars, pos, res);
        return res;
    }

    private void generate(char[] chars, int pos, List<String> res) {
        if (pos == chars.length) {
            res.add(new String(chars));
        } else {
            // current character is upper case
            generate(chars, pos + 1, res);
            // current character is lower case
            chars[pos] = Character.toLowerCase(chars[pos]);
            generate(chars, pos + 1, res);
            chars[pos] = Character.toUpperCase(chars[pos]);
        }
    }

}
