package com.vaani.algo.paradigm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a lowercase string 'ab',write a program to generate all possible lowercase and uppercase combination {'AB',‘Ab’,'aB' and 'ab'}
 * So, you will have 2^n (n = number of chars in the string) output strings. The goal is for you to test each of these string and see if it match a hidden string.
 */
public class CaseCombination {
    public static void main(String[] args) {
        CaseCombination test = new CaseCombination();
        System.out.println(test.getCombination("abc"));
    }

    public List<String> getCombination(String s) {
        List<String> result = new ArrayList<String>();
        StringBuilder single = new StringBuilder();
        getCombination(s, 0, single, result);
        return result;
    }

    public void getCombination(String s, int index, StringBuilder single, List<String> result) {
        if (index == s.length()) {
            result.add(single.toString());
            return;
        }

        single.append(s.charAt(index));
        getCombination(s, index + 1, single, result);
        single.deleteCharAt(single.length() - 1);

        single.append(Character.toUpperCase(s.charAt(index)));
        getCombination(s, index + 1, single, result);
        single.deleteCharAt(single.length() - 1);
    }
}
