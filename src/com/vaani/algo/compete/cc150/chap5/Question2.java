package com.vaani.algo.compete.cc150.chap5;

/**
 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a
 * double, print the binary representation. If the number cannot be represented
 * accurately in binary with less than 32 characters, print "ERROR".
 */
// O(n) space, O(n) time
public class Question2 {

    public String binary(double num) {
        // write implementation here.
        if (num <= 0 || num >= 1) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        while (num > 0) {
            if (sb.length() >= 32) {
                return "ERROR";
            }
            num = num * 2;
            if (num >= 1) {
                num -= 1;
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }
}

