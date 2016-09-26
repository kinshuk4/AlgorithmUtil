package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Implement a method to perform basic string compression using the counts of
 * repeated characters.
 * <p>
 * For example, the string 'aabcccccaaa' would become a2b1c5a3. If the
 * "compressed" string would not become smaller than the original string, your
 * method should return the original string.
 */
// O(n) space O(n) time
public class Question5 {

    public String encoding(String original) {
        if (original == null || original.length() <= 1) {
            return original;
        }

        StringBuilder sb = new StringBuilder();

        char curChar = original.charAt(0);
        int count = 1;
        for (int i = 1; i < original.length(); ++i) {
            char ch = original.charAt(i);
            if (ch == curChar) {
                ++count;
            } else {
                sb.append(curChar);
                sb.append(count);
                curChar = ch;
                count = 1;
            }
        }
        sb.append(curChar);
        sb.append(count);

        if (sb.length() < original.length()) {
            return sb.toString();
        } else {
            return original;
        }
    }

}

