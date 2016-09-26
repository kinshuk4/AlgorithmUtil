package com.vaani.algo.compete.cc150.chapter18;

import java.util.*;

/**
 * Given a list of words, write a program to find the longest word made of other
 * words in the list.
 */
// O(1) space, O(2^(d/2)) time, where d is the length of the word.
public class Question7 {

    public String longestWord(List<String> words) {
        // write implementation here
        Collections.sort(words, new LenComparator());
        // key: word, value: 0, not visited, 1, can be build, 2, cannot
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            map.put(word, 0);
        }

        for (String word : words) {
            if (canBeBuilt(word, map, true)) {
                return word;
            }
        }

        return "";
    }

    private boolean canBeBuilt(String word, Map<String, Integer> map, boolean fromStart) {
        Integer result = map.get(word);
        if (!fromStart && result != null && result != 2) {
            return true;
        }
        if (result != null && result == 2) {
            return false;
        }

        boolean canBeBuilt = false;
        for (int i = 1; i < word.length(); ++i) {
            String firstPart = word.substring(0, i);
            String secondPart = word.substring(i);
            if (canBeBuilt(firstPart, map, false) && canBeBuilt(secondPart, map, false)) {
                canBeBuilt = true;
                break;
            }
        }
        //  update status
        if (canBeBuilt) {
            map.put(word, 1);
        } else {
            map.put(word, 2);
        }
        return canBeBuilt;
    }

    public static class LenComparator implements Comparator<String> {
        public int compare(String str1, String str2) {
            if (str1.length() > str2.length()) {
                return -1;
            }
            return -str1.compareTo(str2);
        }
    }

}

