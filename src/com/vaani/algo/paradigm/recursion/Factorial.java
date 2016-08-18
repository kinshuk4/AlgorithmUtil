package com.vaani.algo.paradigm.recursion;


public class Factorial {
    /**
     * Recursive
     */
    public static int factorial1(int n) {
        if (n <= 1) return 1;
        return n * factorial1(n - 1);
    }

    /**
     * Iterative
     */
    public static int factorial2(int n) {
        int result = 1;
        if (n <= 1) return result;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial1(5));
        System.out.println(factorial2(5));
    }
}
