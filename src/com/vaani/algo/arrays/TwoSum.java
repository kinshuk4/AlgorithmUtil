package com.vaani.algo.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 *
 * Created by Xiaomeng on 7/16/2014.
 */
public class TwoSum {
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(numbers[i])){
                if(target == numbers[i] * 2){
                    result[0] = map.get(numbers[i]) + 1;
                    result[1] = i + 1;
                    return  result;
                }
            }else{
                map.put(numbers[i], i);
            }
        }

        Arrays.sort(numbers);

        int i = 0, j = numbers.length - 1;
        while(i < j){
            if(numbers[i] + numbers[j] > target){
                j--;
            }else if(numbers[i] + numbers[j] < target){
                i++;
            }else{
                result[0] = Math.min(map.get(numbers[i]) + 1, map.get(numbers[j]) + 1);
                result[1] = Math.max(map.get(numbers[i]) + 1, map.get(numbers[j]) + 1);
                return result;
            }
        }
        return result;
    }

    public static int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(target - numbers[i])){
                result[0] = map.get(target - numbers[i]) + 1;
                result[1] = i + 1;
                return result;
            }else{
                map.put(numbers[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {3, 2, 4};
        int[] result = twoSum2(nums, 6);
        System.out.println(result[0] + " " + result[1]);
    }
}
