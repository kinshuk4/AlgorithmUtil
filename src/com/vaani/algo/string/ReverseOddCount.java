package com.vaani.algo.string;

import java.util.*;

/**
 * Reverse the order of all characters in a string that occur an odd amount of times (spaces included). All other characters should remain in the same position; only odd-frequency characters are eligible to swap with each other.
 * <p>
 * Case-sensitivity is important, so for example "a" is considered different than "A" when counting character frequencies.
 * <p>
 * Example
 * <p>
 * For str = "hello world", the output should be reverseOddCount(str) = "dlrwo loleh".
 */
public class ReverseOddCount {
    public static String reverseOddCount(String str) {
        boolean[] isOddArr = new boolean[str.length()];
        Map<Character, Integer> map = new HashMap<>();

        int n = str.length();
        char[] charArr = str.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = charArr[i];
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < n; i++) {
            char c = charArr[i];
            if (map.get(c) % 2 == 0) {
                isOddArr[i] = false;
            } else {
                isOddArr[i] = true;
            }
        }
        int l = 0;
        int r = n-1;
        while(l<r){
            if(isOddArr[l] && isOddArr[r]){
                char c = charArr[l];
                charArr[l] = charArr[r];
                charArr[r] = c;
                l++;
                r--;
            }
            while(!isOddArr[l]){
                l++;
            }

            while(!isOddArr[r]){
                r--;
            }
        }
        return new String(charArr);
    }


    public static String reverseOddCount2(String str) {
        Map<Character, Boolean> map = new HashMap<>();

        int n = str.length();

        char[] charArr = str.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = charArr[i];
            if (map.containsKey(c)) {
                map.put(c, !map.get(c));
            } else {
                map.put(c, true);
            }
        }

        int l = 0;
        int r = n-1;
        while(l<r){

            if(isOddOccuring(charArr, l, map) && isOddOccuring(charArr, r, map)){
                char c = charArr[l];
                charArr[l] = charArr[r];
                charArr[r] = c;
                l++;
                r--;
            }
            while(!isOddOccuring(charArr, l, map)){
                l++;
            }

            while(!isOddOccuring(charArr, r, map)){
                r--;
            }
        }
        return new String(charArr);
    }
    private static boolean isOddOccuring(char[] charArr, int idx, Map<Character, Boolean> map){
        return map.get(charArr[idx]);
    }
    public static void main(String[] args) {
        System.out.println(reverseOddCount2("hello world"));
    }
}
