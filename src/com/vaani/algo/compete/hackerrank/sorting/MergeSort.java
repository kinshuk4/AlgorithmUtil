package com.vaani.algo.compete.hackerrank.sorting;

/**
 * Created by andersonkmi on 8/28/16.
 */
public class MergeSort {
    public void sort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
    }

    private void mergesort(int[] array, int[] helper, int low, int high) {
        if(low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle);
            mergesort(array, helper, middle + 1, high);
            merge(array, helper, low, middle, high);
        }
    }

    private void  merge(int[] array, int[] helper, int low, int middle, int high) {
        for(int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while(helperLeft <= middle && helperRight <= high) {
            if(helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        int remaining = middle - helperLeft;
        for(int i = 0; i <= remaining; i++) {
            array[current + i] = helper[helperLeft + i];
        }
    }

    public static void main(String[] args) {
        MergeSort service = new MergeSort();
        int[] elements = {4, 1, 5, 34, 22, 9, 2};
        service.sort(elements);
        for(int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
    }
}
