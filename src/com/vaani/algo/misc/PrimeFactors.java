package com.vaani.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Find out all the prime factors of a given number.
 */
public class PrimeFactors {

    public List<Integer> primeFactor(int n) {
        List<Integer> primes = new ArrayList<Integer>();

        int prime = 2;

        while (n > 1) {
            if (n % prime == 0) {
                primes.add(prime);
                n /= prime;
            } else {
                ++prime;
            }
        }

        return primes;
    }

}
