package com.vaani.algo.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Often, we want to encode raw IDs in our database by hiding them behind some 2-way decodeable hash. So, a URL which would have at one time been:
 * <p>
 * https://www.airbnb.com/rooms/848662
 * becomes
 * https://www.airbnb.com/rooms/kljJJ324hjkS_
 * <p>
 * We decode the ID kljJJ324hjkS to 848662 on our backend and serve the relevant content.
 * At some point, we start getting 404 errors from clients requesting a certain URL of the form
 * https://www.airbnb.com/rooms/kljjj324hjks_
 * <p>
 * This can happen if certain clients, email services, or url shorteners "sanitize" the url.
 * Unfortunately, this change breaks decoding and the resource cannot be found.
 * To assess how big of a deal this is, we may want to recover the IDs of the targets that were 404ing.
 * <p>
 * Given a method decode(testEncStr) which will return the decoded int id if testEncStr is decodeable or will throw an exception (or return null) if not,
 * implement a new method decodeFind(String badEncStr) which takes a string and returns the decoded int id.
 */
public class URLShortener {
    public static void main(String[] args) {
//     Solution s = new Solution();
        System.out.println(testString("a_3b"));
    }

    public static int decode(String in) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A_3b", 19870825);
        System.out.println(in);
        if (map.containsKey(in)) {
            return map.get(in);
        } else {
            throw new RuntimeException("");
        }
    }

    public static int testString(String original) {
        return helper(original.toCharArray(), 0);
    }

    public static int helper(char[] origin, int idx) {
        if (idx == origin.length) {
            String newStr = new String(origin);
            try {
                int id = decode(newStr);
                return id;
            } catch (Exception e) {
                return -1;
            }
        }

        if (origin[idx] - 'a' >= 0 && origin[idx] - 'z' <= 0) {
            int id = helper(origin, idx + 1);
            if (id != -1) return id;
            origin[idx] = Character.toUpperCase(origin[idx]);
            id = helper(origin, idx + 1);
            origin[idx] = Character.toLowerCase(origin[idx]);
            return id;
        } else
            return helper(origin, idx + 1);
    }
}
