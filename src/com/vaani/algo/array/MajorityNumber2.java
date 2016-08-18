package com.vaani.algo.array;

import java.util.List;

/**
 * Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.
 * <p>
 * Find it.
 * <p>
 * Note
 * There is only one majority number in the array
 * <p>
 * Example
 * For [1, 2, 1, 2, 1, 3, 3] return 1
 * <p>
 * Challenge
 * O(n) time and O(1) space
 */
public class MajorityNumber2 {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(List<Integer> nums) {
        if (nums.size() == 1) return nums.get(0);

        int candidate1 = nums.get(0);
        int count1 = 1;
        int i = 1;
        while (i < nums.size() && nums.get(i) == candidate1) {
            i++;
            count1++;
        }

        int candidate2 = nums.get(i);
        int count2 = 1;
        i++;
        while (i < nums.size()) {
            int num = nums.get(i);
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
            i++;
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            if (num == candidate2) count2++;
        }
        return count1 > count2 ? candidate1 : candidate2;
    }
}
