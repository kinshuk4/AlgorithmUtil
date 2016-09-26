package com.vaani.algo.compete.cc150.chapter18;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words of equal length that are in a dictionary, write a method t
 * otransform one word into another word by changing only one letter at a time.
 * The new word you get in each step must be in the dictionary. EXAMPLE Input:
 * DAMP, LIKE Output: DAMP -> LAMP -> LIMP -> LIME -> LIKE
 */
// O(n) space, O(n) time
public class Question10 {

    // this problem is available at leetcode, problem Word Ladder I and II
    public static class Solution {

        public int ladderLength(String start, String end, HashSet<String> dict) {
            // Start typing your Java solution below
            // DO NOT write main() function
            Queue<Pair> queue = new LinkedList<Pair>();
            Set<String> visited = new HashSet<String>();
            queue.add(new Pair(start, 1));
            visited.add(start);

            while (queue.size() > 0) {
                Pair pair = queue.poll();
                String curString = pair.str;
                int dist = pair.dist;
                char[] curChars = curString.toCharArray();
                char tmp;
                for (int i = 0; i < curChars.length; ++i) {  // substitute each position
                    tmp = curChars[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == tmp) {
                            continue;
                        }
                        curChars[i] = c;
                        String newStr = new String(curChars);
                        if (newStr.equals(end)) {
                            return dist + 1;
                        }
                        if (!visited.contains(newStr) && dict.contains(newStr)) {
                            queue.add(new Pair(newStr, dist + 1));
                            visited.add(newStr);
                        }
                    }
                    curChars[i] = tmp;
                }
            }
            return 0;
        }

        public static class Pair {
            public String str;
            public int dist;

            public Pair(String str, int dist) {
                this.str = str;
                this.dist = dist;
            }
        }
    }

}
