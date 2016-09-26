package com.vaani.algo.compete.cc150.chapter9;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a method to compute all permutations of a string.
 */
public class Question5 {

    // available at leetcode.com online judge, question Permutations

    public class Solution {
        public ArrayList<ArrayList<Integer>> permute(int[] num) {
            // Start typing your Java solution below
            // DO NOT write main() function
            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            if (num == null || num.length == 0) {
                return lists;
            }
            ArrayList<Integer> self = new ArrayList<Integer>();
            Arrays.sort(num);
            for (int i : num) {
                self.add(i);
            }
            lists.add(self);
            while (-1 != next(num)) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i : num) {
                    list.add(i);
                }
                lists.add(list);
            }

            return lists;
        }

        private int next(int[] num) {
            // the last number that is less than its next number
            int k = -1;
            for (int i = num.length - 2; i >= 0; --i) {
                if (num[i] < num[i + 1]) {
                    k = i;
                    break;
                }
            }
            if (k == -1) { // no next permutation
                return -1;
            }
            // swap with the last element that is larger than num[k]
            for (int i = num.length - 1; i >= 0; --i) {
                if (num[i] > num[k]) {
                    int tmp = num[k];
                    num[k] = num[i];
                    num[i] = tmp;
                    break;
                }
            }
            ++k;
            int last = num.length - 1;
            while (k < last) {
                int tmp = num[k];
                num[k] = num[last];
                num[last] = tmp;
                ++k;
                --last;
            }
            return 0;
        }

    }
}
