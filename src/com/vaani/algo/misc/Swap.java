package com.vaani.algo.misc;

/**
 * Created by Xiaomeng on 10/9/2014.
 */
public class Swap {
    public static void main(String[] args) {
        int a = 2, b = 4;
        System.out.println("Before swapping, a: " + a + " b: " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After swapping, a: " + a + " b: " + b);
    }
}
