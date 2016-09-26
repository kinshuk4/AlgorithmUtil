package com.vaani.algo.compete.cc150.chap7;

/**
 * Write methods to implement the multiply, subtract, and divide operations for
 * integers. Use only the add operator.
 */
public class Question4 {

    public int mul(int a, int b) {
        // write implementation here
        int signb = 1;
        if (b < 0) {
            b = -b;
            signb = -1;
        }
        int res = 0;
        for (int i = 0; i < b; ++i) {
            res += a;
        }
        if (signb < 0) {
            return -res;
        } else {
            return res;
        }
    }

    public int subtract(int a, int b) {
        // write implementation here
        int sign = 1;
        if (b < 0) {
            b = -b;
            sign = -1;
        }
        for (int i = 0; i < b; ++i) {
            if (sign == 1) {
                a += -1;
            } else {
                ++a;
            }
        }
        return a;
    }

    public int div(int a, int b) {
        // write implementation here
        int res = 0;
        int sign = 1;
        if (mul(a, b) < 0) {
            sign = -1;
        }
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        while (a >= b) {
            a -= b;
            ++res;
        }
        return mul(sign, res);
    }

}

