package com.vaani.algo.misc;

/**
 * Calculate the arithmetic expression contains positive numbers,  + and -.
 * Time complexity O(N), space complexity O(N).
 */
public class PlusMinusCalculation {
    public int calculate(String expression) {
        char operator = ' ';
        int res = 0;
        for (int i = 0; i < expression.length(); ++i) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                int val = ch - '0';
                if (operator == '+') {
                    res += val;
                } else if (operator == '-') {
                    res -= val;
                } else {
                    res = val;
                }
            } else {
                operator = ch;
            }
        }

        return res;
    }
}
