package com.vaani.algo.compete.cc150.chapter17;

import java.util.HashMap;
import java.util.Map;

/**
 * Design an algorithm to find all pairs of integers within an array which sum
 * to a specified value.
 */
public class Question12 {
    // this problem is available at leetcode, question 2SUM
    public class Solution {
        public int[] twoSum(int[] numbers, int target) {
            // Start typing your Java solution below
            // DO NOT write main() function
            Map<Integer, Integer> index = new HashMap<Integer, Integer>();

            for (int i = 0; i < numbers.length; ++i) {
                Integer exists = index.get(i);
                if (exists != null && numbers[i] * 2 == target) {
                    return new int[]{exists + 1, i + 1};
                } else {
                    index.put(numbers[i], i);
                }
            }

            for (int i = 0; i < numbers.length; ++i) {
                Integer peerIdx = index.get(target - numbers[i]);
                if (peerIdx != null) {
                    int index1 = Math.min(i, peerIdx) + 1;
                    int index2 = Math.max(i, peerIdx) + 1;
                    return new int[]{index1, index2};
                }
            }
            return null;
        }
    }
}
