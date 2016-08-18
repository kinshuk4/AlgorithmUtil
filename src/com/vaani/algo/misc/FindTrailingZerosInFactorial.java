package com.vaani.algo.misc;

/**
 * Given an integer n, write a function that returns count of trailing zeroes in n!.
 * <p>
 * Examples:
 * Input: n = 5
 * Output: 1
 * Factorial of 5 is 120 which has one trailing 0.
 * <p>
 * Input: n = 20
 * Output: 4
 * Factorial of 20 is 2432902008176640000 which has
 * 4 trailing zeroes.
 * <p>
 * Input: n = 100
 * Output: 24
 * <p>
 * Reference: http://www.geeksforgeeks.org/count-trailing-zeroes-factorial-number/
 */
public class FindTrailingZerosInFactorial {
    public static int findTrailingZeros(int n) {
        int count = 0;
        while (n != 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    /**
     * Why is this wrong?
     */
    public static int findTrailingZeros2(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println(findTrailingZeros(100));
    }

    /**
     * My solution
     */
    public int trailingZeroes(int n) {
        int fives = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 5 == 0) {
                fives += 1;
                num /= 5;
            }
        }
        return fives;
    }
}
