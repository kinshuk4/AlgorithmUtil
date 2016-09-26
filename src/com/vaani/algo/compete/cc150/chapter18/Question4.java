package com.vaani.algo.compete.cc150.chapter18;

/**
 * Count the number of digit '2' within range of 0 to n.
 */
// O(1) space, O(d) time, where d is the length of number
public class Question4 {

    public int numOf2(int n) {
        // write implementation here
        if (n < 2) {
            return 0;
        }
        int numOf2 = 0;
        int power = 1;
        while (power * 10 < n) {
            power *= 10;
        }

        int highest = n / power; // in range [1, 10]
        int remaining = n - highest * power;
        if (highest == 2) { // numOf2 includes remaining + 1 in highest digit
            numOf2 += remaining + 1;
        } else if (highest > 2) { // include 2's in [20...0, 29...9]
            numOf2 += power;
        }

        numOf2 += highest * numOf2(power);

        numOf2 += numOf2(remaining);
        return numOf2;
    }

}
