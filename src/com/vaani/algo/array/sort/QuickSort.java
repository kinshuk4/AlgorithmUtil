package com.vaani.algo.array.sort;

public class QuickSort {

    public static void quickSort(int values[]) {
        if (values == null || values.length == 0) {
            return;
        } else quickSortHelper(values, 0, values.length - 1);
    }

    private static int partition(int[] values, int left, int right) {
        int l = left + 1, r = right, pivot_index = left;
        int p = values[pivot_index];
        while (l <= r) {
            while (l < right && values[l] < p) l++;
            while (r > left && values[r] >= p) r--;
            if (l <= r) {
                swapValuesInArray(values, l, r);
                l++;
                r--;
            }
        }

        swapValuesInArray(values, r, pivot_index);
        return r;
        // Recursion
        //		if (left < r)
        //			partition(values,left, r);
        //		if (l < right)
        //			partition(values,l, right);
    }

    private static void quickSortHelper(int arr[], int left, int right) {

        if (left >= right)
            return;

        int index = partition(arr, left, right);

        if (left < index - 1)
            quickSortHelper(arr, left, index);

        if (index < right)
            quickSortHelper(arr, index + 1, right);

    }

    private static void swapValuesInArray(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int a[] = {40, 20, 10, 80, 60, 50, 7, 30, 100};
        quickSort(a);
        printArray(a);
    }

    private static void printArray(int[] a) {
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
    }


}
