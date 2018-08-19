package com.vaani.algo.math.number;

import com.vaani.algo.ds.utils.DigitUtils;

/**
 * Ticket numbers usually consist of an even number of digits. A ticket number is considered lucky if the sum of the first half of the digits is equal to the sum of the second half.
 * <p>
 * Given a ticket number n, determine if it's lucky or not.
 * <p>
 * Example
 * <p>
 * For n = 1230, the output should be
 * isLucky(n) = true;
 * For n = 239017, the output should be
 * isLucky(n) = false.
 */
public class IsLucky {
    static boolean isLucky(int n) {
        int numDigits = DigitUtils.numDigits(n);
        int sum = 0;
        for (int i = 0; i < numDigits / 2; i++) {
            sum += (n % 10);
            n = n / 10;

        }

        for (int i = 0; i < numDigits / 2; i++) {
            sum -= (n % 10);
            n = n / 10;
        }

        return sum == 0;
    }


    public static void main(String[] args) {
        System.out.println(isLucky(1230));
        System.out.println(isLucky(239017));
    }
}
