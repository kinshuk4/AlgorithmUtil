package com.vaani.algo.compete.codeeval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class NumberOperations {

    public static void main(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("numberoperations")));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(checkVariations(parseInts(line), 0, 42) ? "YES" : "NO");
        }
    }

    private static int[] parseInts(String line) {
        String[] ints = line.split(" ");
        int[] integers = new int[ints.length];
        for (int i = 0; i < integers.length; i ++) {
            integers[i] = Integer.valueOf(ints[i]);
        }
        return integers;
    }

    private static boolean checkVariations(int[] numbers, int index, int target) {
        if (index == 0 && checkIfVariationExists(numbers, target)) {
            return true;
        }
        if (index < numbers.length - 1 && checkVariations(numbers, index + 1, target)) {
            return true;
        }
        for (int i = index - 1; i >= 0; i--) {
            int[] permutation = Arrays.copyOf(numbers, numbers.length);
            int buffer = permutation[index];
            permutation[index] = permutation[i];
            permutation[i] = buffer;
            if (checkIfVariationExists(permutation, target)) {
                return true;
            }
            if (index < numbers.length - 1 && checkVariations(permutation, index + 1, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfVariationExists(int[] numbers, int target) {
        return checkIfVariationExists(numbers[0], 1, numbers, target);
    }

    private static boolean checkIfVariationExists(int currentValue, int index, int[] numbers, int target) {
        return proceedOrReturn(currentValue + numbers[index], index + 1, numbers, target) ||
                proceedOrReturn(currentValue - numbers[index], index + 1, numbers, target) ||
                proceedOrReturn(currentValue * numbers[index], index + 1, numbers, target);
    }

    private static boolean proceedOrReturn(int currentValue, int index, int[] numbers, int target) {
        if (index < numbers.length) {
            return checkIfVariationExists(currentValue, index, numbers, target);
        } else {
            return currentValue == target;
        }
    }

}
