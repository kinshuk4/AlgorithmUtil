package com.vaani.algo.paradigm.recursion;

/**
 * Find the next smallest palindrome that larger than current one.
 */
public class NextPalindrome {

    public int nextPalindrome(int num) {
        StringBuilder sb = new StringBuilder().append(num);
        return nextPalindrome(sb);
    }

    private int nextPalindrome(StringBuilder num) {
        int len = num.length();
        boolean hasEvenDigit = len % 2 == 0;

        StringBuilder leftHalf = getLeftHalf(num);
        StringBuilder middle = getMiddle(num);

        int increment = (int) Math.pow(10, len / 2);
        StringBuilder res = null;

        if (hasEvenDigit) {
            increment = (int) (1.1 * Math.pow(10, len / 2)); // trick part
            StringBuilder copy = new StringBuilder(leftHalf);
            res = leftHalf.append(copy.reverse());
        } else {
            StringBuilder copy = new StringBuilder(leftHalf);
            res = leftHalf.append(middle).append(copy.reverse());
        }

        int resNum = Integer.parseInt(res.toString());
        if (resNum > Integer.parseInt(num.toString())) {
            return resNum;
        }

        if (middle.toString().charAt(0) != '9') {
            return resNum + increment;
        } else {
            return nextPalindrome(roundUp(res));
        }
    }

    private StringBuilder getLeftHalf(StringBuilder num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.replace(sb.length() / 2, sb.length(), ""); // remove the second half
        return sb;
    }

    private StringBuilder getMiddle(StringBuilder num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num.charAt(num.length() / 2));
        return sb;
    }

    private StringBuilder roundUp(StringBuilder num) {
        StringBuilder sb = new StringBuilder();
        int len = num.length();
        int increment = (int) Math.pow(10, len / 2);
        int v = Integer.parseInt(num.toString());
        sb.append((v / increment + 1) * increment);
        return sb;
    }

}
