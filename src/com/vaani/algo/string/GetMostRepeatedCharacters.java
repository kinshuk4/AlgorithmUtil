package com.vaani.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, get the characters that has the longest repeative substring.
 * <p>
 * e.g. this is a sentence -> t,h,i,s,i,s,a,s,e,n,t,e,n,c,e
 * this iss a sentence -> s
 * thiis iss a sentence -> i, s
 */
public class GetMostRepeatedCharacters {

    public List<Character> getMostRepeatedCharacters(String s) {
        List<Character> res = new ArrayList<Character>();
        int i = 0;
        int maxLen = 0;

        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') { // skip blank space
                ++i;
            }
            int cur = i;
            while (cur < s.length() && s.charAt(cur) == s.charAt(i)) { // scan the subsequence
                ++cur;
            }
            if (cur - i > maxLen) {
                maxLen = cur - i;
                res.clear();
                res.add(s.charAt(i));
            } else if (cur - i == maxLen) {
                res.add(s.charAt(i));
            }
            i = cur;
        }

        return res;
    }

}
