package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SelfDescribingNumbers {

    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            int number = Integer.valueOf(sc.nextLine().trim());
            System.out.println(isSelfDesc(number) ? "1" : "0");
        }
    }

    private static boolean isSelfDesc(int number) {
        Map<Integer, Integer> digitCounts = new HashMap<>();
        int numberCopy = number;
        int numberLength = 0;
        while (numberCopy >= 1) {
            int digit = numberCopy % 10;
            numberLength ++;
            if (!digitCounts.containsKey(digit)) {
                digitCounts.put(digit, 0);
            }
            digitCounts.put(digit, digitCounts.get(digit) + 1);
            numberCopy /= 10;
        }
        int digitPosition = 0;
        while (number >= 1) {
            if(number % 10 !=
                    (digitCounts.get(numberLength - digitPosition - 1) == null ? 0 : digitCounts.get(numberLength - digitPosition - 1))){
                return false;
            }
            digitPosition ++;
            number /= 10;
        }
        return true;
    }
}
