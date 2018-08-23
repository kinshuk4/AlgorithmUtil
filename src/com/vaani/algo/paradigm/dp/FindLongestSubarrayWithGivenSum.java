package com.vaani.algo.paradigm.dp;

import java.util.*;

public class FindLongestSubarrayWithGivenSum {

    // function to find the length of longest
// subarray having sum k
    public static int lenOfLongSubarr(int arr[], int k) {

        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;

        // traverse the given array
        for (int i = 0; i < n; i++) {

            // accumulate sum
            sum += arr[i];

            // when subarray starts from index '0'
            if (sum == k)
                maxLen = i + 1;

            // make an entry for 'sum' if it is
            // not present in 'um'
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }


            // check if 'sum-k' is present in 'um'
            // or not
            if (map.containsKey(sum - k)) {

                // update maxLength
                if (maxLen < (i - map.get(sum - k)))
                    maxLen = i - map.get(sum - k);
            }
        }

        // required maximum length
        return maxLen;
    }

//    public static int[] findLongestSubarrayBySum(int[] arr, int s) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        int max = 0;
//        int sum = 0;
//
//        int start = 0;
//
//        for (int i = 0; i < arr.length; i++) {
//            sum += arr[i];
//            if (sum == s) {
//                max = i + 1;
//                start++;
//            }
//            if (map.containsKey(sum - s)) {
//                if (max < i - map.get(sum - s)) {
//                    max = i - map.get(sum - s);
//                    re[0] = map.get(sum - s) + 2;
//                    re[1] = i + 1;
//                }
//            }
//            map.put(sum, i);
//        }
//        System.out.println("max:" + max);
//        if (max == 0) return new int[]{-1};
//        else return re;
//
//    }
}
