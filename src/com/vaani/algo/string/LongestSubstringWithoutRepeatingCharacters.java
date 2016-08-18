package com.vaani.algo.string;

import java.util.Arrays;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 * <p>
 * Created by Xiaomeng on 9/6/2014.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters test = new LongestSubstringWithoutRepeatingCharacters();
        String s = "abcaefg";
        System.out.println(test.lengthOfLongestSubstring(s));
    }

    /*
    * http://harrifeng.github.io/algo/leetcode/longest-substring-without-repeating-characters.html
    *
    * O(n) : worst case 2n, O(2n) = O(n)
    * */
    public int lengthOfLongestSubstring(String s) {
        int[] table = new int[256];
        Arrays.fill(table, -1);
        int len = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (table[s.charAt(i)] != -1) {
                max = Math.max(max, len);
                len = 0;
                i = table[s.charAt(i)] + 1;
                Arrays.fill(table, -1);
            }
            table[s.charAt(i)] = i;
            len++;
        }
        return Math.max(max, len);
    }

    /**
     * One pass solution!
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.isEmpty()) return 0;
        int[] hash = new int[256];
        Arrays.fill(hash, -1);
        int i = 0;
        int max = 0;

        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (hash[ch] >= i) {
                i = hash[ch] + 1;
            }
            hash[ch] = j;
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
