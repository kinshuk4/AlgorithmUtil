package com.vaani.algo.array;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * Created by Xiaomeng on 7/22/2014.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (len == 0) return digits;
        boolean allNine = true;
        for (int digit : digits) {
            if (digit != 9) allNine = false;
        }
        if (allNine) {
            int[] result = new int[len + 1];
            result[0] = 1;
            return result;
        } else {
            int i = len - 1;
            while (i >= 0) {
                if (digits[i] + 1 < 10) {
                    digits[i] += 1;
                    return digits;
                } else {
                    digits[i] = 0;
                    i--;
                }
            }
            return digits;
        }
    }

}
