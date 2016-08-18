package com.vaani.algo.string;

import java.util.*;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * <p>
 * Note: All inputs will be in lower-case.
 * <p>
 * Created by Xiaomeng on 8/12/2014.
 */
public class Anagrams {
    public static void main(String[] args) {
        String[] strs = {"and", "dan"};
        Anagrams test = new Anagrams();
        System.out.println(test.anagrams(strs));
    }

    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String s : strs) {
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            String sorted = new String(charArr);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<String>());
            }
            map.get(sorted).add(s);
        }

        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                result.addAll(list);
            }
        }
        return result;
    }
}
