package com.vaani.algo.string;

public class Strcmp {

    /**
     * If s1 > s2, return 1, if s1 == s2, return 0, else return -1
     *
     * @param s1
     * @param s2
     * @return
     */
    public int strcmp(String s1, String s2) {
        int pos1 = 0;
        int pos2 = 0;

        while (pos1 < s1.length() || pos2 < s2.length()) {
            if (pos1 == s1.length()) { // s1 reaches the end
                return -1;
            }
            if (pos2 == s2.length()) { // s2 reaches the end
                return 1;
            }
            if (s1.charAt(pos1) < s2.charAt(pos2)) {
                return -1;
            } else if (s1.charAt(pos1) > s2.charAt(pos2)) {
                return 1;
            } else {
                ++pos1;
                ++pos2;
            }
        }

        return 0;
    }

}
