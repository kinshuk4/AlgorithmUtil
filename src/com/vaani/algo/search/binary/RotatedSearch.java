package com.vaani.algo.search.binary;

public class RotatedSearch {
    public static int rotatedSearch(int[] values, int start, int end,
                                    int x) {
        if (values[start] == x) {
            return start;
        } else if (values[end] == x) {
            return end;
        } else if (end - start == 1) {
            return -1;
        }
        int middle = (start + end) / 2;

        if (values[start] <= values[middle]) {
            if (x <= values[middle] && x >= values[start]) {
                return rotatedSearch(values, start, middle, x);
            } else {
                return rotatedSearch(values, middle, end, x);
            }
        } else if (values[middle] <= values[end]) {
            if (x >= values[middle] && x <= values[end]) {
                return rotatedSearch(values, middle, end, x);
            } else {
                return rotatedSearch(values, start, middle, x);
            }
        } else {
            return -1;
        }
    }

    public static void main(String args[]) {
        int[] rotationalArray = {3, 5, 6, 7, 9, 0, 2};
        int index = rotatedSearch(rotationalArray, 0, rotationalArray.length - 1, 0);
        System.out.println(index);
    }
}
