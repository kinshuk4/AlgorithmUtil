package com.vaani.algo.array;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Created by Xiaomeng on 10/22/2014.
 */
public class FindMinimumInRotatedSortedArray2 {
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray2 test = new FindMinimumInRotatedSortedArray2();
        int[] num = {1, 1};
        System.out.println(test.findMin(num));
    }

    /**
     * For case where AL == AM == AR, the minimum could be on AM’s left or right side (eg,
     * [1, 1, 1, 0, 1] or [1, 0, 1, 1, 1]). In this case, we could not discard either subarrays and
     * therefore such worst case degenerates to the order of O(n)
     */
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;

        while (start < end && num[start] >= num[end]) {
            int mid = start + (end - start) / 2;
            if (num[mid] > num[end]) {
                start = mid + 1;
            } else if (num[mid] < num[start]) {
                end = mid;
            } else {
                start++;
            }
        }
        return num[start];
    }
}
