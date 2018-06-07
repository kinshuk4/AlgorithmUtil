package com.vaani.algo.math.number;

import java.util.*;

/**
 * Your Informatics teacher at school likes coming up with new ways to help you understand the material. When you started studying numeral systems, he introduced his own numeral system, which he's convinced will help clarify things. His numeral system has base 26, and its digits are represented by English capital letters - A for 0, B for 1, and so on.
 *
 * The teacher assigned you the following numeral system exercise: given a one-digit number, you should find all unordered pairs of one-digit numbers whose values add up to the number.
 *
 * Example
 *
 * For number = 'G', the output should be
 * newNumeralSystem(number) = ["A + G", "B + F", "C + E", "D + D"].
 *
 * Translating this into the decimal numeral system we get: number = 6, so it is ["0 + 6", "1 + 5", "2 + 4", "3 + 3"].
 *
 * Input/Output
 *
 * [execution time limit] 3 seconds (java)
 *
 * [input] char number
 *
 * A character representing a correct one-digit number in the new numeral system.
 *
 * Guaranteed constraints:
 * 'A' ≤ number ≤ 'Z'.
 */
public class NewNumeralSystem {
    static String[] newNumeralSystem(char number) {
        int numInt = number - 65;
        char[] numbers = new char[26];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (char) (65 + i);
        }


        int l = 0, r = 25;
        List<String> list = new LinkedList<String>();
        char c = numbers[l];
        char d = numbers[r];
        int sum = c + d - 2 * 65;
        while (l < r) {
            c = numbers[l];
            d = numbers[r];
            sum = c + d - 2 * 65;

            while (sum < numInt) {
                l++;
                c = numbers[l];
                d = numbers[r];
                sum = c + d - 2 * 65;

            }

            while (sum > numInt) {
                r--;
                c = numbers[l];
                d = numbers[r];
                sum = c + d - 2 * 65;

            }


            if (sum == numInt) {
                list.add(new String(c + " + " + d));
            }
            l++;

        }

        return list.toArray(new String[list.size()]);

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(newNumeralSystem('G')));
    }
}
