package com.vaani.algo.compete.hackerrank.sorting;

/**
 * Created by andersonkmi on 8/28/16.
 */
public class QuickSort {
    public void sort(int[] elements) {
        quicksort(elements, 0, elements.length - 1);
    }

    private void quicksort(int[] array, int left, int right) {
        int index = partition(array, left, right);
        if(left < index - 1) {
            quicksort(array, left, index - 1);
        }

        if(left < right) {
            quicksort(array, index, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];

        while(left <= right) {
            while(array[left] < pivot) {
                left++;
            }

            while(array[right] > pivot) {
                right--;
            }

            if(left <= right) {
                // swap
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
                left++;
                right--;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        QuickSort service = new QuickSort();
        int[] elements = {4, 1, 5, 34, 22, 9, 2};
        service.sort(elements);
        for(int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
    }
}
