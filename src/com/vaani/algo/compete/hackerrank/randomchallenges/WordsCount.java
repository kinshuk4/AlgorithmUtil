package com.vaani.algo.compete.hackerrank.randomchallenges;

import java.util.Scanner;

/**
 * Created by mykola on 11.05.15.
 * http://www.glassdoor.com/Interview/Given-a-string-of-digits-where-every-digit-represents-a-letter-1-and-gt-a-2-and-gt-b-26-and-gt-z-Find-the-number-of-words-that-QTN_881358.htm
 */
public class WordsCount {

    private final static int ALPHABET_LETTERS = 26;

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String line;
        while ((line = sc.nextLine()) != null && !line.isEmpty()) {
            System.out.println(findCombinations(line));
        }
    }

    private static int findCombinations(String line) {
        return findCombinationsIncrement(line.toCharArray());
    }

    private static int findCombinationsIncrement(char[] digits) {
        int n = 1;
        int np = n;
        for(int i = 0 ; i < digits.length - 1; i++){
            int npp = np;
            np = n;
            char current = digits[i];
            char next = digits[i + 1];
            if(current == '1' || (current == '2' && next <= '6')){
                n = np + npp;
            }
        }
        return n;
    }

    private static int findCombinations(char[] digits, int offset) {
        if (offset >= digits.length) {
            return 0;
        }
        int combinations = offset == 0 ? 1 : 0;
        combinations += findCombinations(digits, offset + 1);
        if (offset < digits.length - 1 && (digits[offset] == '1' || digits[offset] == '2' && digits[offset + 1] <= '6')) {
            combinations++;
            combinations += findCombinations(digits, offset + 2);
        }
        return combinations;
    }

}
