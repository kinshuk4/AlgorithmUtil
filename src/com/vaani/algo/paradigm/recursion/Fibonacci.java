package com.vaani.algo.paradigm.recursion;

import java.util.HashMap;
import java.util.Map;


public class Fibonacci {
    /**
     * Native recursive without memorization
     */
    public static int fibonacci1(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    /**
     * Recursive with memorization
     */
    public static int fibonacci2(int n) {
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        memo.put(0, 0);
        memo.put(1, 1);
        if (!memo.containsKey(n))
            memo.put(n, fibonacci1(n - 1) + fibonacci1(n - 2));
        return memo.get(n);
    }

    /**
     * Iterative
     */
    public static int fibonacci3(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int first = 0, second = 1, result = 0;
        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci1(8));
        System.out.println(fibonacci2(10));
        System.out.println(fibonacci3(12));
    }
}
