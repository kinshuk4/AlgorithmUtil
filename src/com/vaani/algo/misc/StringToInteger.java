package com.vaani.algo.misc;

/**
 * Implement atoi to convert a string to an integer.
 * <p>
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * <p>
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 * <p>
 * Requirements for atoi:
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 * <p>
 * Created by Xiaomeng on 9/9/2014.
 */
public class StringToInteger {
    public static void main(String[] args) {
        StringToInteger test = new StringToInteger();
        String str = "-321";
        System.out.println(test.atoi(str));
    }

    public int atoi(String str) {
        str = str.trim();
        int len = str.length();
        if (len == 0) return 0;

        boolean positive = true;
        int i = 0;
        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            positive = false;
            i++;
        }

        int result = 0;
        int measure = Integer.MAX_VALUE / 10;
        while (i < len) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                if (result > measure || (result == measure && digit >= 8)) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                result = result * 10 + digit;
            } else {
                break;
            }
            i++;
        }
        return positive ? result : -result;
    }
}
