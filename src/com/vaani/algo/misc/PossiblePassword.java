package com.vaani.algo.misc;

import java.util.*;

/**
 * Given a dictionary based simple password, create all possible (special character) passwords based on a provided mapping
 * Input: face
 * Map: {a -> @, 4, A}
 * Output: f@ce, f4ce, fAce"
 */
public class PossiblePassword {
    public static List<String> createPasswords(String s, Map<Character, List<Character>> map) {
        List<String> result = new ArrayList<String>();
        StringBuilder single = new StringBuilder();
        createPasswords(s, 0, map, single, result);
        return result;
    }

    public static void createPasswords(String s, int index, Map<Character, List<Character>> map, StringBuilder single, List<String> result) {
        if (index == s.length()) {
            result.add(single.toString());
            return;
        }

        if (map.containsKey(s.charAt(index))) {
            List<Character> specialChars = map.get(s.charAt(index));
            for (char ch : specialChars) {
                single.append(ch);
                createPasswords(s, index + 1, map, single, result);
                single.deleteCharAt(single.length() - 1);
            }
        } else {
            single.append(s.charAt(index));
            createPasswords(s, index + 1, map, single, result);
            single.deleteCharAt(single.length() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "face";
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
        map.put('a', new ArrayList<Character>(Arrays.asList('@', '4', 'A')));
        System.out.println(createPasswords(s, map));
    }
}
