package com.vaani.algo.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 *  Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 *  For example,
 *  Given [100, 4, 200, 1, 3, 2],
 *  The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *
 *  Your algorithm should run in O(n) complexity.
 *
 *  Created by Xiaomeng on 9/2/2014.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        if(num.length == 0) return 0;
        Set<Integer> set = new HashSet<Integer>();
        for(int n : num) set.add(n);

        int maxLen = 0;
        for(int number : num){
            int len = 1;

            int n = number + 1;
            while(set.contains(n)){
                set.remove(n);
                n++;
                len++;
            }

            n = number - 1;
            while(set.contains(n)){
                set.remove(n);
                n--;
                len++;
            }
            maxLen = len > maxLen ? len : maxLen;
        }
        return maxLen;
    }

    public static void main(String[] args){
        int[] num = {100, 4, 200, 1, 3, 2};
        LongestConsecutiveSequence test = new LongestConsecutiveSequence();
        System.out.println(test.longestConsecutive(num));
    }
}
