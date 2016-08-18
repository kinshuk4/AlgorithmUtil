package com.vaani.algo.math;

/**
 * Write a program to find the square root of a double.
 */
public class SquareRoot {
    public static double sqrt(double n) {
        return getSquareRoot(n, 0, n);
    }

    public static double getSquareRoot(double n, double low, double high) {
        double errorMargin = 0.001;
        double sqrt = (low + high) / 2;
        double diff = sqrt * sqrt - n;
        if (diff > errorMargin)
            return getSquareRoot(n, low, sqrt);
        if (-diff > errorMargin)
            return getSquareRoot(n, sqrt, high);
        return sqrt;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(15));
    }
}
