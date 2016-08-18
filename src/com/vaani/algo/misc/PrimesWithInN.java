package com.vaani.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Find out all the primes in range [0, n].
 */
public class PrimesWithInN {

    public List<Integer> primes(int n) {
        List<Integer> res = new ArrayList<Integer>();
        if (n < 2) {
            return res;
        }

        res.add(2);

        for (int i = 3; i <= n; ++i) {
            int squareRoot = (int) Math.sqrt(i);
            boolean valid = true;
            for (int j = 2; j <= squareRoot; ++j) {
                if (i % j == 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                res.add(i);
            }
        }

        return res;
    }

}
