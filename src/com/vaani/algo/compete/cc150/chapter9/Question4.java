package com.vaani.algo.compete.cc150.chapter9;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a method to return all subsets of a set.
 */
public class Question4 {

    // available at leetcode.com online judge, question Subsets
    // O(2^n) space, O(2^n) time
    public class Solution {
        public ArrayList<ArrayList<Integer>> subsets(int[] S) {
            // Start typing your Java solution below
            // DO NOT write main() function
            Arrays.sort(S);
            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> curList = new ArrayList<Integer>();
            lists.add(curList);
            subsets(S, curList, lists, 0);
            return lists;
        }

        private void subsets(int[] S, ArrayList<Integer> curList,
                             ArrayList<ArrayList<Integer>> lists, int index) {
            if (index == S.length) {
                return;
            }
            // not contain cur
            subsets(S, curList, lists, index + 1);

            // contain cur
            ArrayList<Integer> copyList = new ArrayList<Integer>(curList);
            copyList.add(S[index]);
            lists.add(copyList);
            subsets(S, copyList, lists, index + 1);
        }
    }

}
