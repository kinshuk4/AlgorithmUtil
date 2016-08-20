package com.vaani.algo.array.sort;

import org.junit.Test;

import java.util.Arrays;

/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fsorting%2FSelectionSort.java
 * 
 */
public class SelectionSort {
    public void selectionSort(int[] nums) {
        int min;
        for (int i = 0; i < nums.length; i++) {
            min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    public void swap(int[] nums, int one, int two) {
        int temp = nums[one];
        nums[one] = nums[two];
        nums[two] = temp;
    }

    @Test
    public void test() {
        int[] nums = new int[]{9, 3, 4, 22, 33, 1, 9, 11, 10};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
