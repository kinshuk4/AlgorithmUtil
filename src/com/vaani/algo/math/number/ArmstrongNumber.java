package com.vaani.algo.math.number;

/**
 * Created by Xiaomeng on 10/9/2014.
 */
public class ArmstrongNumber {
    /**
     * 153 = 1^3 + 5^3 + 3^3
     * 371 = 3^3 +7^3 +1^3
     * 9474 = 9^4 + 4^4 +7^4 + 4^4
     * 54748 = 5^5 + 4^5 + 7^5 + 4^5 + 8^5
     */
    public static boolean isArmstrong(int input) {
        int n = input;
        int numOfDigits = (int) Math.log10((double) n) + 1;
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            sum += Math.pow(digit, numOfDigits);
            n = n / 10;
            if (sum > input) return false;
        }
        return sum == input;
    }

    public static void main(String[] args) {
        System.out.println(isArmstrong(153));
        System.out.println(isArmstrong(371));
        System.out.println(isArmstrong(9474));
        System.out.println(isArmstrong(54748));
        System.out.println(isArmstrong(12399));
    }
}
