package com.vaani.algo.ds.utils;


import java.util.Iterator;
import java.util.TreeSet;

public class ArrayUtils {
    public static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start++, end--);
        }
    }

    public static void reverse(int[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        TreeSet<Integer> ts = new TreeSet<>();

    }

    public static Character[] toObjectArray(char[] list) {
        Character[] charArr = new Character[list.length];
        for (int i = 0; i < list.length; i++) {
            charArr[i] = list[i];
        }
        return charArr;
    }

    public static int[] toPrimitiveArray(Integer[] list) {
        int[] arr = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            arr[i] = list[i];
        }
        return arr;
    }
}
