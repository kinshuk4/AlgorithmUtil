package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;

/**
 * Created by mykola on 03.08.15.
 */
public class VeryBigSum {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long sum = 0;
        while (n > 0) {
            n--;
            sum += scanner.nextInt();
        }
        System.out.println(sum);
    }

}
