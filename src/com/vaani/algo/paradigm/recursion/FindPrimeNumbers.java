package com.vaani.algo.paradigm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaomeng on 10/9/2014.
 */
public class FindPrimeNumbers {
    public static List<Integer> findPrimeNumbers(int n) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) result.add(i);
        }
        return result;
    }

    /**
     * Better solution!
     * http://leetcode.com/2010/04/finding-prime-numbers.html
     */
    public static void prime_sieve(int n) {
        boolean[] prime = new boolean[n];
        prime[0] = false;
        prime[1] = false;
        int i;
        for (i = 2; i <= n; i++)
            prime[i] = true;

        double limit = Math.sqrt(n);
        for (i = 2; i <= limit; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;
            }
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt((double) n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt(2));
        System.out.println(Math.sqrt(3));
        System.out.println(Math.sqrt(4));
        System.out.println(Math.sqrt(5));
        System.out.println(Math.sqrt(6));
    }

}
