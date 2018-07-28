package com.vaani.algo.compete.codesignal.interviewpractice;

/**
 * Given a sorted integer array that does not contain any duplicates, return a summary of the number ranges it contains.
 * <p>
 * Example
 * <p>
 * For nums = [-1, 0, 1, 2, 6, 7, 9], the output should be
 * composeRanges(nums) = ["-1->2", "6->7", "9"].
 */

import java.util.*;

public class ComposeRanges {
    static String[] composeRanges(int[] nums) {
        if(nums!=null && nums.length==1){
            return new String[] {""+nums[0]};
        }
        List<String> result = new LinkedList<String>();
        int end = 0;
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i] - 1) {
                end++;
                if (i == nums.length-1) {
                    result.add(nums[start] + "->" + nums[start + end]);
                }
            } else {

                    if (end > 0) {
                        result.add(nums[start] + "->" + nums[start + end]);
                    } else if (end == 0) {
                        result.add("" + nums[start]);
                    }
                    start = start + end + 1;
                    end = 0;

                    if (i == nums.length-1) {
                        result.add(""+nums[nums.length-1]);
                    }


            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(composeRanges(new int[]{-1, 0, 1, 2, 6, 7, 8})));
    }
}
