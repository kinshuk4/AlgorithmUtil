package com.vaani.algo.compete.hackerrank.misc;

/**
 * Created by andersonkmi on 8/6/2016.
 */
public class GenericTest<T extends Comparable> {
    public void run(T first, T second) {
        System.out.println(first.compareTo(second));
    }
}
