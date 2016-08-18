package com.vaani.algo.misc;

public class RemoveZerosFromArray {

    public int removeZerosFromArray(int[] arr) {
        int size = 0;
        int right = arr.length - 1;

        while (size <= right) {
            if (arr[size] == 0) {
                int tmp = arr[size];
                arr[size] = arr[right];
                --right;
            } else {
                ++size;
            }
        }
        return size;
    }

}
