package com.vaani.algo.array.sort;

import org.junit.Test;

import java.util.Arrays;

/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fsorting%2FBubbleSort.java
 * 
 */
public class BubbleSort {
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    public void swap(int[] nums, int one, int two) {
        int temp = nums[one];
        nums[one] = nums[two];
        nums[two] = temp;
    }

    @Test
    public void test() {
        int[] nums = new int[]{7, 3, 4, 23, 3, 9};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}