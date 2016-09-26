package com.vaani.algo.compete.cc150.chapter11;

import java.util.*;

/**
 * Write a method to sort an array of strings so that all the anagrams are next
 * to each other.
 */
public class Question2 {

    private String sort(String str) {
        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }

    ;

    public void sortAnagrams(String[] strs) {
        // write implementation here
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sorted = sort(str);
            List<String> list = map.get(sorted);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(sorted, list);
        }

        int p = 0;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            for (String str : list) {
                strs[p++] = str;
            }
        }

    }

    public static class AnagramComparator {

        private static Map<String, String> cache = new HashMap<String, String>();

        public int compare(String s1, String s2) {
            String sorted1 = null;
            String sorted2 = null;
            if (cache.containsKey(s1)) {
                sorted1 = cache.get(s1);
            } else {
                char[] arr = s1.toCharArray();
                Arrays.sort(arr);
                sorted1 = new String(arr);
                cache.put(s1, sorted1);
            }
            if (cache.containsKey(s2)) {
                sorted2 = cache.get(s2);
            } else {
                char[] arr = s2.toCharArray();
                Arrays.sort(arr);
                sorted2 = new String(arr);
                cache.put(s2, sorted2);
            }
            return sorted1.compareTo(sorted2);
        }
    }

}

