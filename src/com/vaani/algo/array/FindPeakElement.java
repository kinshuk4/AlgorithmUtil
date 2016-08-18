package com.vaani.algo.array;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] num = {4, 3, 2, 1};
        FindPeakElement test = new FindPeakElement();
        System.out.println(test.findPeakElement(num));
    }

    /**
     * Binary Search: O(logN)
     * <p>
     * http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
     */
    public int findPeakElement(int[] num) {
        return findPeakElement(num, 0, num.length - 1);
    }

    public int findPeakElement(int[] num, int start, int end) {
        int mid = start + (end - start) / 2;

        if ((mid == 0 || num[mid - 1] < num[mid]) && (mid == num.length - 1 || num[mid + 1] < num[mid])) return mid;

        if (mid > 0 && num[mid - 1] > num[mid]) {
            return findPeakElement(num, start, mid - 1);
        } else {
            return findPeakElement(num, mid + 1, end);
        }
    }
}
