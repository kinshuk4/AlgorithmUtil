package com.vaani.algo.paradigm.recursion;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */
public class ExcelSheetColumnTitle {
    /**
     * Reference: http://www.bubuko.com/infodetail-541678.html
     */
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }
}
