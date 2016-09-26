package com.vaani.algo.compete.hackerrank.misc;

/**
 * Created by andersonkmi on 8/6/2016.
 */
public class OtherGeneric {

    public <T> void run(T item) {
        System.out.println(item.toString());
    }

    public <T extends Comparable> int getGreatherThan(T[] array, T element) {
        return array[0].compareTo(element);
    }

    public static void main(String[] args) {
        OtherGeneric service = new OtherGeneric();
        service.run(1);
    }
}
