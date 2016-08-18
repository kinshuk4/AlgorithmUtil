package com.vaani.algo.string;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * For example,
 * Given s = "Hello World",
 * return 5.
 * <p>
 * Created by Xiaomeng on 9/1/2014.
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord test = new LengthOfLastWord();
        String s = "Hello World";
        System.out.println(test.lengthOfLastWord(s));
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;
        int i = s.length() - 1;
        int len = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
            len++;
        }
        return len;
    }
}
