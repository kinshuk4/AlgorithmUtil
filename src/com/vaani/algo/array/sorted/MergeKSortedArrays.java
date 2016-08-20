package com.vaani.algo.array.sorted;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge ‘k’ sorted arrays, each array may have max ‘n’ elements
 */
public class MergeKSortedArrays {
    public static int[] mergeKSortedArrays(int[][] arr, int n) {
        int k = arr.length;
        int[] result = new int[k * n];
        if (k == 0) return result;

        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                int num1 = e1.arr[e1.index];
                int num2 = e2.arr[e2.index];
                return num1 - num2;
            }
        };
        PriorityQueue<Element> queue = new PriorityQueue<Element>(k, comparator);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length > 0) queue.add(new Element(arr[i], 0));
        }

        int i = 0;
        while (!queue.isEmpty()) {
            Element element = queue.poll();
            result[i++] = element.arr[element.index];
            if (element.index + 1 < element.len) {
                element.index++;
                queue.add(element);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 6, 8, 9, 12, 14, 16};
        int[] arr2 = {3, 6, 7, 9, 22, 25, 28};
        int[] arr3 = {2, 5, 7, 8, 10, 11, 16};
        int[] arr4 = {4, 8, 23, 26, 28};
        int[][] arr = new int[][]{arr1, arr2, arr3, arr4};
        int[] result = mergeKSortedArrays(arr, 8);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static class Element {
        public int[] arr;
        public int index;
        public int len;

        public Element(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
            this.len = arr.length;
        }
    }
}
