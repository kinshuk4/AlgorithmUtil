package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.Scanner;

public class ArmstrongNumbers {

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            String lineNumber = sc.nextLine().trim();
            System.out.println(isArmstrongNumber(lineNumber) ? "True" : "False");
        }
    }

    private static boolean isArmstrongNumber(String number) {
        int digits = number.length();
        long sum = 0;
        for (int i = 0; i < number.length(); i++) {
            sum += Math.pow(Character.getNumericValue(number.charAt(i)), digits);
        }
        return Long.valueOf(number) == sum;
    }
}
