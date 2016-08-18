package com.vaani.algo.number;

public class IntegerPallindrome {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static boolean isPalindrome1(int x) {
        if (x < 0) return false;
        return x == reverseInt(x);
    }

    public static int reverseInt(int a) {
        int r = 0;
        while (a != 0) {
            r = a % 10 + r * 10;
            a = a / 10;
        }
        return r;

    }
}
