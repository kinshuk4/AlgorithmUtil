package com.vaani.algo.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array. Find it.
 * <p>
 * Note
 * There is only one majority number in the array.
 * <p>
 * Example
 * For [3,1,2,3,2,3,3,4,4,4] and k = 3, return 3
 * <p>
 * Challenge
 * O(n) time and O(k) extra space
 */
public class MajorityNumber3 {
    /**
     * @param nums: A list of integers
     * @param k:    As described
     * @return: The majority number
     */
    public int majorityNumber(List<Integer> nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        int size = nums.size();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > size / k) return entry.getKey();
        }
        return -1;
    }
}
