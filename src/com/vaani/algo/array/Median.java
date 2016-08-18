package com.vaani.algo.array;

/**
 * Given a unsorted array with integers, find the median of it.
 * <p>
 * A median is the middle number of the array after it is sorted.
 * <p>
 * If there are even numbers in the array, return the N/2-th number after sorted.
 * <p>
 * Example
 * Given [4, 5, 1, 2, 3], return 3
 * Given [7, 9, 4, 5], return 5
 * <p>
 * Challenge
 * O(n) time.
 */
public class Median {
    public static void main(String[] args) {
        int[] nums = {-1, -2, -3, -100, -1, -50};
        Median test = new Median();
        System.out.println(test.median(nums));
    }

    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        int len = nums.length;
        if (len % 2 == 0) {
            return findKth(nums, len / 2 - 1);
        } else {
            return findKth(nums, len / 2);
        }
    }

    private int findKth(int[] nums, int k) {
        return findKth(nums, 0, nums.length - 1, k);
    }

    private int findKth(int[] nums, int start, int end, int k) {
        int partitionIndex = partition(nums, start, end);
        if (partitionIndex > k) {
            return findKth(nums, start, partitionIndex - 1, k);
        } else if (partitionIndex < k) {
            return findKth(nums, partitionIndex + 1, end, k);
        } else {
            return nums[partitionIndex];
        }
    }

    private int partition(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        swap(nums, mid, end);
        int pivot = nums[end];
        int low = start;
        int high = end - 1;
        while (low <= high) { // --> be careful, it need to has '='!
            if (nums[low] >= pivot) {
                swap(nums, low, high--);
            } else {
                low++;
            }
        }
        swap(nums, low, end);
        return low;
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
