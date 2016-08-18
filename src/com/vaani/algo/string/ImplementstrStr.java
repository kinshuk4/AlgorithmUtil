package com.vaani.algo.string;

/**
 * Implement strStr().
 * <p>
 * Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
 * <p>
 * Created by Xiaomeng on 9/18/2014.
 */
public class ImplementstrStr {
    public static void main(String[] args) {
        ImplementstrStr test = new ImplementstrStr();
        String haystack = "i love qiqi";
        String needle = "qiqi";
        System.out.println(test.strStr(haystack, needle));
    }

    public String strStr(String haystack, String needle) {
        if (needle.isEmpty()) return haystack;
        int[] next = new int[needle.length()];
        buildNext(needle, next);
        int i = 0, j = 0;

        while (i < haystack.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == needle.length()) return haystack.substring(i - j);
        }
        return null;
    }

    private void buildNext(String needle, int[] next) {
        int j = 0, k = -1;
        next[0] = -1;
        while (j < needle.length() - 1) {
            if (k == -1 || needle.charAt(j) == needle.charAt(k)) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
    }
}
