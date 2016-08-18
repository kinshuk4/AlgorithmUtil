package com.vaani.algo.string;

/**
 * Given an input string, reverse the string word by word.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * <p>
 * click to show clarification.
 * <p>
 * Clarification:
 * <p>
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 * <p>
 * Created by Xiaomeng on 9/12/2014.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        String s = "the sky is blue";
        ReverseWordsInString test = new ReverseWordsInString();
        System.out.println(test.reverseWords(s));
    }

    public String reverseWords(String s) {
        s = s.trim();
        if (s.isEmpty()) return s;

        StringBuilder result = new StringBuilder();
        int index = s.length() - 1;
        while (index >= 0) {
            int end = index;
            while (index >= 0 && s.charAt(index) != ' ') index--;
            result.append(s.substring(index + 1, end + 1));
            if (index >= 0) result.append(" ");
            while (index >= 0 && s.charAt(index) == ' ') index--;
        }
        return result.toString();
    }
}
