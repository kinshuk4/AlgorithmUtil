package com.vaani.algo.paradigm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider a coding system for alphabets to integers where ‘a’ is represented as 1, ‘b’ as 2, .. ‘z’ as 26.
 * Given an array of digits (1 to 9) as input, write a function that prints all valid interpretations of input array.
 * <p>
 * Examples
 * <p>
 * Input: {1, 1}
 * Output: ("aa", 'k")
 * [2 interpretations: aa(1, 1), k(11)]
 * <p>
 * Input: {1, 2, 1}
 * Output: ("aba", "au", "la")
 * [3 interpretations: aba(1,2,1), au(1,21), la(12,1)]
 * <p>
 * Input: {9, 1, 8}
 * Output: {"iah", "ir"}
 * [2 interpretations: iah(9,1,8), ir(9,18)]
 */
public class FindAllPossibleInterpretation {
    StringBuilder single;
    List<String> result;

    public static void main(String[] args) {
        FindAllPossibleInterpretation test = new FindAllPossibleInterpretation();
        int[] arr = {1, 1, 1};
        List<String> result = test.printInterpretation(arr);
        System.out.println(result);
    }

    /**
     * Tree solution: http://www.geeksforgeeks.org/find-all-possible-interpretations/
     * Reference solution of Decodes Ways
     */
    public List<String> printInterpretation(int[] arr) {
        single = new StringBuilder();
        result = new ArrayList<String>();
        dfs(arr, 0, arr.length - 1);
        return result;
    }

    public void dfs(int[] arr, int start, int end) {
        if (start > end) {
            result.add(single.toString());
            return;
        }

        //Single number
        int num = arr[start];
        if (num >= 1 && num <= 26) {
            single.append((char) (num + 96));
            dfs(arr, start + 1, end);
            single.deleteCharAt(single.length() - 1);
        }

        //Two numbers
        if (start < end) {
            num = arr[start] * 10 + arr[start + 1];
            if (num >= 10 && num <= 26) {
                single.append((char) (num + 96));
                dfs(arr, start + 2, end);
                single.deleteCharAt(single.length() - 1);
            }
        }
    }
}
