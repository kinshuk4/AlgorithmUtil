package com.vaani.algo.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3Sum 变体，每个数字可以重复用
 * <p>
 * 比方说有
 * -6,-2,-1,3
 * 不重用的话 -2,-1,3
 * 可重用的话，估计 -6,3,3也是一组解
 */
public class ThreeSum2 {
    public static List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> single = new ArrayList<Integer>();
        Arrays.sort(num); // Don't need to sort if you don't want sorted result
        threeSum(num, 0, 0, 0, single, result);
        return result;
    }

    public static void threeSum(int[] num, int index, int count, int sum, List<Integer> single, List<List<Integer>> result) {
        if (index > num.length || count > 3) return;
        if (count == 3 && sum == 0) {
            result.add(new ArrayList<Integer>(single));
            return;
        }

        for (int i = index; i < num.length; i++) {
            single.add(num[i]);
            threeSum(num, i, count + 1, sum + num[i], single, result);
            single.remove(single.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] num = {-6, -2, -1, 3};
        for (List<Integer> single : threeSum(num)) {
            System.out.println(single);
        }
    }
}
