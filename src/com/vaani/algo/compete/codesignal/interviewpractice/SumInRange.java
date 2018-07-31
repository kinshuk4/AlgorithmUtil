package com.vaani.algo.compete.codesignal.interviewpractice;

import java.util.*;
public class SumInRange {
    int sumInRange(int[] nums, int[][] queries) {
        if(nums==null || nums.length==0 || queries == null || queries.length==0) {
            return 0;
        }
        int mod = (int)Math.pow(10,9) + 7;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < nums.length; i++){
            sum += nums[i];
            map.put(i, sum%mod);
        }
        int sumInRange = 0;
        for(int[] query: queries){
            int start = query[0];
            int end = query[1];

            // if range starts at 0
            if(start==0){
                sumInRange = sumInRange % mod + map.get(end) % mod;
            }else{
                int sumInRangeToStart = map.get(start-1); // [0 - start-1]
                int sumInRangeToEnd = map.get(end);
                sumInRange = sumInRange % mod + (sumInRangeToEnd - sumInRangeToStart) % mod;
            }
        }
        return modulo(sumInRange, mod);    // or 	  (sumInRange+ mod)%mod;


    }

    static int modulo(int number, int divisor){
        if(number < 0 ){
            return divisor - ((0-number) % divisor);
        }else{
            return number % divisor;
        }
    }


}
