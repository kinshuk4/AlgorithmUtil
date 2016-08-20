package com.vaani.algo.array.sort;

import java.util.Arrays;

/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fsorting%2FInsertSort.java
 * 
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = new int[]{9090, 4, 5, 2, 3, 1, 4, 5, 4, 4, 8989, 4};
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
        System.out.println(Arrays.toString(nums));

    }

    //
    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
