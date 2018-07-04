package com.vaani.algo.array;


import com.vaani.algo.ds.utils.DigitUtils;

import java.util.*;

/**
 * Digit root of some positive integer is defined as the sum of all of its digits.
 * <p>
 * You are given an array of integers. Sort it in such a way that if a comes before b then the digit root of a is less than or equal to the digit root of b. If two numbers have the same digit root, the smaller one (in the regular sense) should come first. For example 4 and 13 have the same digit root, however 4 < 13 thus 4 comes before 13 in any digitRoot sorting where both are present.
 * <p>
 * Example
 * <p>
 * For a = [13, 20, 7, 4], the output should be [].
 */
public class DigiRootSort {
    static int[] digitRootSort(int[] a) {
        Map<Integer, List<Integer>> map = new TreeMap<>();

        for (int i = 0; i < a.length; i++) {
            int b = DigitUtils.sumDigits(a[i]);
            if (!map.containsKey(b)) {
                List<Integer> list = new LinkedList<>();
                map.put(b, list);

            }

            map.get(b).add(i);

        }
        int[] result = new int[a.length];
        int i = 0;
        for (Map.Entry k : map.entrySet()) {
            List<Integer> list = map.get(k.getKey());
            if (list.size() == 1) {
                result[i++] = a[list.get(0)];
            }else{
                List<Integer> newList = new LinkedList<>();
                for(int idx: list){
                    newList.add(a[idx]);
                }
                Collections.sort(newList);
                for(int el: newList){
                    result[i++] = el;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {20, 4, 13, 7};
        System.out.println(Arrays.toString(digitRootSort(arr)));
    }
}
