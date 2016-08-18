package com.vaani.algo.search.binary;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstrings%2FBinarySearch.java
public class BinarySearch {
    private static int recursionBinarySearch(int[] nums, int start, int end, int key) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) >> 1;
        if (nums[mid] == key) {
            return mid;
        } else if (nums[mid] > key) {
            return recursionBinarySearch(nums, start, mid - 1, key);
        } else {
            return recursionBinarySearch(nums, mid + 1, end, key);
        }


    }


    private static int nonRecursionBinarySearch(int[] nums, int key) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == key) {
                return mid;
            } else if (nums[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11};
        int i = recursionBinarySearch(nums, 0, nums.length - 1, 11);
        System.out.println(i);
        int i1 = nonRecursionBinarySearch(nums, 1);
        System.out.println(i1);
    }
}
