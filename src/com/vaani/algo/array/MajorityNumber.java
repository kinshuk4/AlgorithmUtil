package com.vaani.algo.array;

import java.util.ArrayList;

/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fcommons%2FMajorityNumber.java
 * */

public class MajorityNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 2, 4, 2, 5, 8, 2};
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i : nums) {
            a.add(i);
        }
        System.out.println(new MajorityNumber().majorityNumber(a));
    }

    public int majorityNumber(ArrayList<Integer> nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1, count2;
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (candidate1 == nums.get(i)) {
                count1++;
            } else if (candidate2 == nums.get(i)) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = nums.get(i);
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums.get(i);
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == candidate1) {
                count1++;
            } else if (nums.get(i) == candidate2) {
                count2++;
            }
        }
        return count1 > count2 ? candidate1 : candidate2;
    }
}
