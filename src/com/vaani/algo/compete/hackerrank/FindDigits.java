package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;

/**
 * Created by andersonkmi on 7/23/16.
 */
public class FindDigits {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testQuantity = in.nextInt();
        int arr[] = new int[testQuantity];
        for(int arr_i=0; arr_i < testQuantity; arr_i++){
            arr[arr_i] = in.nextInt();
        }

        // Creates an array with results;
        int[] results = new int[testQuantity];

        for(int index = 0; index < testQuantity; index++) {
            char[] digits = String.valueOf(arr[index]).toCharArray();

            for (char digit : digits) {
                int digitValue = Character.digit(digit, 10);
                if (digitValue > 0) {
                    if (arr[index] % digitValue == 0) {
                        results[index] = results[index] + 1;
                    }
                }
            }
        }

        // Prints the result
        for (int result : results) {
            System.out.println(result);
        }
    }
}
