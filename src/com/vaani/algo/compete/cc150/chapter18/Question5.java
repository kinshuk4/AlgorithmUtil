package com.vaani.algo.compete.cc150.chapter18;

import java.util.List;

/**
 * You have a large text file containing words. Given any two words, find the
 * shortest distance (in terms of number of words) between them in the file. Can
 * you make the searching operation in O(1) time? What about the space
 * complexity for your solution?
 */
// O(1) space, O(n) time
public class Question5 {

    // one time search
    public int distance(String str1, String str2, List<String> text) {
        // write implementation here
        int dist = 0;
        int lastStr1 = -1;
        int lastStr2 = -1;
        for (int i = 0; i < text.size(); ++i) {
            if (text.get(i).equals(str1)) {
                lastStr1 = i;
                if (lastStr2 != -1 && lastStr1 - lastStr2 < dist) {
                    dist = lastStr1 - lastStr2;
                }
            }
            if (text.get(i).equals(str2)) {
                lastStr2 = i;
                if (lastStr1 != -1 && lastStr2 - lastStr1 < dist) {
                    dist = lastStr2 - lastStr1;
                }
            }
        }

        return dist;
    }

}
