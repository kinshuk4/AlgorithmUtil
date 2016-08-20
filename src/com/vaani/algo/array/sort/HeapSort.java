package com.vaani.algo.array.sort;

/**
 * A simple implementation of heap sort.
 *
 * @param <T> The comparable elements in heap.
 */
public class HeapSort<T extends Comparable<T>> {

    private int heapSize;

    public HeapSort() {
        this.heapSize = 0;
    }

    public void heapSort(T[] arr) {
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i >= 1; --i) {
            T tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            --this.heapSize;
            heapify(arr, 0);
        }
    }

    private void heapify(T[] arr, int i) {
        int largestIdx = i;
        int leftChildIdx = leftChild(i);
        if (leftChildIdx < heapSize && arr[leftChildIdx].compareTo(arr[largestIdx]) > 0) {
            largestIdx = leftChildIdx;
        }
        int rightChildIdx = rightChild(i);
        if (rightChildIdx < heapSize && arr[rightChildIdx].compareTo(arr[largestIdx]) > 0) {
            largestIdx = rightChildIdx;
        }
        if (largestIdx != i) {
            T tmp = arr[i];
            arr[i] = arr[largestIdx];
            arr[largestIdx] = tmp;
            heapify(arr, largestIdx);
        }
    }

    private void buildMaxHeap(T[] arr) {
        this.heapSize = arr.length;
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            heapify(arr, i);
        }
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }

}
